package pl.goldy.danowski.fiszki.flashcards;

import java.util.ArrayList;

public class FlashcardUtility {

    private static ArrayList<Flashcard> cards;

    private static boolean initialized = false;

    public static void initialize() {
        if(initialized)
            return;

        cards = new ArrayList<>();
        initialized = true;
    }

    private static void print() {
        // TODO


    }

}
