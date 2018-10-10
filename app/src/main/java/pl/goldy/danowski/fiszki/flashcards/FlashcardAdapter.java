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
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;

public class FlashcardAdapter extends ArrayAdapter<FlashcardEntity> {

    FlashcardAdapter(Context context, ArrayList<FlashcardEntity> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        FlashcardEntity card = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        TextView mWord = convertView.findViewById(R.id.text);
        assert card != null : "Received grid_item is null!";

        if(FlashcardUtility.getForeign()) {
            mWord.setText(card.getForeignWord());
        } else {
            mWord.setText(card.getPolishWord());
        }

        return convertView;
    }
}