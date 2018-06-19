package ch.cpnv.angrywirds.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public abstract class Font {

    public static BitmapFont get(int size, Color color, String fontpath){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "abcdefghijklmnopqrstuvwxyzàéèêëùABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontpath));

        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }

}
