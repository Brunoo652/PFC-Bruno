package com.afundacion.inazumawiki.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    public static void register(Context context, String email, String password) {
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
                        Log.d("POSTregister", "Respuesta exitosa: " + response.toString());
                        // La solicitud fue exitosa, muestra un toast de éxito
                        Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                        // Cambia a la actividad principal (MainActivity)
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("POSTregister", "Error en la solicitud: " + error.toString());
                        // Muestra el error en el registro (log)
                        Log.e("RegistroError", "Error al registrar el usuario", error);
                        // Hubo un error en la solicitud, muestra un toast de error
                        // Hubo un error en la solicitud, muestra un toast de error
                        Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agrega la solicitud a la cola
        requestQueue.add(request);
    }
}


