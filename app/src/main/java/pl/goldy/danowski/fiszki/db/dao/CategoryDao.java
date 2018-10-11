package pl.goldy.danowski.fiszki.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM categories")
    List<CategoryEntity> getAll();

    @Query("SELECT * FROM categories WHERE language_id = :languageId")
    List<CategoryEntity> getFromLanguage(int languageId);

    @Query("SELECT * FROM categories WHERE id = :id")
    CategoryEntity getById(int id);

    @Insert
    void insert(CategoryEntity cat);

    @Delete
    void delete(CategoryEntity cat);

    @Update
    void update(CategoryEntity cat);

}
