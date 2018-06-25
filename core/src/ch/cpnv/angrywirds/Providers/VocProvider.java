package ch.cpnv.angrywirds.Providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Models.Homework;
import ch.cpnv.angrywirds.Models.Language;
import ch.cpnv.angrywirds.Models.PostAssignmentsDatas;
import ch.cpnv.angrywirds.Models.Vocabulary;
import ch.cpnv.angrywirds.Models.Word;

import org.json.JSONObject;

public abstract class VocProvider {
    private static final String API = "http://voxerver.mycpnv.ch/api/v1/";
    private static final String TOKEN = "*69E37CEBA91FA05A4C21861CA2B5A848772E1568";

    public enum Status { unknown, ready, cancelled, nocnx, error500, error403, ok }
    public static Status status = Status.unknown;

    public static ArrayList<Language> languages;
    public static ArrayList<Vocabulary> vocabularies;
    public static ArrayList<Homework> homeworks;

    static public void load() {
        languages = new ArrayList<Language>();
        vocabularies = new ArrayList<Vocabulary>();
        homeworks = new ArrayList<Homework>();

        HttpRequestBuilder requestLangues = new HttpRequestBuilder();
        Net.HttpRequest httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"languages").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jsonlangue = new JsonReader();
                JsonValue baselangue = jsonlangue.parse(httpResponse.getResultAsString());
                for (JsonValue langages : baselangue.iterator())
                {
                    languages.add(new Language(langages.getInt("id"),langages.getString("lName")));
                }
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("VOCPROVIDER", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("VOCPROVIDER", "cancelled");
            }
        });

        httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"fullvocs").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jreader = new JsonReader();
                JsonValue vocs = jreader.parse(httpResponse.getResultAsString());
                for (JsonValue voc : vocs.iterator())
                {
                    Vocabulary newvoc = new Vocabulary(voc.getInt("mId"),voc.getString("mTitle"),voc.getInt("mLang1"),voc.getInt("mLang2"));
                    for (JsonValue word : voc.get("Words").iterator())
                    {
                        newvoc.addWord(new Word(word.getInt("mId"), word.getString("mValue1"), word.getString("mValue2")));
                    }
                    vocabularies.add(newvoc);
                }
                status = Status.ready;
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("VOCPROVIDER", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("VOCPROVIDER", "cancelled");
            }
        });

        HttpRequestBuilder request = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = request.newRequest().method(Net.HttpMethods.GET).url(API+"assignments/"+TOKEN).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if(httpResponse.getStatus().getStatusCode() == 403){
                    status = Status.error403;
                }
                else if(httpResponse.getStatus().getStatusCode() == 500){
                    status = Status.error500;
                }
                else{
                    JsonReader json = new JsonReader();
                    JsonValue base = json.parse(httpResponse.getResultAsString());
                    for (JsonValue element : base.iterator())
                    {
                        homeworks.add(new Homework(element.getInt("assignment_id"), element.getInt("vocabulary_id"), element.getString("title"), element.getString("result")));
                    }
                }

            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("VOCPROVIDER", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("VOCPROVIDER", "cancelled");
            }
        });

    }

    static public void submitResults (int assignement, int score) {
        Gdx.app.log("AJAXPOST", "Appel ajax demandé");
        HttpRequestBuilder requestSubmitResults = new HttpRequestBuilder();
        PostAssignmentsDatas datas = new PostAssignmentsDatas(assignement, TOKEN, score);
        //String content = "{\"id\":\""+assignement+"\",\"result\":\""+score+"\",\"token\":\""+TOKEN+"\"}";
        JSONObject json = new JSONObject();
        json.put("id", String.valueOf(assignement));
        json.put("result", String.valueOf(score));
        json.put("token", TOKEN);
        String content = json.toString();
        Net.HttpRequest httpRequestSubmitResults = requestSubmitResults.newRequest()
                .header("X-Requested-With", "XmlHttpRequest")
                .header("Content-Type", "application/json")
                .method(Net.HttpMethods.POST)
                .content(content)
                .url(API+"result")
                .build();
        Gdx.app.log("AJAXPOST", httpRequestSubmitResults.getContent());
        Gdx.app.log("AJAXPOST", httpRequestSubmitResults.getHeaders().toString());
        Gdx.net.sendHttpRequest(httpRequestSubmitResults, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("AJAXPOST", "Soumission des résultats");
                Gdx.app.log("AJAXPOST", "status "+ httpResponse.getStatus().getStatusCode());
                status = Status.ok;
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("AJAXPOST", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("AJAXPOST", "cancelled");
            }
        });
    }

}