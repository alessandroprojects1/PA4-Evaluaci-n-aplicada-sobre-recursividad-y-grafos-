package ordenamiento;

import java.util.Arrays;

public class MergeSortDistancias {

    // Método recursivo sort (Divide)
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Dividir recursivamente la mitad izquierda y la mitad derecha
            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            // Combinar las dos mitades ya ordenadas
            merge(arr, left, mid, right);
        }
    }

    // Método merge (Combina / Conquista)
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static String obtenerUbicacion(int distancia) {
        switch (distancia) {
            case 50:  return "Biblioteca";
            case 70:  return "Cafetería";
            case 90:  return "Laboratorio";
            case 130: return "Patio";
            case 165: return "Auditorio";
            default:  return "Desconocido";
        }
    }

    public static void mostrarConUbicaciones(int[] arr) {
        for (int d : arr) {
            System.out.println(" - " + d + " m (" + obtenerUbicacion(d) + ")");
        }
    }

    public static void main(String[] args) {
        // Distancias desde la Puerta que llegan desordenadas:
        // {165, 50, 130, 70, 90} (Auditorio, Biblioteca, Patio, Cafetería, Laboratorio)
        int[] distancias = {165, 50, 130, 70, 90};

        System.out.println("Distancias originales desde la Puerta (desordenadas): " + Arrays.toString(distancias));
        mostrarConUbicaciones(distancias);

        sort(distancias, 0, distancias.length - 1);

        System.out.println("\nDistancias ordenadas con Merge Sort (menor a mayor): " + Arrays.toString(distancias));
        mostrarConUbicaciones(distancias);
    }
}
