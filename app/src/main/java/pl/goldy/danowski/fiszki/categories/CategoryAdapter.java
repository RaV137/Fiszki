package pl.goldy.danowski.fiszki.categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

class CategoryAdapter extends ArrayAdapter<CategoryEntity> {

    CategoryAdapter(Context context, ArrayList<CategoryEntity> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        CategoryEntity cat = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        TextView mWord = convertView.findViewById(R.id.text);
        assert cat != null : "Language is null!";
        mWord.setText(cat.getName());

        return convertView;
    }
}
