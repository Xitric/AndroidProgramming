package dk.sdu.threadingexercise.chucknorris;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class Joke {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("joke")
    @Expose
    private String joke;

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }


    public static class ResponseDeserializer implements JsonDeserializer<Joke> {
        @Override
        public Joke deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonElement content = json.getAsJsonObject().get("value");
            return new Gson().fromJson(content, Joke.class);
        }
    }
}
