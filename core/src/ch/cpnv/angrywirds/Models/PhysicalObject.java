package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PhysicalObject {
    public Sprite sprite;

    public PhysicalObject(float positionX, float positionY, float width, float height, String texturePath) {
        this.sprite = new Sprite(new Texture(texturePath));
        this.sprite.setBounds(positionX,positionY,width,height);
    }

    public PhysicalObject(float positionX, float positionY, float width, float height) {
        this.sprite = new Sprite(new Texture("block.png"));
        this.sprite.setBounds(positionX,positionY,width,height);
    }

    public Rectangle getHitBox(){
        return new Rectangle((int)this.getPositionX(),(int)this.getPositionY(),(int)this.sprite.getWidth(),(int)this.sprite.getHeight());
    }


    public void touchdown(){

    }
    public void touchup(){ }
    public void generaltouchup(){ }
    public void reset() {}

    public void collision(){
        Gdx.app.log("COLLISION", "Box");
    }

    public float getPositionX() { return sprite.getX();  }

    public void setPositionX(float positionX) {
        this.sprite.setX(positionX);
    }

    public float getPositionY() {
        return sprite.getY();
    }

    public void setPositionY(float positionY) {
        this.sprite.setY(positionY);
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void draw(SpriteBatch spriteBatch){
        this.sprite.draw(spriteBatch);
    }

    @Override
    public String toString()
    {
        return (getClass().getSimpleName()+" at ("+this.sprite.getX()+","+this.sprite.getY()+")");
    }
}
