package pl.goldy.danowski.fiszki.db.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.Getter;
import pl.goldy.danowski.fiszki.db.dao.CategoryDao;
import pl.goldy.danowski.fiszki.db.dao.FlashcardDao;
import pl.goldy.danowski.fiszki.db.dao.LanguageDao;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

public class DatabaseRepository {

    private FlashcardDao flashcardDao;
    private LanguageDao languageDao;
    private CategoryDao categoryDao;

    public DatabaseRepository(Application application) {
        FlashcardDatabase db = FlashcardDatabase.getDatabase(application);
        flashcardDao = db.flashcardDao();
        languageDao = db.languageDao();
        categoryDao = db.categoryDao();
    }

    // ================ INSERTS ================
    public void insertFlashcard (FlashcardEntity card) {
        new InsertFlashcardAsyncTask(flashcardDao).execute(card);
    }

    public void insertCategory (CategoryEntity cat) {
        new InsertCategoryAsyncTask(categoryDao).execute(cat);
    }

    public void insertLanguage (LanguageEntity lang) {
        new InsertLanguageAsyncTask(languageDao).execute(lang);
    }

    private static class InsertFlashcardAsyncTask extends AsyncTask<FlashcardEntity, Void, Void> {
        InsertFlashcardAsyncTask(FlashcardDao dao) {
            this.dao = dao;
        }

        private FlashcardDao dao;

        @Override
        protected Void doInBackground(FlashcardEntity... flashcardEntities) {
            dao.insert(flashcardEntities[0]);
            return null;
        }
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {
        InsertCategoryAsyncTask(CategoryDao dao) {
            this.dao = dao;
        }

        private CategoryDao dao;

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            dao.insert(categoryEntities[0]);
            return null;
        }
    }

    private static class InsertLanguageAsyncTask extends AsyncTask<LanguageEntity, Void, Void> {
        InsertLanguageAsyncTask(LanguageDao dao) {
            this.dao = dao;
        }

        private LanguageDao dao;

        @Override
        protected Void doInBackground(LanguageEntity... languageEntities) {
            dao.insert(languageEntities[0]);
            return null;
        }
    }

    // ================ DELETES ================
    public void deleteFlashcard (FlashcardEntity card) {
        new DeleteFlashcardAsyncTask(flashcardDao).execute(card);
    }

    public void deleteCategory (CategoryEntity cat) {
        new DeleteCategoryAsyncTask(categoryDao).execute(cat);
    }

    public void deleteLanguage (LanguageEntity lang) {
        new DeleteLanguageAsyncTask(languageDao).execute(lang);
    }

    private static class DeleteFlashcardAsyncTask extends AsyncTask<FlashcardEntity, Void, Void> {
        DeleteFlashcardAsyncTask(FlashcardDao dao) {
            this.dao = dao;
        }

        private FlashcardDao dao;

