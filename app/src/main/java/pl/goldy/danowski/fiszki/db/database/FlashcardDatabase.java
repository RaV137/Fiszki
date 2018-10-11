package pl.goldy.danowski.fiszki.db.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import pl.goldy.danowski.fiszki.db.dao.CategoryDao;
import pl.goldy.danowski.fiszki.db.dao.FlashcardDao;
import pl.goldy.danowski.fiszki.db.dao.LanguageDao;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

@Database(entities = {FlashcardEntity.class, LanguageEntity.class, CategoryEntity.class}, version = 7)
public abstract class FlashcardDatabase extends RoomDatabase {

    public abstract FlashcardDao flashcardDao();

    public abstract CategoryDao categoryDao();

    public abstract LanguageDao languageDao();

    private static volatile FlashcardDatabase INSTANCE;

    static FlashcardDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlashcardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FlashcardDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
//
//    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) { }
//    };

}
