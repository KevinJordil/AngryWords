package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;

public class DirectionArrow {


    private Sprite arrows;
    private Vector2 startarrows = new Vector2();

    public DirectionArrow(Vector2 startpositions){
        Texture arrowtext = new Texture("arrow.png");
        arrowtext.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        arrows = new Sprite(arrowtext);
        startarrows.set(startpositions.x+15, startpositions.y+40);
        arrows.setBounds(startarrows.x, startarrows.y, 0, 0);
        Play.scene.addfrontsprite(arrows);
    }

    public void reset(){
        arrows.setSize(0, 0);
    }

    public void dragged(float positionx, float positiony){
        Vector2 diff = new Vector2(positionx, positiony).sub(new Vector2(startarrows.x, startarrows.y));
        arrows.setSize((float)Math.sqrt(Math.pow(startarrows.x - positionx, 2)+Math.pow(startarrows.y - positiony, 2))+40, 40);
        arrows.setRotation(diff.angle()+180);
    }


}
