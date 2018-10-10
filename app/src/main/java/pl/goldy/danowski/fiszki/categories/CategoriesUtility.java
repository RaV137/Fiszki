package pl.goldy.danowski.fiszki.categories;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;

class CategoriesUtility {

    private static boolean initialized = false;
    private static ArrayList<CategoryEntity> cats;

    @Getter
    @Setter
    private static String currentTitle;

    static void initialize() {
        if(initialized)
            return;

        initialized = true;
        cats = new ArrayList<>();
        fillList();
    }

    private static void fillList() {
        if (!initialized) throw new AssertionError("Class not initialized!");

        for(int i = 1; i < 6; ++i) {
            cats.add(new CategoryEntity("Kategoria nr " + i));
        }
    }

    static CategoryEntity getCategory(int position) {
        return cats.get(position);
    }

    static void printCategories(View headView) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        GridView gridView = headView.findViewById(R.id.catGrid);
        ArrayAdapter<CategoryEntity> adapter;
        adapter = new CategoryAdapter(headView.getContext(), cats);
        gridView.setAdapter(adapter);
    }

    static void deleteCategory(View headView, int id) {
        cats.remove(id);
        printCategories(headView);
    }

    static void editCategory(int position, String name) {
        CategoryEntity cat = getCategory(position);
        cat.setName(name);
    }

    static void addNewCategory(String name) {
        cats.add(new CategoryEntity(name));
    }
}
