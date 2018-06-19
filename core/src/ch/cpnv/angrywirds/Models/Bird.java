package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;



public final class Bird extends MovingObject {

    public enum statelist { cantouch, movable, cannottouch }
    public statelist state = statelist.cantouch;
    private Rubberband rubberband = new Rubberband(startpositions);

    Texture arrowtext = new Texture("arrow.png");;


    public Bird(float positionX, float positionY, float width, float height, String texturePath) {
        super(positionX, positionY, width, height, texturePath);
    }

    public Bird(float positionX, float positionY, float width, float height) {
        super(positionX, positionY, width, height, "bird.png");

    }

    public void fly(Vector2 touchDown, Vector2 touchUp){
        if (touchUp.x < startpositions.x && touchUp.y < startpositions.y){
            this.setVector(new Vector2((touchDown.x - touchUp.x)*10, (touchDown.y - touchUp.y)*10));
        }
        else{
            this.reset();
        }
    }

    public boolean canfly(){
        return (this.getPositionX() > 0 && this.getPositionY() > 199  || this.getPositionX() < 270);
    }

    @Override
    public void touchup(){
        if (state.equals(statelist.cantouch)){
            this.state = statelist.movable;
            rubberband.reset();
        }
    }

    @Override
    public void collision(){
        this.state = statelist.cannottouch;
    }

    public void dragged(float positionx, float positiony){
        this.setPositionX(positionx - this.sprite.getWidth() / 2);
        this.setPositionY(positiony - this.sprite.getWidth() / 2);
        rubberband.dragged(positionx, positiony);
    }

    public void reset(){
        super.reset();
        rubberband.reset();
        this.state = statelist.cantouch;
    }

    public void accelerate(float dt){
        vector.y -= MovingObject.G * dt;
    }

    private void setVector(Vector2 vector){
        this.vector = vector;
    }

}
