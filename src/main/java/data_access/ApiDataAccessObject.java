package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ApiDataAccessObject {
    public final String Url = "https://api.edamam.com";
    public final String Key = System.getenv("Key");
    public final String Id = System.getenv("Id");
    private static final String MESSAGE = "message";

    public JSONObject getRecipebyName(String recipeName) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/api/recipes/v2?type=public&q=%s&app_id=%s&app_key=%s", Url, recipeName, Id, Key))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody;
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }

        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
