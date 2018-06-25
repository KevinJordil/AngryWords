package ch.cpnv.angrywirds.Models;

public class Homework {

    public Integer id, vocId;
    public String title, result;

    public Homework(Integer id, Integer vocId, String title, String result) {
        this.id = id;
        this.vocId = vocId;
        this.title = title;
        this.result = result;
    }
}
