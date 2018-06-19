package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.Gdx;

import ch.cpnv.angrywirds.Activities.GameActivity;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Controllers.GameActivityManager;

public final class Tnt extends PhysicalObject {
    private int negativePoints;

    public Tnt(float positionX, float positionY, float width, float height, String texturePath, int negativePoints) {
        super(positionX, positionY, width, height, texturePath);
        this.negativePoints = negativePoints;
    }

    public Tnt(float positionX, float positionY, float width, float height, int negativePoints) {
        super(positionX, positionY, width, height, "tnt.png");
        this.negativePoints = negativePoints;
    }

    @Override
    public void collision(){
        GameActivityManager.points -= negativePoints;
        Play.maintext = "Tnt";
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }
}
