package com.afundacion.inazumawiki.main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONObject;
import org.json.JSONException;

public class GETRandomPlayer {

    private static final String BASE_URL = "http://192.168.68.140:8080/api/jugador/";
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private Context context;

    public GETRandomPlayer(Context context) {
        this.context = context;
    }

    public interface RandomPlayerCallback {
        void onRandomPlayerReceived(Intent intent);
    }

    public void getRandomId(RandomPlayerCallback callback) {
        Random random = new Random();
        int jugadorId = random.nextInt(1598) + 1; // Número aleatorio entre 1 y 1598

        // Pasamos al método getJugadorPorRandomId
        getJugadorPorRandomID(jugadorId, callback);
    }

    public void getJugadorPorRandomID(int jugadorId, RandomPlayerCallback callback) {
        String url = BASE_URL + jugadorId;

        executorService.execute(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData); // Convierte la cadena en un objeto JSON
                    Log.d("GETRandomPlayer", "Respuesta del servidor: " + jsonData);

                    // Crear un archivo JSON para guardar los datos
                    String filename = "jugador_data.json";
                    saveDataToJsonFile(filename, jsonObject);

                    // Crea un Intent y agrega los datos como extras
                    Intent intent = new Intent(context, DetalleJugadorActivity.class);
                    intent.putExtra("nombre", jsonObject.getString("nombre"));
                    intent.putExtra("descripcion", jsonObject.getString("descripcion"));
                    intent.putExtra("sexo", jsonObject.getString("sexo"));
                    intent.putExtra("afinidad", jsonObject.getString("afinidad"));
                    intent.putExtra("posicion", jsonObject.getString("posicion"));
                    intent.putExtra("sprite", jsonObject.getString("sprite"));

                    handler.post(() -> callback.onRandomPlayerReceived(intent));
                } else {
                    handler.post(() -> callback.onRandomPlayerReceived(null));
                }
            } catch (IOException | JSONException e) {
                Log.e("GETRandomPlayer", "Error al realizar la solicitud GET", e);
                handler.post(() -> callback.onRandomPlayerReceived(null));
            }
        });
    }

    private void saveDataToJsonFile(String filename, JSONObject jsonObject) {
        // Implementa la lógica para guardar los datos en un archivo JSON como se mostró en la respuesta anterior.
    }
}


