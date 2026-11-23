/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad1;

/**
 *
 * @author melch
 */
public class Actividad1 {

    static int buscar(int[] distancias, int objetivo, int inicio, int fin) {
        if (inicio > fin) {
            return -1;                     
        }
        int medio = (inicio + fin) / 2;
        if (distancias[medio] == objetivo) {
            return medio;  
        }
        if (objetivo < distancias[medio]) {
            return buscar(distancias, objetivo, inicio, medio - 1); 
        }
        return buscar(distancias, objetivo, medio + 1, fin);        
    }

    public static void main(String[] args) {
        
        String[] ubicaciones = {"Biblioteca", "Cafetería", "Laboratorio", "Patio", "Auditorio"};
        int[] distancias = {50, 70, 90, 130, 165};

        int[] consultas = {130, 100};
        for (int d : consultas) {
            int pos = buscar(distancias, d, 0, distancias.length - 1);
            if (pos != -1) {
                System.out.println(d + " m -> " + ubicaciones[pos]);
            } else {
                System.out.println(d + " m -> No hay ubicación a esa distancia");
            }
        }
    }
}

