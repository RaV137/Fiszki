package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "languages")
@Setter
@Getter
public class LanguageEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    @Ignore
    public LanguageEntity(String name) {
        this.name = name;
    }

    public LanguageEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
