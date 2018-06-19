package ch.cpnv.angrywirds.Providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Models.Language;

public class Data {

    private static final String API = "http://voxerver.mycpnv.ch/api/v1/";

    public enum Status { unknown, ready, cancelled, nocnx }
    public static Status status = Status.unknown;

    private static ArrayList<Language> languages;

    static public void load() {
        languages = new ArrayList<Language>();
        HttpRequestBuilder requestLangues = new HttpRequestBuilder();
        final Net.HttpRequest httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"languages").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jsonlangue = new JsonReader();
                JsonValue baselangue = jsonlangue.parse(httpResponse.getResultAsString());
                for (JsonValue langages : baselangue.iterator())
                {
                    languages.add(new Language(langages.getInt("lId"), langages.getString("lName")));
                    Gdx.app.log("ANGRY",langages.getString("lName"));
                }
                status = Status.ready;
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("ANGRY", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("ANGRY", "cancelled");
            }
        });
    }


}
