package com.afundacion.inazumawiki.jugadores;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;

public class GETJugadorPorNombre  {

    public interface Callback {
        void onResponse(String result);
        void onError(Exception e);
    }

    public static JSONArray obtenerDatosJugador(String nombreJugador,  Callback callback) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.68.140:8080/api/jugador?name=" + nombreJugador;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String jsonData = responseBody.string();
                    // Convierte la respuesta JSON en un JSONArray
                    return new JSONArray(jsonData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
