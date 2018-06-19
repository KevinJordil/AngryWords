package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.angrywirds.Activities.GameActivity;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Controllers.GameActivityManager;

public final class Pig extends PhysicalObject {

    public static final String PIG_TEXTURE = "pig.png";
    public static final String BACKGROUND_MESSAGE_TEXTURE = "bubble.png";

    private String message;
    private BitmapFont text;
    private Sprite textBackground;
    private boolean display;
    private GlyphLayout glyphLayout = new GlyphLayout();

    public Pig(float positionX, float positionY, float width, float height, String texturePath, String message) {
        super(positionX, positionY, width, height, texturePath);
        this.message = message;
        GenericConstruct(positionX, positionY);
    }

    public Pig(float positionX, float positionY, float width, float height, String message) {
        super(positionX, positionY, width, height, PIG_TEXTURE);
        this.message = message;
        GenericConstruct(positionX, positionY);
    }

    private void GenericConstruct(float positionX, float positionY){
        this.textBackground = new Sprite(new Texture(BACKGROUND_MESSAGE_TEXTURE));
        this.textBackground.setBounds(positionX-270,positionY+100,450, 300);
        text = Font.get(90, Color.BLACK, "angrybirds.ttf");
    }

    @Override
    public void touchdown(){
        displayMessage();
    }

    @Override
    public void touchup(){
        hideMessage();
    }

    @Override
    public void generaltouchup(){
        hideMessage();
    }



    @Override
    public void collision(){
        if (Play.scene.words[Play.scene.wordsindex].checkWord(getMessage())){
            GameActivityManager.points += 5;
            Play.maintext = "Réussi";
        }
        else{
            GameActivityManager.points -= 3;
            Play.maintext = "Raté ...";
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        this.sprite.draw(spriteBatch);
        if (display){
            textBackground.draw(spriteBatch);
            glyphLayout.setText(text, message);
            text.draw(spriteBatch, glyphLayout, this.getPositionX()-210, this.getPositionY()+310);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void displayMessage(){
        display = true;
    }

    public void hideMessage(){
        display = false;
    }
}
