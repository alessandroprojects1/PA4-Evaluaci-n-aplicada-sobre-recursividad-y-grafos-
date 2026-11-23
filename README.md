# PA4 - Evaluación aplicada sobre recursividad y grafos

> **Curso:** Algoritmo y Estructura de Datos Basados en Inteligencia Artificial
> **Código:** 30710
> **Evaluación:** PA4
> **Profesor:** Wilder Julio Espinoza Bravo
> **Video de exposición:** [PEGAR AQUÍ EL LINK DE YOUTUBE]

## Objetivo del trabajo

Modelar y resolver problemas de búsqueda, ordenamiento y navegación dentro de un campus académico aplicando recursividad, la estrategia *divide y vencerás* (Merge Sort) y estructuras no lineales basadas en grafos.

## Integrantes

| Integrante | Rol | Aporte principal |
|---|---|---|
| [ALESSANDRO ISMAEL GALLO QUIROZ] | Desarrollador de Recursividad | Explicación de recursividad y *divide y vencerás*, búsqueda recursiva de ubicaciones en Java, caso base, caso recursivo y condición de finalización (Actividad 1). |
| [JOSE FERNANDO FRIAS DÍAZ] | Desarrollador de Ordenamiento Recursivo | Implementación de Merge Sort (`sort` y `merge`) para ordenar las distancias desde la Puerta, árbol de recursión paso a paso (división y combinación) y justificación del enfoque recursivo $O(n \log n)$ (Actividad 2). |
| [GIANFRANCO ARTURO BERNAL SARAVIA] | Analista de Grafos | Desarrollo y análisis de la Actividad 3, además de armado del informe final en PDF. |
| [YANHIRA LIZETH GUTIERREZ PÉREZ] | Analista de Recorridos y Comparación | Desarrollo y análisis de la Actividad 4. |
| [MARTHA SOFIA LOZANO PAREDES] | Analista de Conclusiones y Documentación | Conclusión grupal, gestión del repositorio en GitHub y README. |

## BASE COMÚN DEL PROYECTO (Alineación Grupal)

Para mantener la coherencia entre todas las actividades del caso del campus académico, se definieron los siguientes datos base:

### Ubicaciones del campus

```java
String[] ubicaciones = {"Puerta", "Biblioteca", "Cafetería", "Laboratorio", "Patio", "Auditorio"};
```

### Distancias desde la Puerta (en metros, llegan desordenadas)

| Ubicación | Distancia desde la Puerta |
|---|---|
| Auditorio | 165 m |
| Biblioteca | 50 m |
| Patio | 130 m |
| Cafetería | 70 m |
| Laboratorio | 90 m |

Arreglo inicial desordenado:

```java
int[] distancias = {165, 50, 130, 70, 90};
```

Arreglo final ordenado (de menor a mayor distancia desde la Puerta):

```text
[50, 70, 90, 130, 165] → (Biblioteca: 50 m, Cafetería: 70 m, Laboratorio: 90 m, Patio: 130 m, Auditorio: 165 m)
```

## Instrucciones de uso

1. Clonar el repositorio: `git clone https://github.com/alessandroprojects1/PA4-Evaluaci-n-aplicada-sobre-recursividad-y-grafos-.git`
2. Abrir la carpeta de la actividad correspondiente en un IDE de Java (NetBeans, IntelliJ IDEA o Eclipse).
3. Ejecutar la clase principal (por ejemplo, `MergeSortDistancias` en `Actividad2`).
4. La consola mostrará el arreglo original de distancias desde la Puerta y el arreglo ordenado mediante Merge Sort junto con sus ubicaciones.

--------------------------------------------------------------------------------------------------------------
## Actividad 1. Recursividad

--------------------------------------------------------------------------------------------------------------
## Actividad 2. Ordenamiento recursivo (Merge Sort)

### a. Funcionamiento de Merge Sort bajo el enfoque Divide y Vencerás

**Merge Sort** (ordenamiento por mezcla) es un algoritmo de ordenamiento recursivo basado en la estrategia **divide y vencerás** (*Divide and Conquer*). En el caso del campus académico, permite ordenar las distancias desde la Puerta que llegan desordenadas: `{165, 50, 130, 70, 90}`, correspondientes a `(Auditorio, Biblioteca, Patio, Cafetería, Laboratorio)`.

El algoritmo opera en tres etapas fundamentales a través de los métodos `sort` y `merge`:

- **Dividir (`sort`):** si el subarreglo tiene más de un elemento (`left < right`), se calcula el punto medio `mid = (left + right) / 2` y se divide recursivamente en dos mitades: izquierda (`left..mid`) y derecha (`mid + 1..right`), hasta llegar al **caso base** de subarreglos de 1 solo elemento (que por definición ya están ordenados).
- **Conquistar:** cada mitad se ordena de manera recursiva invocando `sort(arr, left, mid)` y `sort(arr, mid + 1, right)`.
- **Combinar (`merge`):** las dos subsecuencias ordenadas (`L` y `R`) se intercalan comparando sus elementos desde el inicio (`L[i] <= R[j]`) para formar un único arreglo ordenado de menor a mayor.

### Representación paso a paso: Cómo se divide y cómo se combina (Árbol de recursión)

Partiendo del arreglo inicial `{165, 50, 130, 70, 90}` (`left = 0`, `right = 4`), el proceso ocurre en dos fases:

