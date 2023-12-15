package uj.wmii.pwj.anns;

public class MyBeautifulTestSuite {

    @MyTest

    public int testSomething() {
        System.out.println("I'm testing something!");
        return 1;
    }

    @MyTest(params = {"a", "b", "c"}, results = {"a", "d", "abc"})
    public String testWithLetters(String param) {
        System.out.printf("I was invoked with parameter: %s\n", param);
        return param;
    }

    @MyTest(params = {"1,2", "2,3"}, results = {"3", "5"})
    public int testWithTwoParams(Integer a, Integer b) {
        System.out.println("I'm testing something!");
        return a + b;
    }

    @MyTest(params = {"1.2", "2.3"}, results = {"2.2", "3.3"})
    public float testWithFloat(Float a) {
        return a + 1;
    }

    @MyTest(results = "lol", params = {"lol"})
    public String testWithSingleParam(String param) {
        return param;
    }

    public void notATest() {
        System.out.println("I'm not a test.");
    }

    @MyTest()
    public void imFailure() {
        System.out.println("I AM EVIL.");
        throw new NullPointerException();
    }

    @MyTest(params = {"true", "false", "false"}, results = {"false", "false", "false" })
    public boolean testWithWrongAnswer(Boolean b) {
        return !b;
    }

}
