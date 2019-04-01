package dk.sdu.threadingexercise.chucknorris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Joke.class, new Joke.ResponseDeserializer())
                    .create();

            instance = new Retrofit.Builder()
                    .baseUrl("http://api.icndb.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return instance;
    }

    public static ChuckNorrisService getChuckNorrisService() {
        return getInstance().create(ChuckNorrisService.class);
    }
}
