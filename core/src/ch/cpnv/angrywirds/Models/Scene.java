package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.cpnv.angrywirds.Activities.GameActivity;

public class Scene {
    private ArrayList<PhysicalObject> physicalObjects = new ArrayList<PhysicalObject>();
    private ArrayList<Sprite> backsprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> frontsprites = new ArrayList<Sprite>();
    private ArrayList<Button> buttons = new ArrayList<Button>();


    private Random rand = new Random();
    public ArrayList<Word> listwords;
    public Words words;

    public Scene() {
    }

    public void setWords(Vocabulary voc){
        this.listwords = voc.words;

    }

    public void setphysicalobjects(ArrayList<PhysicalObject> physicalObjects) {
        this.physicalObjects = physicalObjects;
    }

    public void addphysicalobject(PhysicalObject el) throws Exception {
        Rectangle newelrect = el.getHitBox();
        for (PhysicalObject o : physicalObjects)
            if (newelrect.overlaps(o.getHitBox()))
                throw new Exception("Error, overlaps");
        physicalObjects.add(el);
    }

    public void addMovingObject(PhysicalObject el){
        physicalObjects.add(el);
    }

    public void addbacksprite(Sprite el) {
        this.backsprites.add(el);
    }

    public void addfrontsprite(Sprite el) {
        this.frontsprites.add(el);
    }

    public void removebacksprite(Sprite el) {
        this.backsprites.remove(el);
    }

    public void removefrontsprite(Sprite el) {
        this.frontsprites.remove(el);
    }

    public void addbutton(Button el) {
        this.buttons.add(el);
    }


    public void elementtouchdown(float x, float y){
        for(PhysicalObject physicalObject : this.physicalObjects){
            if (physicalObject.getHitBox().contains(x, y)){
                physicalObject.touchdown();
            }
        }
    }

    public void elementtouchup(float x, float y){
        for(PhysicalObject physicalObject : this.physicalObjects){
            if (physicalObject.getHitBox().contains(x, y)){
                physicalObject.touchup();
            }
        }
    }

    public void touchup(){
        for(PhysicalObject physicalObject : this.physicalObjects){
            physicalObject.generaltouchup();
        }
    }

    public void reset(){
        for(PhysicalObject physicalObject : this.physicalObjects){
            physicalObject.reset();
        }
    }

    public void collision(PhysicalObject element){
        for(PhysicalObject physicalObject : this.physicalObjects){
            if (physicalObject.getHitBox().overlaps(element.getHitBox()) && element != physicalObject ){
                physicalObject.collision();
                element.collision();
            }
        }
    }

    public void draw(SpriteBatch spriteBatch){
        for(Sprite backsprite : this.backsprites){
            backsprite.draw(spriteBatch);
        }
        for(PhysicalObject physicalObject : this.physicalObjects){
            physicalObject.draw(spriteBatch);
        }
        for(Sprite frontsprite : this.frontsprites){
            frontsprite.draw(spriteBatch);
        }
        for(Button button : this.buttons){
            button.draw(spriteBatch);
        }
    }

    public void randomgenerate(){
        // Copy object other use as reference
        ArrayList<Word> clone = new ArrayList<Word>(listwords.size());
        for (Word item : listwords) clone.add(item);
        words = new Words(clone, 4);


        //Box
        for(int i=1; i<=6; i++){
            Integer x = (rand.nextInt((int)GameActivity.WORLD_WIDTH/100-8)+8)*100;
            Integer y = (rand.nextInt((int)GameActivity.WORLD_HEIGHT/250-2)+2);
            for(int j=2; j<=y; j++){
                try{
                    this.addphysicalobject(new PhysicalObject(x, j*100, 100, 100));
                } catch (Exception e) {
                    Gdx.app.log("SCENEERROR", e.getMessage());
                }
            }
        }

        //Tnt
        for(int i=1; i<=3; i++){
            Integer x = (rand.nextInt((int)GameActivity.WORLD_WIDTH/100-8)+8)*100;
            Integer y = (rand.nextInt((int)GameActivity.WORLD_HEIGHT/250-2)+2);
            try{
                this.addphysicalobject(new Tnt(x, y*100, 100, 100, 20));
            } catch (Exception e) {
                i--;
                Gdx.app.log("SCENEERROR", e.getMessage());
            }
            for(int j=2; j<=y; j++){
                try{
                    this.addphysicalobject(new PhysicalObject(x, j*100, 100, 100));
                } catch (Exception e) {
                    Gdx.app.log("SCENEERROR", e.getMessage());
                }
            }
        }


        //Pig
        List<String> xpigs = new ArrayList<String>();
        for(int i=0; i<=3; i++){

            Integer x;
            do {
                x = (rand.nextInt((int)GameActivity.WORLD_WIDTH/100-12)+12)*100;
            }while(xpigs.contains(x.toString()));
            xpigs.add(x.toString());

            Integer y = (rand.nextInt((int)GameActivity.WORLD_HEIGHT/200-4)+4);
            try{
                this.addphysicalobject(new Pig(x, y*100, 100, 100, words.words.get(i).value2));
            } catch (Exception e) {
                i--;
                Gdx.app.log("SCENEERROR", e.getMessage());
            }
            for(int j=2; j<=y; j++) {
                try{
                    this.addphysicalobject(new PhysicalObject(x, j*100, 100, 100));
                } catch (Exception e) {
                    Gdx.app.log("SCENEERROR", e.getMessage());
                }
            }
        }
    }

}

