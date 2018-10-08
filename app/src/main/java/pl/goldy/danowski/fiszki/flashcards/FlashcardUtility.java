package pl.goldy.danowski.fiszki.flashcards;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import pl.goldy.danowski.fiszki.R;

class FlashcardUtility {

    private static ArrayList<Flashcard> cards;
    private static boolean initialized = false;

    private static boolean foreignWords = false;

    static void initialize() {
        if(initialized)
            return;

        initialized = true;
        cards = new ArrayList<>();
        fillList();
    }

    static void printCards(final View view) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        GridView gridView = view.findViewById(R.id.flashcardsGrid);
        ArrayAdapter<Flashcard> adapter;
        if(foreignWords) {
            adapter = new ForeignWordFlashcardAdapter(view.getContext(), cards);
        } else {
            adapter = new PolishWordFlashcardAdapter(view.getContext(), cards);
        }
        gridView.setAdapter(adapter);
    }

    private static void fillList() {
        if (!initialized) throw new AssertionError("Class not initialized!");

        for(int i = 1; i < 21; ++i) {
            cards.add(new Flashcard("Słówko " + i,"Word " + i, "Przykład użycia nr " + i, "Use case no " + i));
        }
    }

    static void addNewFlashCard(String foreignWord, String foreignUseCase, String polishWord, String polishUseCase) {
        cards.add(new Flashcard(polishWord, foreignWord, polishUseCase, foreignUseCase));
    }

    static void shuffleCards(View view) {
        Collections.shuffle(cards);
        printCards(view);
    }

    static void changeLanguage(View headView) {
        foreignWords = !foreignWords;
        for(Flashcard card : cards){
            card.resetTaps();
        }
        printCards(headView);
    }

    /**
     *
     * @param position Position of card in an array.
     * @param view View with word from selected card,
     * @return True = show details, false = do nothing.
     */
    static boolean flashcardClicked(int position, View view) {
        TextView textView = (TextView) view;
        Flashcard card = cards.get(position);
        int taps = card.getTaps();
        if(taps == 0) {
            if(foreignWords) {
                textView.setText(card.getPolish());
            } else {
                textView.setText(card.getForeign());
            }
            card.incrementTaps();
            return false;
        } else {
            if(!foreignWords) {
                textView.setText(card.getPolish());
            } else {
                textView.setText(card.getForeign());
            }
            card.resetTaps();
            return true;
        }
    }

    static Flashcard getCardFromArray(int position) {
        return cards.get(position);
    }

    static void changeLanguageDialog() {
        // TODO
    }

    public static boolean getForeign() {
        return foreignWords;
    }
}
