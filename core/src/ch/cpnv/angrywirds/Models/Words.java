package ch.cpnv.angrywirds.Models;

public class Words {

    private String frenchword;
    private String rightword;
    public String[] words;

    public Words(String frenchword, String rightword, String[] words) {
        this.frenchword = frenchword;
        this.rightword = rightword;
        this.words = words;
    }

    public boolean checkWord(String word) {
        return word.equals(this.rightword);
    }

    public String getFrenchword() {
        return frenchword;
    }
}
