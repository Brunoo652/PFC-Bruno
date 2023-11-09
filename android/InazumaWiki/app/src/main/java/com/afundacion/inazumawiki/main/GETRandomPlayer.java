package com.afundacion.inazumawiki.main;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GETRandomPlayer {

    private static final String BASE_URL = "http://192.168.68.140:8080/api/jugador/";
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public interface RandomPlayerCallback {
        void onRandomPlayerReceived(String jsonData);
    }

    public static void getRandomPlayer(RandomPlayerCallback callback) {
        Random random = new Random();
        int jugadorId = random.nextInt(1598) + 1; // Número aleatorio entre 1 y 1598

        String url = BASE_URL + jugadorId;

        executorService.execute(() -> {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String jsonDataRandom = response.body().string();
                    Log.d("GETRandomPlayer", "Respuesta del servidor: " + jsonDataRandom);
                    System.out.println("Solicitud correcta, datos: "+ jsonDataRandom);
                    handler.post(() -> callback.onRandomPlayerReceived(jsonDataRandom));
                } else {
                    handler.post(() -> callback.onRandomPlayerReceived(null));
                }
            } catch (IOException e) {
                Log.e("GETRandomPlayer", "Error al realizar la solicitud GET", e);
                System.out.println("La solicitud ha fallado");
                handler.post(() -> callback.onRandomPlayerReceived(null));
            }
        });
    }
}