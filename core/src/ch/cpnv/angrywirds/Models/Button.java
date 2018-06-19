package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {

    public Sprite sprite;

    public Button(float positionX, float positionY, float width, float height, String texturePath) {
        this.sprite = new Sprite(new Texture(texturePath));
        this.sprite.setBounds(positionX,positionY,width,height);
    }

    public Rectangle getHitBox(){
        return new Rectangle((int)this.getPositionX(),(int)this.getPositionY(),(int)this.sprite.getWidth(),(int)this.sprite.getHeight());
    }

    public void draw(SpriteBatch spriteBatch){
        this.sprite.draw(spriteBatch);
    }

    public void touchdown(){  }

    public void touchup(){ }

    protected float getPositionX() { return sprite.getX();  }

    public void setPositionX(float positionX) {
        this.sprite.setX(positionX);
    }

    protected float getPositionY() {
        return sprite.getY();
    }

    public void setPositionY(float positionY) {
        this.sprite.setY(positionY);
    }
}
