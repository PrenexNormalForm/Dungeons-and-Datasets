package dnds;
/*
Last updated Sep 27, 2019

Simply the main class. Created because java 11 doesn't like it when your main
class extends javafx.application.Application.

Contributors:
Eva Moniz
 */

import view.DNDSApplication;
import javafx.application.Application;

/**
 * This class starts the program.
 *
 * @author Eva Moniz
 */
public class DungeonsAndDatasets {

    public static void main(String[] args) {
       Application.launch(DNDSApplication.class, args);
    }
}
