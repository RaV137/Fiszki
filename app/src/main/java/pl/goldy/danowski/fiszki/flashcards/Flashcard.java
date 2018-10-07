package pl.goldy.danowski.fiszki.flashcards;

public class Flashcard {

    private String polish, foreign, polishUseCase, foreignUseCase;

    public Flashcard(String polish, String foreign) {
        this.polish = polish;
        this.foreign = foreign;
        this.polishUseCase = "";
        this.foreignUseCase = "";
    }

    public Flashcard(String polish, String foreign, String polishUseCase, String foreignUseCase) {
        this.polish = polish;
        this.foreign = foreign;
        this.polishUseCase = polishUseCase;
        this.foreignUseCase = foreignUseCase;
    }

    public String getPolish() {
        return polish;
    }

    public String getForeign() {
        return foreign;
    }

    public String getPolishUseCase() {
        return polishUseCase;
    }

    public String getForeignUseCase() {
        return foreignUseCase;
    }
}
