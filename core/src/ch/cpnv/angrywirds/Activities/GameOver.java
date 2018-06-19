package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Controllers.GameActivityManager;
import ch.cpnv.angrywirds.Models.Font;
import ch.cpnv.angrywirds.Models.Scene;
import ch.cpnv.angrywirds.Models.Words;

public class GameOver extends GameActivity {

    Sprite background;
    public static Scene scene = new Scene();
    static BitmapFont font;


    public GameOver(){
        this.create();
    }

    @Override
    public void create () {

        super.create();

        try{
            batch.begin();
        }catch (Exception e){

        }

        // Background
        background = new Sprite(new Texture("background.jpg"));
        scene.addbacksprite(background);

        // Font
        font = Font.get(400, Color.RED, "angrybirds.ttf");
    }

    @Override
    public void render () {
        batch.begin();
        scene.draw(batch);
        glyphLayout.setText(font, "Game Over");
        font.draw(batch, glyphLayout, 400, 1100);
        glyphLayout.setText(font, String.valueOf(GameActivityManager.points));
        font.draw(batch, glyphLayout, 1000, 600);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        GameActivityManager.points = GameActivityManager.START_POINTS;
        GameActivityManager.time = GameActivityManager.START_TIME;
        GameActivityManager.pop();
        Gdx.input.setInputProcessor(GameActivityManager.activities.peek());


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
