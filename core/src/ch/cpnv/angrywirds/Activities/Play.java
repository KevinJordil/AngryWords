package ch.cpnv.angrywirds.Activities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Controllers.GameActivityManager;
import ch.cpnv.angrywirds.Models.Bird;
import ch.cpnv.angrywirds.Models.Button;
import ch.cpnv.angrywirds.Models.Font;
import ch.cpnv.angrywirds.Models.PhysicalObject;
import ch.cpnv.angrywirds.Models.Scene;
import ch.cpnv.angrywirds.Models.Wasp;
import ch.cpnv.angrywirds.Models.Words;

public class Play extends GameActivity {

    public static String maintext = "";
    Sprite background, panel, frontslingshot, backslingshot;
    Button reset, reload;
    static Bird bird;
    static Wasp wasp;
    public static Scene scene = new Scene();
    static BitmapFont font;


    Vector2 touchDown, touchUp;


    public Play(){
        this.create();
    }

    @Override
    public void create () {
        super.create();


        // Scene
        scene.setWords(new Words[]{
                new Words("Bleu", "Blue", new String[] {"Blue", "Red", "Purple", "Yellow"}),
                new Words("Coeur", "Heart", new String[] {"Heart", "Heard", "Earthe", "Earth"}),
                new Words("Chaise", "Chair", new String[] {"Chair", "Share", "Chaire", "Sure"})
        });

        // Background
        background = new Sprite(new Texture("background.jpg"));
        scene.addbacksprite(background);

        // Backgroun Slingshot
        backslingshot = new Sprite(new Texture("backslingshot.png"));
        backslingshot.setBounds(280,190,130,350);
        scene.addbacksprite(backslingshot);

        // Front Slingshot
        frontslingshot = new Sprite(new Texture("frontslingshot.png"));
        frontslingshot.setBounds(280,190,130,350);
        scene.addfrontsprite(frontslingshot);

        // Panel
        panel = new Sprite(new Texture("panel.png"));
        panel.setBounds(100,1000,600,500);
        scene.addfrontsprite(panel);

        // Bird and Wasp
        bird = new Bird(290, 440, 100, 100);
        wasp = new Wasp(1000, 1200, 150, 150);

        // Reset
        this.reset = new Button(2100, 1220, 150, 150, "refresh.png");
        scene.addbutton(reset);

        // Reload
        this.reload = new Button(2300, 1220, 150, 150, "reload.png");
        scene.addbutton(reload);

        // Font
        font = Font.get(100, Color.BLACK, "angrybirds.ttf");

        // Scene
        scene.randomgenerate();
        scene.addMovingObject(wasp);
        scene.addMovingObject(bird);

    }

    private void update()
    {
        checkend();

        GameActivityManager.time -= Gdx.graphics.getDeltaTime();

        if ( bird.canfly()  && bird.state.equals(Bird.statelist.movable) ) {
            bird.accelerate(Gdx.graphics.getDeltaTime());
            bird.move(Gdx.graphics.getDeltaTime());
            scene.collision(bird);
        }
        wasp.accelerate(Gdx.graphics.getDeltaTime());
        wasp.move(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render () {
        batch.begin();
        //Scene
        scene.draw(batch);

        // Info
        glyphLayout.setText(font, maintext);
        font.draw(batch, glyphLayout, 200, 1150);

        // Points
        glyphLayout.setText(font, "Points : "+String.valueOf(GameActivityManager.points));
        font.draw(batch, glyphLayout, 800, 1200);

        // Words
        glyphLayout.setText(Play.font, scene.words[scene.wordsindex].getFrenchword());
        Play.font.draw(batch, glyphLayout , 200, 1275);

        // Update
        update();

        // Time
        glyphLayout.setText(font, "Temps restant : "+String.valueOf(Math.round(GameActivityManager.time)));
        font.draw(batch, glyphLayout, 800, 1320);

        batch.end();
    }

    public void checkend(){
        if (GameActivityManager.points >= 100 || GameActivityManager.points <= 0 || GameActivityManager.time <= 0){
            if (GameActivityManager.points <= 0){
                GameActivityManager.points = 0;
            }
            Gdx.app.log("SCORE", String.valueOf(GameActivityManager.points));
            GameActivityManager.push(new GameOver());
            Play.reset();
        }
    }

    @Override
    public void dispose () {
        super.dispose();
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
        Vector3 positions = new Vector3(camera.unproject(new Vector3(screenX, screenY, 0)));
        touchDown = new Vector2(positions.x, positions.y);

        scene.elementtouchdown(positions.x, positions.y);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 positions = new Vector3(camera.unproject(new Vector3(screenX, screenY, 0)));
        touchUp = new Vector2(positions.x, positions.y);

        if (bird.getHitBox().contains(positions.x, positions.y)){
            bird.fly(touchDown, touchUp);
        }
        scene.elementtouchup(positions.x, positions.y);
        scene.touchup();

        if (reset.getHitBox().contains(positions.x, positions.y)){
            reset();
        }
        else if (reload.getHitBox().contains(positions.x, positions.y)){
            reload();
        }

        return false;
    }

    public static void reset(){
        scene.reset();
        maintext = "";
        scene.setphysicalobjects(new ArrayList<PhysicalObject>());
        scene.randomgenerate();
        scene.addMovingObject(wasp);
        scene.addMovingObject(bird);
    }

    private void reload(){
        scene.reset();
        maintext = "";
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 positions = new Vector3(camera.unproject(new Vector3(screenX, screenY, 0)));

        if (bird.getHitBox().contains(positions.x, positions.y) && bird.state.equals(Bird.statelist.cantouch)){
            bird.dragged(positions.x, positions.y);
        }

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
