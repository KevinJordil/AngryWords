package ch.cpnv.angrywirds.Models;

import java.util.ArrayList;
import java.util.Random;

public class Vocabulary {
    public int id;
    public String vocName;
    int langprof;
    int langeleve;
    ArrayList<Word> words;
    Random alea;

    public Vocabulary(int id, String vocName, int langprof, int langeleve){
        this.id = id;
        this.vocName = vocName;
        this.langprof = langprof;
        this.langeleve = langeleve;
        this.words = new ArrayList<Word>();
    }

    public void addWord(Word w) {
        words.add(w);
    }

    public Word pickAWord() {
        return words.get(alea.nextInt(words.size()));
    }
}