package pl.goldy.danowski.fiszki.languages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

public class LanguageAdapter extends ArrayAdapter<LanguageEntity> {

    LanguageAdapter(Context context, ArrayList<LanguageEntity> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LanguageEntity lang = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        TextView mWord = convertView.findViewById(R.id.text);
        assert lang != null : "Language is null!";
        mWord.setText(lang.getName());

        return convertView;
    }
}
