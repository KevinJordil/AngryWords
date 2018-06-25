package ch.cpnv.angrywirds.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Words {

    Random random = new Random();
    private String frenchword;
    private String rightword;
    public  ArrayList<Word> words;

    public Words(ArrayList<Word> words, Integer number) {

        this.words = words;
        Collections.shuffle(this.words);
        this.words.subList(number, words.size()).clear();

        Integer index = random.nextInt(this.words.size());
        this.frenchword = this.words.get(index).value1;
        this.rightword = this.words.get(index).value2;
    }

    public boolean checkWord(String word) {
        return word.equals(this.rightword);
    }

    public String getFrenchword() {
        return frenchword;
    }
}
