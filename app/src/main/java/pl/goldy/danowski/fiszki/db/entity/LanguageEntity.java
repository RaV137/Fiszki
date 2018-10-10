package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "languages")
@Setter
@Getter
public class LanguageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public LanguageEntity(String name) {
        this.name = name;
    }

}
