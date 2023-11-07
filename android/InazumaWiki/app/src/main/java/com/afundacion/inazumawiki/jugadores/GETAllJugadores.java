package com.afundacion.inazumawiki.jugadores;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
public class GETAllJugadores {
    public static String obtenerTodosLosJugadores() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.68.140:8080/api/jugador?name=";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    return responseBody.string();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}