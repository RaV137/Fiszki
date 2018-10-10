package pl.goldy.danowski.fiszki.flashcards;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
;
import lombok.Getter;
import lombok.Setter;
import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;

class FlashcardUtility {

    private static ArrayList<FlashcardEntity> cards;
    private static boolean initialized = false;
    private static boolean foreignWords = false;

    @Getter
    @Setter
    private static String currentTitle;

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
        ArrayAdapter<FlashcardEntity> adapter;
        adapter = new FlashcardAdapter(view.getContext(), cards);
        gridView.setAdapter(adapter);
    }

    private static void fillList() {
        if (!initialized) throw new AssertionError("Class not initialized!");

        for(int i = 1; i < 21; ++i) {
            cards.add(new FlashcardEntity("Słówko " + i,"Word " + i, "Przykład użycia nr " + i, "Use case no " + i));
        }
    }

    static void addNewFlashCard(String foreignWord, String foreignUseCase, String polishWord, String polishUseCase) {
        cards.add(new FlashcardEntity(polishWord, foreignWord, polishUseCase, foreignUseCase));
    }

    static void shuffleCards(View view) {
        Collections.shuffle(cards);
        printCards(view);
    }

    static void changeLanguage(View headView) {
        foreignWords = !foreignWords;
        for(FlashcardEntity card : cards){
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
        FlashcardEntity card = cards.get(position);
        int taps = card.getTaps();
        if(taps == 0) {
            if(foreignWords) {
                textView.setText(card.getPolishWord());
            } else {
                textView.setText(card.getForeignWord());
            }
            card.incrementTaps();
            return false;
        } else {
            if(!foreignWords) {
                textView.setText(card.getPolishWord());
            } else {
                textView.setText(card.getForeignWord());
            }
            card.resetTaps();
            return true;
        }
    }

    static FlashcardEntity getCardFromArray(int position) {
        return cards.get(position);
    }

    static void deleteCard(int id, View headView) {
        cards.remove(id);
        printCards(headView);
    }

    static void editFlashcard(String foreignWord, String foreignUseCase, String polishWord, String polishUseCase, int position) {
        FlashcardEntity card = cards.get(position);
        card.setForeignWord(foreignWord);
        card.setForeignUseCase(foreignUseCase);
        card.setPolishWord(polishWord);
        card.setPolishUseCase(polishUseCase);
    }

    static boolean getForeign() {
        return foreignWords;
    }
}
