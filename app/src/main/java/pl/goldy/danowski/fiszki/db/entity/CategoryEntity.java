package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "categories", foreignKeys = @ForeignKey(entity = LanguageEntity.class,
        parentColumns = "id",
        childColumns = "language_id"))
@Getter
@Setter
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    @ColumnInfo(name = "language_id")
    private Integer languageId;

    @Ignore
    public CategoryEntity(String name) {
        this.name = name;
    }

    @Ignore
    public CategoryEntity(String name, Integer languageId) {
        this.name = name;
        this.languageId = languageId;
    }

    public CategoryEntity(Integer id, String name, Integer languageId) {
        this.id = id;
        this.name = name;
        this.languageId = languageId;
    }
}
