package dnds;
/*
Last updated Oct 29, 2019

Simply the main class. Created because java 11 doesn't like it when your main
class extends javafx.application.Application.

Contributors:
Jonathan Bacon
Brandon Pozil
Eva Moniz
 */

import view.DNDSApplication;
import javafx.application.Application;
import java.io.IOException;

/**
 * This class starts the program.
 */
public class DungeonsAndDatasets {

    public static void main(String[] args) throws IOException {
        Application.launch(DNDSApplication.class, args);
        TestCases.testChoice();
    }
}