        @Override
        protected Void doInBackground(FlashcardEntity... flashcardEntities) {
            dao.delete(flashcardEntities[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {
        DeleteCategoryAsyncTask(CategoryDao dao) {
            this.dao = dao;
        }

        private CategoryDao dao;

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            dao.delete(categoryEntities[0]);
            return null;
        }
    }

    private static class DeleteLanguageAsyncTask extends AsyncTask<LanguageEntity, Void, Void> {
        DeleteLanguageAsyncTask(LanguageDao dao) {
            this.dao = dao;
        }

        private LanguageDao dao;

        @Override
        protected Void doInBackground(LanguageEntity... languageEntities) {
            dao.delete(languageEntities[0]);
            return null;
        }
    }

    // ================ UPDATES ================
    public void updateFlashcard (FlashcardEntity card) {
        new UpdateFlashcardAsyncTask(flashcardDao).execute(card);
    }

    public void updateCategory (CategoryEntity cat) {
        new UpdateCategoryAsyncTask(categoryDao).execute(cat);
    }

    public void updateLanguage (LanguageEntity lang) {
        new UpdateLanguageAsyncTask(languageDao).execute(lang);
    }

    private static class UpdateFlashcardAsyncTask extends AsyncTask<FlashcardEntity, Void, Void> {
        UpdateFlashcardAsyncTask(FlashcardDao dao) {
            this.dao = dao;
        }

        private FlashcardDao dao;

        @Override
        protected Void doInBackground(FlashcardEntity... flashcardEntities) {
            dao.update(flashcardEntities[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {
        UpdateCategoryAsyncTask(CategoryDao dao) {
            this.dao = dao;
        }

        private CategoryDao dao;

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            dao.update(categoryEntities[0]);
            return null;
        }
    }

    private static class UpdateLanguageAsyncTask extends AsyncTask<LanguageEntity, Void, Void> {
        UpdateLanguageAsyncTask(LanguageDao dao) {
            this.dao = dao;
        }

        private LanguageDao dao;

        @Override
        protected Void doInBackground(LanguageEntity... languageEntities) {
            dao.update(languageEntities[0]);
            return null;
        }
    }

    // ================ GETTERS ================
    public List<LanguageEntity> getAllLanguages() {
        try {
            return new GetAllLanguagesAsyncTask(languageDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FlashcardEntity> getFlashcardsByCategoryId(int id) {
        try {
            return new GetFlashcardsByCategoryIdAsyncTask(flashcardDao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CategoryEntity> getCategoriesByLanguageId(int id) {
        try {
            return new GetCategoriesByLanguageIdAsyncTask(categoryDao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LanguageEntity getLanguageById(int id) {
        try {
            return new GetLanguageByIdAsyncTask(languageDao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CategoryEntity getCategoryById(int id) {
        try {
            return new GetCategoryByIdAsyncTask(categoryDao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetAllLanguagesAsyncTask  extends AsyncTask<Void, Void, ArrayList<LanguageEntity>> {
        GetAllLanguagesAsyncTask(LanguageDao languageDao) {
            this.dao = languageDao;
        }

        private LanguageDao dao;

        @Override
        protected ArrayList<LanguageEntity> doInBackground(Void... voids) {
            return (ArrayList<LanguageEntity>) dao.getAll();
        }
    }

    private static class GetCategoriesByLanguageIdAsyncTask extends AsyncTask<Integer, Void, ArrayList<CategoryEntity>> {
        GetCategoriesByLanguageIdAsyncTask(CategoryDao dao) {
            this.dao = dao;
        }

        private CategoryDao dao;

        @Override
        protected ArrayList<CategoryEntity> doInBackground(Integer... ints) {
            return (ArrayList<CategoryEntity>) dao.getFromLanguage(ints[0]);
        }
    }

    private static class GetFlashcardsByCategoryIdAsyncTask extends AsyncTask<Integer, Void, ArrayList<FlashcardEntity>> {
        GetFlashcardsByCategoryIdAsyncTask(FlashcardDao dao) {
            this.dao = dao;
        }

        private FlashcardDao dao;

        @Override
        protected ArrayList<FlashcardEntity> doInBackground(Integer... ints) {
            return (ArrayList<FlashcardEntity>) dao.getFromCategory(ints[0]);
        }
    }

    private static class GetLanguageByIdAsyncTask  extends AsyncTask<Integer, Void, LanguageEntity> {
        GetLanguageByIdAsyncTask(LanguageDao languageDao) {
            this.dao = languageDao;
        }

        private LanguageDao dao;

        @Override
        protected LanguageEntity doInBackground(Integer... integers) {
            return dao.getById(integers[0]);
        }
    }

    private static class GetCategoryByIdAsyncTask  extends AsyncTask<Integer, Void, CategoryEntity> {
        GetCategoryByIdAsyncTask(CategoryDao dao) {
            this.dao = dao;
        }

        private CategoryDao dao;

        @Override
        protected CategoryEntity doInBackground(Integer... integers) {
            return dao.getById(integers[0]);
        }
    }
}
