package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.angrywirds.Controllers.GameActivityManager;
import ch.cpnv.angrywirds.Models.Button;
import ch.cpnv.angrywirds.Models.Font;
import ch.cpnv.angrywirds.Models.Scene;
import ch.cpnv.angrywirds.Models.TextButton;
import ch.cpnv.angrywirds.Providers.VocProvider;

public class Welcome extends GameActivity {

    Sprite background;
    public static Scene scene = new Scene();
    static BitmapFont titlefont, textfont;
    Button play;
    boolean clickplay = false;


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
        titlefont = Font.get(400, Color.BLACK, "angrybirds.ttf");
        textfont = Font.get(200, Color.BLACK, "angrybirds.ttf");

        // Play button
        play = new Button(950, 280, 520, 320, "play.png");

        // Voc
        VocProvider.load();

    }

    @Override
    public void render () {
        batch.begin();
        scene.draw(batch);
        glyphLayout.setText(titlefont, "AngryWirds");
        titlefont.draw(batch, glyphLayout, 200, 1200);

        if(VocProvider.status == VocProvider.Status.ready){
            play.draw(batch);
        }
        else if (VocProvider.status == VocProvider.Status.error500){
            glyphLayout.setText(textfont, "Erreur ... (500)");
            textfont.draw(batch, glyphLayout, 500, 500);
        }
        else if (VocProvider.status == VocProvider.Status.error403){
            glyphLayout.setText(textfont, "Erreur ... (403)");
            textfont.draw(batch, glyphLayout, 500, 500);
        }
        else{
            glyphLayout.setText(textfont, "Chargement ...  "+VocProvider.status);
            textfont.draw(batch, glyphLayout, 500, 500);
        }

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

        if (play.getHitBox().contains(positions.x, positions.y) && VocProvider.status == VocProvider.Status.ready){
            GameActivityManager.push(new VocSelect());
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
