package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ch.cpnv.angrywirds.Activities.GameActivity;
import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Controllers.GameActivityManager;

public final class Wasp extends MovingObject {

    Random alea = new Random();

    public Wasp(float positionX, float positionY, float width, float height, String texturePath) {
        super(positionX, positionY, width, height, texturePath);
    }

    public Wasp(float positionX, float positionY, float width, float height) {
        super(positionX, positionY, width, height, "wasp.png");
    }

    @Override
    public void collision(){

        GameActivityManager.push(new GameOver());
        Play.reset();

    }


    public void accelerate(float dt){
        if (this.sprite.getX() <= 100 || this.sprite.getY() <= 200 || this.sprite.getX() >= Play.WORLD_WIDTH || this.sprite.getY() >= Play.WORLD_HEIGHT) {
            this.vector.x = 0;
            this.vector.y = 0;
        }
        vector.x += (alea.nextFloat()-sprite.getX()/GameActivity.WORLD_WIDTH)*50;
        vector.y += (alea.nextFloat()-sprite.getY()/GameActivity.WORLD_HEIGHT)*50;
    }

}