**1. Fase de División (`sort`):**
1. El arreglo completo `[165, 50, 130, 70, 90]` se parte en `mid = 2` generando la mitad izquierda `[165, 50, 130]` (`0..2`) y la mitad derecha `[70, 90]` (`3..4`).
2. La mitad izquierda `[165, 50, 130]` se parte en `mid = 1` en `[165, 50]` (`0..1`) y `[130]` (`2..2`, caso base).
3. El subarreglo `[165, 50]` se parte en `mid = 0` en `[165]` (`0..0`, caso base) y `[50]` (`1..1`, caso base).
4. La mitad derecha `[70, 90]` se parte en `mid = 3` en `[70]` (`3..3`, caso base) y `[90]` (`4..4`, caso base).

**2. Fase de Combinación (`merge`):**
1. Se combinan `[165]` y `[50]`: como `50 < 165`, resulta **`[50, 165]`**.
2. Se combinan `[50, 165]` y `[130]`: se compara `50 <= 130` (entra `50`), luego `165 > 130` (entra `130`) y finalmente queda `165`, resultando **`[50, 130, 165]`**.
3. En la rama derecha se combinan `[70]` y `[90]`: como `70 <= 90`, resulta **`[70, 90]`**.
4. Finalmente se combinan las dos mitades ordenadas `[50, 130, 165]` y `[70, 90]`: entra `50`, luego `70`, luego `90`, y por último los restantes `130` y `165`, obteniendo el arreglo ordenado **`[50, 70, 90, 130, 165]`**.

```text
                       [165, 50, 130, 70, 90]
                         /                \
             (Divide)   /                  \   (Divide)
                       ▼                    ▼
              [165, 50, 130]             [70, 90]
                 /        \               /    \
                ▼          ▼             ▼      ▼
           [165, 50]     [130]         [70]    [90]
            /     \        │             \      /
           ▼       ▼       │              ▼    ▼
         [165]   [50]      │             [70, 90]  ◄── Merge(3..4)
            \     /        │                 │
             ▼   ▼         │                 │
           [50, 165]       │                 │     ◄── Merge(0..1)
                \         /                  │
                 ▼       ▼                   │
              [50, 130, 165]                 │     ◄── Merge(0..2)
                     \                      /
                      \                    /
                       ▼                  ▼
                      [50, 70, 90, 130, 165]       ◄── Merge final(0..4)
       (Biblioteca, Cafetería, Laboratorio, Patio, Auditorio)
```

### b. Implementación en Java (`sort` y `merge`)

La implementación completa se encuentra en la carpeta `Actividad2` (`MergeSortDistancias.java`), siguiendo la estructura vista en la sesión 12 con los métodos `sort` y `merge`:

```java
package ordenamiento;

import java.util.Arrays;

public class MergeSortDistancias {

    // Método recursivo sort (Divide)
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Dividir la mitad izquierda y la mitad derecha
            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            // Combinar las mitades ordenadas
            merge(arr, left, mid, right);
        }
    }

    // Método merge (Combina)
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

    public static void main(String[] args) {
        int[] distancias = {165, 50, 130, 70, 90};
        System.out.println("Original: " + Arrays.toString(distancias));

        sort(distancias, 0, distancias.length - 1);

        System.out.println("Ordenado: " + Arrays.toString(distancias));
    }
}
```

**Salida en consola:**

```text
Distancias originales desde la Puerta (desordenadas): [165, 50, 130, 70, 90]
 - 165 m (Auditorio)
 - 50 m (Biblioteca)
 - 130 m (Patio)
 - 70 m (Cafetería)
 - 90 m (Laboratorio)

Distancias ordenadas con Merge Sort (menor a mayor): [50, 70, 90, 130, 165]
 - 50 m (Biblioteca)
 - 70 m (Cafetería)
 - 90 m (Laboratorio)
 - 130 m (Patio)
 - 165 m (Auditorio)
```

### c. ¿Por qué sirve el enfoque recursivo en este caso?

El enfoque recursivo con **Merge Sort** es idóneo para ordenar las distancias de las ubicaciones del campus por tres razones técnicas:

- **Complejidad temporal garantizada $O(n \log n)$:** el árbol de recursión divide el arreglo a la mitad en cada nivel generando $\log_2(n)$ niveles de profundidad, y en cada nivel la operación `merge` recorre linealmente los $n$ elementos ($O(n)$). Esto garantiza un rendimiento $O(n \log n)$ tanto en el mejor como en el promedio y peor de los casos, superando a métodos cuadráticos $O(n^2)$ como Burbuja o Inserción cuando crece el número de ubicaciones.
- **Es un algoritmo de ordenamiento estable:** al utilizar la condición `L[i] <= R[j]` en el método `merge`, si dos ubicaciones del campus tuvieran exactamente la misma distancia desde la Puerta, Merge Sort conserva su orden relativo original de llegada sin intercambiarlas innecesariamente.
- **Claridad estructural mediante Divide y Vencerás:** descomponer recursivamente un arreglo desordenado hasta casos triviales de un elemento (`left == right`) simplifica el problema: el ordenamiento real recae únicamente en intercalar dos listas que ya están ordenadas (`merge`).

--------------------------------------------------------------------------------------------------------------
## Actividad 3. Grafos

--------------------------------------------------------------------------------------------------------------
## Actividad 4. Recorridos y conclusión
