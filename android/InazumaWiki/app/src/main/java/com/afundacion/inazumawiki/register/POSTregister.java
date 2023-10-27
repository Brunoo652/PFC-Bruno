package com.afundacion.inazumawiki.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.afundacion.inazumawiki.main.MainActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class POSTregister {
    public static void register(Context context, String email, CheckEmail.EmailCheckCallback callback) throws InterruptedException {
        // Primero, verifica si el correo ya está registrado
        CheckEmail.isEmailAlreadyRegistered(context, email, callback);
    }


    public static void continueRegistration(Context context, String email, String password) throws InterruptedException {
        String serverUrl = "http://192.168.68.140:8080/api/usuarios/register";

        // Inicializa la cola de solicitudes HTTP
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Crea un objeto JSON con los datos del usuario
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("email", email);
            requestData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Envia una solicitud POST al servidor
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, serverUrl, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Usuario registrado con éxito
                        Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                        // Cambia a la actividad principal (MainActivity)
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error al registrar el usuario
                        Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agrega la solicitud a la cola
        requestQueue.add(request);

    }
}