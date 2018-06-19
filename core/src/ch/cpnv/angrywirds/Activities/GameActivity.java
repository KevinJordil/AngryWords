package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class GameActivity extends Game implements InputProcessor {

    public static final float WORLD_WIDTH = 2560f;
    public static final float WORLD_HEIGHT = 1440f;
    public static OrthographicCamera camera;
    public static SpriteBatch batch;
    GlyphLayout glyphLayout = new GlyphLayout();

    @Override
    public void create () {

        Gdx.input.setInputProcessor(this);

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WORLD_WIDTH,WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();

        // Batch
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void dispose () {
        batch.dispose();
    }


}
