package pl.goldy.danowski.fiszki.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "flashcards", foreignKeys = @ForeignKey(entity = CategoryEntity.class,
        parentColumns = "id",
        childColumns = "category_id"))
@Getter
@Setter
public class FlashcardEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "foreign_word")
    private String foreignWord;

    @ColumnInfo(name = "foreign_use_case")
    private String foreignUseCase;

    @ColumnInfo(name = "polish_word")
    private String polishWord;

    @ColumnInfo(name = "polish_use_case")
    private String polishUseCase;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    public FlashcardEntity(String polishWord, String foreignWord, String polishUseCase, String foreignUseCase) {
        this.polishWord = polishWord;
        this.foreignWord = foreignWord;
        this.polishUseCase = polishUseCase;
        this.foreignUseCase = foreignUseCase;
        resetTaps();
    }

    public void resetTaps() {
        this.taps = 0;
    }

    public void incrementTaps() {
        this.taps++;
    }

    @Ignore
    @Setter(AccessLevel.NONE)
    private int taps;
}
