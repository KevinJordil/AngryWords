package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.angrywirds.Controllers.GameActivityManager;
import ch.cpnv.angrywirds.Models.Button;
import ch.cpnv.angrywirds.Models.Font;
import ch.cpnv.angrywirds.Models.Scene;
import ch.cpnv.angrywirds.Models.Words;

public class Welcome extends GameActivity {

    Sprite background;
    public static Scene scene = new Scene();
    static BitmapFont font;
    Button play;


    public Welcome(){
        this.create();
    }

    @Override
    public void create () {

        super.create();

        // Background
        background = new Sprite(new Texture("background.jpg"));
        scene.addbacksprite(background);

        // Font
        font = Font.get(400, Color.BLACK, "angrybirds.ttf");

        // Play button
        play = new Button(950, 280, 520, 320, "play.png");
        scene.addbutton(play);
    }

    @Override
    public void render () {
        batch.begin();
        scene.draw(batch);
        glyphLayout.setText(font, "AngryWirds");
        font.draw(batch, glyphLayout, 200, 1000);
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
        Vector3 positions = new Vector3(camera.unproject(new Vector3(screenX, screenY, 0)));

        if (play.getHitBox().contains(positions.x, positions.y)){
            GameActivityManager.push(new Play());
        }





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
