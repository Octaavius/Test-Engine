package uj.wmii.pwj.anns;

public class AsciiArt {
    public static void drawAsciiArt(){
        System.out.println(
                ConsoleColors.RED +  ConsoleColors.BLACK_BACKGROUND +
                        "#### ##  ### ###   ## ##   #### ##           ### ###  ###  ##   ## ##     ####   ###  ##  ### ### " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.YELLOW + ConsoleColors.BLACK_BACKGROUND+
                        "# ## ##   ##  ##  ##   ##  # ## ##            ##  ##    ## ##  ##   ##     ##      ## ##   ##  ## " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.GREEN + ConsoleColors.BLACK_BACKGROUND +
                        "  ##      ##      ####       ##               ##       # ## #  ##          ##     # ## #   ##     " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.BLUE + ConsoleColors.BLACK_BACKGROUND +
                        "  ##      ## ##    #####     ##               ## ##    ## ##   ##  ###     ##     ## ##    ## ##  " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.PURPLE +  ConsoleColors.BLACK_BACKGROUND +
                        "  ##      ##          ###    ##               ##       ##  ##  ##   ##     ##     ##  ##   ##     " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.RED +  ConsoleColors.BLACK_BACKGROUND +
                        "  ##      ##  ##  ##   ##    ##               ##  ##   ##  ##  ##   ##     ##     ##  ##   ##  ## " +
                        ConsoleColors.RESET + "\n" + ConsoleColors.YELLOW + ConsoleColors.BLACK_BACKGROUND +
                        " ####    ### ###   ## ##    ####             ### ###  ###  ##   ## ##     ####   ###  ##  ### ### " +
                        ConsoleColors.RESET);
    }
}
