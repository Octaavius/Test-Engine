package uj.wmii.pwj.anns;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Class.forName;

public class MyTestEngine {
    private final String className;

    public static void main(String[] args) throws ClassNotFoundException {

        if (args.length < 1) {
            System.out.println("Please specify test class name");
            System.exit(-1);
        }
        String className = args[0].trim();
        System.out.printf("Testing class: %s\n", className);
        MyTestEngine engine = new MyTestEngine(className);
        engine.runTests();
    }

    public MyTestEngine(String className) {
        AsciiArt.drawAsciiArt();
        this.className = className;
    }

    public void runTests() throws ClassNotFoundException {
        final Object unit = getObject(className);
        List<Method> testMethods = getTestMethods(unit);
        int successCount = 0;
        int failCount = 0;
        int errorCount = 0;
        for (Method m: testMethods) {
            TestResult result = launchSingleMethod(m, unit);
            if (result == TestResult.PASS) successCount++;
            else if(result == TestResult.FAIL) failCount++;
            else errorCount++;
        }
        System.out.printf("Engine launched %d tests.\n", testMethods.size());
        System.out.printf(ConsoleColors.GREEN + "%d" + ConsoleColors.RESET + " of them passed, " +
                ConsoleColors.RED + "%d" + ConsoleColors.RESET + " failed, " +
                ConsoleColors.YELLOW + "%d" + ConsoleColors.RESET + " ended with error.\n", successCount, failCount, errorCount);
    }
    TestResult printResults(Method m, boolean testResults, String[] params, String[] results, String[] actualResults){
        if(testResults){
            System.out.println(ConsoleColors.GREEN + "Tested method: " + m.getName() + " test successful." + ConsoleColors.RESET + "\n----------------------------------------------------------------");
            return TestResult.PASS;
        } else {
            System.out.println(ConsoleColors.RED + "Tested method: " + m.getName() + " test failed.");
            for(int i = 0; i < results.length; ++i){
                System.out.println(i + 1 + ":For param: " + params[i]);
                System.out.println("   Expected: " + results[i]);
                System.out.println("   Actual: " + actualResults[i]);
            }
            System.out.println(ConsoleColors.RESET + "----------------------------------------------------------------");
            return TestResult.FAIL;
        }
    }
    private TestResult launchSingleMethod(Method m, Object unit) {
        try {
            String[] params = m.getAnnotation(MyTest.class).params();
            String[] results = m.getAnnotation(MyTest.class).results();
            String[] actualResults = new String[results.length];
            boolean testResults = true;
            Class<?>[] paramTypes = m.getParameterTypes();
            if (params.length == 0) {
                Object resultOfMethod = m.invoke(unit);
                actualResults[0] = resultOfMethod.toString();
                testResults = resultOfMethod.toString().equals(results[0]);
            } else{
                for(int i = 0; i < params.length; ++i){
                    String[] splitParam = params[i].split(",");
                    Object[] paramsObject = new Object[splitParam.length];

                    for (int j = 0; j < splitParam.length; j++) {
                        paramsObject[j] = paramTypes[j].getConstructor(String.class).newInstance(splitParam[j]);
                    }
                    actualResults[i] = m.invoke(unit, paramsObject).toString();
                    if(!actualResults[i].equals(results[i])){
                        testResults = false;
                    }
                }
            }

            return printResults(m, testResults, params, results, actualResults);
            //return TestResult.FAIL;
        } catch (ReflectiveOperationException | RuntimeException e) {
            System.out.println(ConsoleColors.YELLOW);
            e.printStackTrace( System.out);
            System.out.println("\n\n\nTested method: " + m.getName() + " test ended with error." + ConsoleColors.RESET + "\n----------------------------------------------------------------");
            return TestResult.ERROR;
        }
    }
    private static List<Method> getTestMethods(Object unit) {
        Method[] methods = unit.getClass().getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(m -> m.getAnnotation(MyTest.class) != null).collect(Collectors.toList());
    }
    private static Object getObject(String className) {
        try {
            Class<?> unitClass = forName(className);
            return unitClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new Object();
        }
    }
}
