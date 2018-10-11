package pl.goldy.danowski.fiszki.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

@Dao
public interface LanguageDao {

    @Query("SELECT * FROM languages")
    List<LanguageEntity> getAll();

    @Query("SELECT * FROM languages WHERE id = :id")
    LanguageEntity getById(int id);

    @Insert
    void insert(LanguageEntity lang);

    @Delete
    void delete(LanguageEntity lang);

    @Update
    void update(LanguageEntity lang);

}
