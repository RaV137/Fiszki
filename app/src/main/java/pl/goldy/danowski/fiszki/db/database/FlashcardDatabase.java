package pl.goldy.danowski.fiszki.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.goldy.danowski.fiszki.db.dao.FlashcardDao;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

@Database(entities = {FlashcardEntity.class, LanguageEntity.class, CategoryEntity.class}, version = 1)
public abstract class FlashcardDatabase extends RoomDatabase {

    public abstract FlashcardDao getFlashcardDao();

}
