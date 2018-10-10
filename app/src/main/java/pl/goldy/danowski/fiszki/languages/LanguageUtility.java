package pl.goldy.danowski.fiszki.languages;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

class LanguageUtility {

    private static boolean initialized = false;
    private static ArrayList<LanguageEntity> langs;

    static void initialize() {
        if(initialized)
            return;

        initialized = true;
        langs = new ArrayList<>();
        fillList();
    }

    private static void fillList() {
        if (!initialized) throw new AssertionError("Class not initialized!");

        for(int i = 1; i < 6; ++i) {
            langs.add(new LanguageEntity("Język nr " + i));
        }
    }

    static void deleteLang(View headView, int id) {
        langs.remove(id);
        printLangs(headView);
    }

    static void printLangs(View headView) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        GridView gridView = headView.findViewById(R.id.langGrid);
        ArrayAdapter<LanguageEntity> adapter;
        adapter = new LanguageAdapter(headView.getContext(), langs);
        gridView.setAdapter(adapter);
    }

    static LanguageEntity getLang(int position) {
        return langs.get(position);
    }

    static void addNewLang(String name) {
        langs.add(new LanguageEntity(name));
    }

    static void editLang(int position, String name) {
        LanguageEntity lang = getLang(position);
        lang.setName(name);
    }
}
