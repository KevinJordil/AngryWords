package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;

public class Rubberband {

    private Sprite frontrubberband, backrubberband;
    private Vector2 startfrontrubberband = new Vector2(), startbackrubberband = new Vector2();

    public Rubberband(Vector2 startpositions){
        frontrubberband = new Sprite(new Texture("rubberband.png"));
        startfrontrubberband.set(startpositions.x+15, startpositions.y+40);
        frontrubberband.setBounds(startfrontrubberband.x, startfrontrubberband.y, 0, 0);
        Play.scene.addfrontsprite(frontrubberband);

        backrubberband = new Sprite(new Texture("rubberband.png"));
        startbackrubberband.set(startpositions.x+95, startpositions.y+40);
        backrubberband.setBounds(startbackrubberband.x, startbackrubberband.y, 0, 0);
        Play.scene.addbacksprite(backrubberband);
    }

    public void reset(){
        frontrubberband.setSize(0, 0);
        backrubberband.setSize(0, 0);
    }

    public void dragged(float positionx, float positiony){
        Vector2 frontdiff = new Vector2(positionx, positiony).sub(new Vector2(startfrontrubberband.x, startfrontrubberband.y));
        frontrubberband.setSize((float)Math.sqrt(Math.pow(startfrontrubberband.x - positionx, 2)+Math.pow(startfrontrubberband.y - positiony, 2))+40, 20);
        frontrubberband.setRotation(frontdiff.angle());

        Vector2 backdiff = new Vector2(positionx, positiony).sub(new Vector2(startbackrubberband.x, startbackrubberband.y));
        backrubberband.setSize((float)Math.sqrt(Math.pow(startbackrubberband.x - positionx, 2)+Math.pow(startbackrubberband.y - positiony, 2))+40, 20);
        backrubberband.setRotation(backdiff.angle());
    }

}
