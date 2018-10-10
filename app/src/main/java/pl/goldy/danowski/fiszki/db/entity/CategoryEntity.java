package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "categories", foreignKeys = @ForeignKey(entity = LanguageEntity.class,
        parentColumns = "id",
        childColumns = "language_id"))
@Getter
@Setter
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(name = "language_id")
    private int languageId;

    public CategoryEntity(String name) {
        this.name = name;
    }
}
