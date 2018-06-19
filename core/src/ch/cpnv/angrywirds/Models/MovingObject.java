package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {

    public final static float G = 3000.00f;
    protected Vector2 vector, startpositions;

    public MovingObject(float positionX, float positionY, float width, float height, String texturepath) {
        super(positionX, positionY, width, height, texturepath);
        startpositions = new Vector2(positionX, positionY);
        this.vector = new Vector2();
    }

    @Override
    public void reset(){
        this.sprite.setPosition(startpositions.x, startpositions.y);
    }

    public final void move(float dt){
        this.sprite.setPosition(this.sprite.getX() + this.vector.x * dt, this.sprite.getY() + this.vector.y * dt);
    }
    public abstract void accelerate(float dt);

}
