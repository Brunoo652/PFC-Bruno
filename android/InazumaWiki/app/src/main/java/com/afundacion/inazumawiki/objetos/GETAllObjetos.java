package com.afundacion.inazumawiki.objetos;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.afundacion.inazumawiki.st.GETAllSt;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GETAllObjetos {

    public interface Callback {
        void onResponse(String jsonResponse);
        void onError(Exception e);
    }

    public static void obtenerTodosLosObjetos(final GETAllObjetos.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.1.44:8080/api/objeto?name=";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String jsonData = response.body().string();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(jsonData);
                        }
                    });
                } else {
                    callback.onError(new Exception("Error en la solicitud"));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(e);
            }

        });
    }

}
