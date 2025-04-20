
package com.clima.servicio;

import com.clima.modelo.Clima;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServicioClima {
    public static final String API_KEY = "API_KEY";
    public static final String URL_API = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s&lang=es";

    public Clima obtenerClima(String ciudad) throws Exception {
        String ciudadCodificada = URLEncoder.encode(ciudad, "UTF-8");
        String urlString = String.format(URL_API, ciudadCodificada, API_KEY);
        URL url = new URL(urlString);

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        if (conexion.getResponseCode() != 200) {
            throw new RuntimeException("No se pudo obtener el clima. Código: " + conexion.getResponseCode());
        }

        JsonObject json = JsonParser.parseReader(new InputStreamReader(conexion.getInputStream())).getAsJsonObject();

        String nombreCiudad = json.get("name").getAsString();
        double temperatura = json.getAsJsonObject("main").get("temp").getAsDouble();
        String descripcion = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        return new Clima(nombreCiudad, temperatura, descripcion);
    }
}
