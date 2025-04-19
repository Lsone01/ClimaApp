package com.clima;

import com.clima.modelo.Clima;
import com.clima.servicio.ServicioClima;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Clima> historial = new ArrayList<>(); // Cambiar a una lista de Clima

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioClima servicio = new ServicioClima();

        while (true) {
            System.out.print("Introduce una ciudad (o escribe 'salir' para terminar): ");
            String ciudad = scanner.nextLine();

            if (ciudad.equalsIgnoreCase("salir")) {
                System.out.println("Programa finalizado.");
                break;
            }

            try {
                Clima clima = servicio.obtenerClima(ciudad);
                System.out.println("Ciudad: " + clima.getNombre());
                System.out.println("Temperatura: " + clima.getTemperatura() + " °C");
                System.out.println("Descripción: " + clima.getDescripcion());

                // Agregar el clima al historial
                historial.add(clima);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Guardar el historial en un archivo JSON
        try (FileWriter writer = new FileWriter("historial_clima.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(historial, writer);
            System.out.println("Historial guardado en historial_clima.json");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial.");
        }
    }
}