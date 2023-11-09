package com.afundacion.inazumawiki.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class POSTlogin {

    public interface LoginCallback {
        void onLoginSuccess();

        void onEmailError();

        void onPasswordError();

        void onLoginError();
    }


    public static void continueLogin(Context context, String email, String password, final LoginCallback callback) {
        String serverUrl = "http://192.168.68.140:8080/api/usuarios/login?login="+ email;


        // Inicializa la cola de solicitudes HTTP
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Crea un objeto JSON con los datos del usuario
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("email", email);
            requestData.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Envia una solicitud POST al servidor
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, serverUrl, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Maneja la respuesta exitosa
                        if (response.optInt("status") == 200) {
                            callback.onLoginSuccess();
                        } else {
                            callback.onLoginError();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Maneja los errores de la solicitud
                        Log.e("LoginError", "Error al iniciar sesión", error);

                        if (error.networkResponse != null && error.networkResponse.statusCode == 401) {
                            callback.onPasswordError();
                        } else if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                            callback.onEmailError();
                        } else {
                            callback.onLoginError();
                        }
                    }
                }
        );

        // Agrega la solicitud a la cola
        requestQueue.add(request);
    }
}
