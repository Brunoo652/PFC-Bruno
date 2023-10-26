package com.afundacion.inazumawiki.register;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckEmail {


    public interface EmailCheckCallback {
        void onEmailCheckComplete(boolean emailExists);
    }


    public static void isEmailAlreadyRegistered(Context context, String email, String password, EmailCheckCallback callback) {
        String serverUrl = "http://192.168.68.140:8080/api/usuarios/check-email?email=" + email;

        // Inicializa la cola de solicitudes HTTP
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Realiza una solicitud GET al servidor para verificar si el correo ya está registrado
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, serverUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null && response.has("emailExists")) {
                                boolean emailExists = response.getBoolean("emailExists");

                                if (emailExists) {
                                    // True, el correo existe
                                    Toast.makeText(context, "Este correo ya existe", Toast.LENGTH_SHORT).show();
                                } else {
                                    // False, el correo no existe, procede con el registro
                                    callback.onEmailCheckComplete(false);
                                }
                            } else {
                                // Respuesta inesperada del servidor, muestra un toast de error
                                Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Error al procesar la respuesta del servidor, muestra un toast de error
                            Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hubo un error en la solicitud, muestra un toast de error
                        Toast.makeText(context, "Error al verificar el correo", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agrega la solicitud a la cola
        requestQueue.add(request);
    }

}
