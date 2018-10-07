package pl.goldy.danowski.fiszki.flashcards;

class Flashcard {

    private String polish, foreign, polishUseCase, foreignUseCase;
    private int taps;

    Flashcard(String polish, String foreign) {
        this.polish = polish;
        this.foreign = foreign;
        this.polishUseCase = "";
        this.foreignUseCase = "";
        resetTaps();
    }

    Flashcard(String polish, String foreign, String polishUseCase, String foreignUseCase) {
        this.polish = polish;
        this.foreign = foreign;
        this.polishUseCase = polishUseCase;
        this.foreignUseCase = foreignUseCase;
        resetTaps();
    }

    int getTaps() {
        return taps;
    }

    void resetTaps() {
        this.taps = 0;
    }

    void incrementTaps() {
        this.taps++;
    }

    String getPolish() {
        return polish;
    }

    String getForeign() {
        return foreign;
    }

    String getPolishUseCase() {
        return polishUseCase;
    }

    String getForeignUseCase() {
        return foreignUseCase;
    }
}
