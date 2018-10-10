package pl.goldy.danowski.fiszki.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;

@Dao
public interface FlashcardDao {

    @Query("SELECT * FROM flashcards")
    List<FlashcardEntity> getAll();

    @Query("SELECT * FROM flashcards WHERE category_id = :categoryId")
    List<FlashcardEntity> getFromCategory(int categoryId);

    @Query("SELECT * FROM flashcards WHERE id = :id")
    FlashcardEntity getById(int id);

    @Insert
    void insert(FlashcardEntity card);

    @Delete
    void delete(FlashcardEntity card);

    @Update
    void update(FlashcardEntity card);
}
