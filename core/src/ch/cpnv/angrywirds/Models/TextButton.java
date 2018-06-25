package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TextButton {

    public Sprite background;
    private BitmapFont font;
    private GlyphLayout glyphLayout = new GlyphLayout();
    Integer movex, movey;
    String text;

    public TextButton(float positionX, float positionY, float width, float height, Integer movex, Integer movey, String text, Color color, Integer size, String background) {
        this.background = new Sprite(new Texture(background));
        this.background.setBounds(positionX,positionY,width,height);
        this.text = text;
        this.movex = movex;
        this.movey = movey;
        font = Font.get(size, color, "angrybirds.ttf");
    }

    public Rectangle getHitBox(){
        return new Rectangle((int)this.getPositionX(),(int)this.getPositionY(),(int)this.background.getWidth(),(int)this.background.getHeight());
    }

    public void draw(SpriteBatch spriteBatch){

        this.background.draw(spriteBatch);
        glyphLayout.setText(font, text);
        font.draw(spriteBatch, glyphLayout, this.getPositionX()+movex, this.getPositionY()+movey);
    }

    public void touchdown(){  }

    public void touchup(){ }

    protected float getPositionX() { return background.getX();  }

    public void setPositionX(float positionX) {
        this.background.setX(positionX);
    }

    protected float getPositionY() {
        return background.getY();
    }

    public void setPositionY(float positionY) {
        this.background.setY(positionY);
    }

}
