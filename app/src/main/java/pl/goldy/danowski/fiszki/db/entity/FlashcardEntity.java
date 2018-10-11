package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "flashcards")
@Getter
@Setter
public class FlashcardEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "foreign_word")
    private String foreignWord;

    @ColumnInfo(name = "foreign_use_case")
    private String foreignUseCase;

    @ColumnInfo(name = "polish_word")
    private String polishWord;

    @ColumnInfo(name = "polish_use_case")
    private String polishUseCase;

    @ColumnInfo(name = "category_id")
    @ForeignKey(entity = CategoryEntity.class, childColumns = "category_id", parentColumns = "id")
    private Integer categoryId;

    @Ignore
    public FlashcardEntity(String polishWord, String foreignWord, String polishUseCase, String foreignUseCase) {
        this.polishWord = polishWord;
        this.foreignWord = foreignWord;
        this.polishUseCase = polishUseCase;
        this.foreignUseCase = foreignUseCase;
        resetTaps();
    }

    @Ignore
    public FlashcardEntity(String foreignWord, String foreignUseCase, String polishWord, String polishUseCase, Integer categoryId) {
        this.foreignWord = foreignWord;
        this.foreignUseCase = foreignUseCase;
        this.polishWord = polishWord;
        this.polishUseCase = polishUseCase;
        this.categoryId = categoryId;
        resetTaps();
    }

    @Ignore
    public void resetTaps() {
        this.taps = 0;
    }

    @Ignore
    public void incrementTaps() {
        this.taps++;
    }

    @Ignore
    @Setter(AccessLevel.NONE)
    private int taps;

    public FlashcardEntity(Integer id, String foreignWord, String foreignUseCase, String polishWord, String polishUseCase, Integer categoryId) {
        this.id = id;
        this.foreignWord = foreignWord;
        this.foreignUseCase = foreignUseCase;
        this.polishWord = polishWord;
        this.polishUseCase = polishUseCase;
        this.categoryId = categoryId;
        resetTaps();
    }
}
