package pl.goldy.danowski.fiszki.categories;

import android.app.Application;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.database.DatabaseRepository;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

class CategoriesUtility {

    private static boolean initialized = false;
    private static ArrayList<CategoryEntity> cats;
    private static DatabaseRepository repo;
    private static LanguageEntity currentLanguage;

    static String getCurrentTitle() {
        return currentLanguage.getName();
    }

    static Integer getCurrentLanguageId() {
        return currentLanguage.getId();
    }

    static void initialize(Application application, int languageId) {
        if(initialized)
            return;

        initialized = true;
        cats = new ArrayList<>();
        repo = new DatabaseRepository(application);
        currentLanguage = repo.getLanguageById(languageId);
        fillList();
    }

    static void destroy() {
        initialized = false;
        cats = null;
        repo = null;
        currentLanguage = null;
    }

    private static void fillList() {
        if (!initialized) throw new AssertionError("Class not initialized!");

        cats = (ArrayList<CategoryEntity>) repo.getCategoriesByLanguageId(getCurrentLanguageId());

//        for(int i = 1; i < 6; ++i) {
//            cats.add(new CategoryEntity("Kategoria nr " + i));
//        }
    }

    static CategoryEntity getCategory(int position) {
        return cats.get(position);
    }

    static void printCategories(View headView) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        fillList();
        GridView gridView = headView.findViewById(R.id.catGrid);
        ArrayAdapter<CategoryEntity> adapter;
        adapter = new CategoryAdapter(headView.getContext(), cats);
        gridView.setAdapter(adapter);
    }

    static void deleteCategory(View headView, int id) {
        repo.deleteCategory(getCategory(id));
        cats.remove(id);
        printCategories(headView);
    }

    static void editCategory(int position, String name) {
        CategoryEntity cat = getCategory(position);
        cat.setName(name);
        repo.updateCategory(cat);
    }

    static void addNewCategory(String name) {
        CategoryEntity newCat = new CategoryEntity(name, getCurrentLanguageId());
        repo.insertCategory(newCat);
        cats.add(newCat);
    }
}
