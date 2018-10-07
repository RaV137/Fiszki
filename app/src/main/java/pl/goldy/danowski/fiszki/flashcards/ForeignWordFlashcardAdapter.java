package pl.goldy.danowski.fiszki.flashcards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.goldy.danowski.fiszki.R;

public class ForeignWordFlashcardAdapter extends ArrayAdapter<Flashcard> {

    public ForeignWordFlashcardAdapter(Context context, ArrayList<Flashcard> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Flashcard card = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.flashcard, parent, false);
        }

        TextView mWord = convertView.findViewById(R.id.word);
        assert card != null : "Received flashcard is null!";
        mWord.setText(card.getForeign());

        return convertView;
    }
}