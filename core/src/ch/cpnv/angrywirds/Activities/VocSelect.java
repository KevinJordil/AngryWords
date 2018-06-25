package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Controllers.GameActivityManager;
import ch.cpnv.angrywirds.Models.Scene;
import ch.cpnv.angrywirds.Models.TextButton;
import ch.cpnv.angrywirds.Models.Vocabulary;
import ch.cpnv.angrywirds.Providers.VocProvider;

public class VocSelect extends GameActivity {


    Sprite background;
    public static Scene scene = new Scene();
    ArrayList<TextButton> vocsbutons = new ArrayList<TextButton>();

    public VocSelect(){
        this.create();
    }

    @Override
    public void create () {
        super.create();

        // Background
        background = new Sprite(new Texture("background.jpg"));
        scene.addbacksprite(background);

        for(int i=0; i<VocProvider.homeworks.size(); i++) {
            if(VocProvider.homeworks.get(i).result == null){
                vocsbutons.add(new TextButton(200, i*250+300, 2000, 200, 20, 160, VocProvider.homeworks.get(i).title, Color.WHITE, 150, "red.png"));
            }
            else {
                vocsbutons.add(new TextButton(200, i*250+300, 2000, 200, 20, 160, VocProvider.homeworks.get(i).title + " score : "+VocProvider.homeworks.get(i).result, Color.WHITE, 150, "green.png"));
            }

        }

    }

    @Override
    public void render () {
        batch.begin();
        scene.draw(batch);
        for(TextButton button : this.vocsbutons) {
            button.draw(batch);
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



        for(int i = 0; i < VocProvider.homeworks.size(); i++) {
            if (this.vocsbutons.get(i).getHitBox().contains(positions.x, positions.y)){
                GameActivityManager.VOC_ID = VocProvider.homeworks.get(i).vocId;
                GameActivityManager.ASSIGNMENTS_ID = VocProvider.homeworks.get(i).id;
                GameActivityManager.push(new Play());
            }
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
