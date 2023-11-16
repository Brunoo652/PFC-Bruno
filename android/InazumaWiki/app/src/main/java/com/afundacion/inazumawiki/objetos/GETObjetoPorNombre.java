package com.afundacion.inazumawiki.objetos;

import org.json.JSONArray;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GETObjetoPorNombre {

    public interface Callback {
        void onResponse(String result);
        void onError(Exception e);
    }

    public static JSONArray obtenerDatosObjeto(String nombreObjeto, GETObjetoPorNombre.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.68.140:8080/api/objeto?name=" + nombreObjeto;

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
