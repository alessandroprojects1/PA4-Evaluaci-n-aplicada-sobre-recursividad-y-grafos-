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

## Actividad 1. Recursividad aplicada (Búsqueda Binaria Recursiva)

### a. ¿Qué es la recursividad y cómo se relaciona con Divide y Vencerás?

La **recursividad** es una técnica en la que una función se llama a sí misma para resolver una versión más pequeña del mismo problema, hasta llegar a un **caso base** que se resuelve directamente.

Se relaciona con la estrategia **divide y vencerás** porque cada llamada recursiva divide el problema en una parte menor del mismo tipo. En el caso del campus académico, se utiliza una **búsqueda binaria recursiva** para encontrar qué ubicación se encuentra a una distancia dada desde la Puerta. Trabaja sobre el arreglo ya ordenado por el Merge Sort de la Actividad 2: `{50, 70, 90, 130, 165}`, correspondientes a `(Biblioteca, Cafetería, Laboratorio, Patio, Auditorio)`.

El algoritmo opera mediante el método `buscar`, que recibe el arreglo, el valor objetivo y los límites `inicio` y `fin`:

- **Dividir:** se calcula el punto medio `medio = (inicio + fin) / 2` y se compara `distancias[medio]` con el objetivo.
- **Conquistar:** se descarta la mitad donde el valor no puede estar y se invoca recursivamente `buscar` sobre la otra mitad.
- **Combinar:** no hace falta. La respuesta de la llamada más interna es la respuesta final.

### Caso base, caso recursivo y condición de finalización

- **Caso base 1 (`inicio > fin`):** el rango quedó vacío, el valor no existe y retorna `-1`.
- **Caso base 2 (`distancias[medio] == objetivo`):** se encontró el valor y retorna su posición.
- **Caso recursivo:** si el objetivo es menor que `distancias[medio]`, se llama a `buscar` con la mitad izquierda (`inicio..medio-1`). Si es mayor, con la mitad derecha (`medio+1..fin`).
- **Condición de finalización:** en cada llamada el rango se reduce a la mitad y excluye la posición `medio`, por lo que siempre se llega a uno de los dos casos base y la recursión no puede ser infinita.

### Representación paso a paso (árbol de llamadas)

**Buscar 130 m** en `{50, 70, 90, 130, 165}`:

```text
buscar(inicio=0, fin=4)  → medio=2, valor 90
        130 > 90 → se descarta la mitad izquierda
              │
              ▼
buscar(inicio=3, fin=4)  → medio=3, valor 130
        130 == 130 → caso base 2
              │
              ▼
        retorna 3  →  Patio
```

**Buscar 100 m** (valor inexistente):

```text
buscar(inicio=0, fin=4)  → medio=2, valor 90
        100 > 90 → mitad derecha
              │
              ▼
buscar(inicio=3, fin=4)  → medio=3, valor 130
        100 < 130 → mitad izquierda
              │
              ▼
buscar(inicio=3, fin=2)  → inicio > fin → caso base 1
              │
              ▼
        retorna -1  →  No hay ubicación a esa distancia
```

### b. Implementación en Java (`buscar`)

La implementación completa se encuentra en la carpeta `Actividad1` (`Actividad1.java`):

```java
package actividad1;

public class Actividad1 {

    // Método recursivo buscar (búsqueda binaria)
    static int buscar(int[] distancias, int objetivo, int inicio, int fin) {
        if (inicio > fin) {
            return -1;                       // caso base 1: el valor no existe
        }
        int medio = (inicio + fin) / 2;
        if (distancias[medio] == objetivo) {
            return medio;                    // caso base 2: valor encontrado
        }
        if (objetivo < distancias[medio]) {
            return buscar(distancias, objetivo, inicio, medio - 1);  // caso recursivo: mitad izquierda
        }
        return buscar(distancias, objetivo, medio + 1, fin);         // caso recursivo: mitad derecha
    }

    public static void main(String[] args) {

        // Resultado ordenado de la Actividad 2 (Merge Sort)
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
```

**Salida en consola:**

```text
130 m -> Patio
100 m -> No hay ubicación a esa distancia
```

### c. ¿Por qué sirve el enfoque recursivo en este caso?

El enfoque recursivo con **búsqueda binaria** es idóneo para consultar las distancias de las ubicaciones del campus por tres razones:

- **Complejidad temporal $O(\log n)$:** en cada llamada el rango se reduce a la mitad, por lo que se necesitan como máximo $\log_2(n)$ comparaciones, frente a las $n$ de una búsqueda lineal $O(n)$. Esto importa cuando crece el número de ubicaciones del campus.

- **Claridad estructural mediante Divide y Vencerás:** el problema se reduce a decidir en cada paso si el valor está a la izquierda o a la derecha del punto medio, y la definición recursiva refleja exactamente esa idea.
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
## Actividad 3. Modelado del problema mediante grafos

📁 Código: carpeta [`Actividad 3`](./Actividad%203) — clase `Grafo.java` (paquete `pe.edu.isil`)

### 3.1 Representación del campus como grafo

El campus se modeló como un grafo **no dirigido y ponderado**:

- **Nodos:** 0 Puerta Principal, 1 Biblioteca, 2 Cafetería, 3 Laboratorio de Cómputo, 4 Patio Central, 5 Auditorio.
- **Aristas:** los pasillos o caminos que unen dos ubicaciones.
- **Sentido:** no dirigido, porque cada pasillo se puede recorrer en ambos sentidos.
- **Peso:** distancia en metros de cada camino.

| Arista | Distancia |
|---|---|
| Puerta – Biblioteca | 50 m |
| Puerta – Cafetería | 70 m |
| Biblioteca – Laboratorio | 40 m |
| Cafetería – Laboratorio | 30 m |
| Cafetería – Patio | 60 m |
| Laboratorio – Auditorio | 80 m |
| Patio – Auditorio | 35 m |

Se eligió la **lista de adyacencia** (`LinkedList<Integer>[]`) en lugar de la matriz de adyacencia. El grafo tiene 6 nodos y solo 7 aristas, así que es disperso. La lista ocupa espacio O(V + E) y guarda solo las conexiones que existen, mientras que la matriz ocupa O(V²).

```
Lista de adyacencia del campus:
0 Puerta -> Biblioteca Cafeteria
1 Biblioteca -> Puerta Laboratorio
2 Cafeteria -> Puerta Laboratorio Patio
3 Laboratorio -> Biblioteca Cafeteria Auditorio
4 Patio -> Cafeteria Auditorio
5 Auditorio -> Laboratorio Patio
```

### 3.2 Situación en la que conviene usar DFS

**Situación:** antes de poner en marcha el módulo, verificar que **todas las ubicaciones del campus estén conectadas** y que ninguna zona quede aislada (por ejemplo, por un pasillo en obras).

La búsqueda en profundidad (DFS) avanza por un camino hasta el nodo más profundo y luego retrocede (backtracking) para explorar las demás ramas. Se implementó de forma **recursiva**, como en la sesión 13:

- **Caso recursivo:** si un vecino no ha sido visitado, se llama a `DFS` con ese vecino.
- **Caso base:** cuando un nodo ya no tiene vecinos sin visitar, la llamada termina y regresa al nodo anterior.
- Si al final todos los nodos quedaron visitados, el campus está conectado.

DFS conviene aquí porque solo se necesita saber si se llega a todos los nodos, no cuál es la ruta más corta. Usa poca memoria (la pila de llamadas recursivas) y su complejidad es O(V + E).

```
Recorrido DFS desde Puerta: Puerta Biblioteca Laboratorio Cafeteria Patio Auditorio
Todas las ubicaciones estan conectadas? true
```

### 3.3 Situación en la que conviene usar BFS

**Situación:** un visitante está en la **Puerta** y quiere llegar al **Auditorio** pasando por la **menor cantidad de lugares posible**.

La búsqueda en anchura (BFS) recorre el grafo **por niveles** usando una **cola** (FIFO). Primero visita los vecinos directos del origen, luego los vecinos de esos vecinos, y así sucesivamente. Por eso la primera vez que llega al destino lo hace con el **mínimo número de tramos**. Para reconstruir la ruta se guarda en un arreglo `padre[]` desde qué nodo se llegó a cada uno.

| Nivel | Ubicaciones |
|---|---|
| 0 | Puerta |
| 1 | Biblioteca, Cafetería |
| 2 | Laboratorio, Patio |
| 3 | Auditorio |

```
Recorrido BFS desde Puerta: Puerta Biblioteca Cafeteria Laboratorio Patio Auditorio
Ruta con menos tramos de Puerta a Auditorio: Puerta -> Biblioteca -> Laboratorio -> Auditorio  (3 tramos)
```

La ruta tiene 3 tramos. Su limitación es que **BFS no considera los metros**: mide 50 + 40 + 80 = **170 m**, y no es la más corta en distancia.

### 3.4 Aporte del algoritmo heurístico A*

A* es una búsqueda **informada**: en cada paso elige el nodo con menor valor de

**f(n) = g(n) + h(n)**

- **g(n):** costo real acumulado desde el origen (metros recorridos).
- **h(n):** heurística, una estimación de lo que falta para llegar a la meta (por ejemplo, la distancia en línea recta).

Si la heurística es **admisible** (nunca sobreestima la distancia real), A* garantiza el camino óptimo. Estimaciones usadas hasta el Auditorio: Puerta 150, Biblioteca 118, Cafetería 85, Laboratorio 70, Patio 30, Auditorio 0.

**Ejecución de A\* de Puerta a Auditorio:**

| Paso | Nodo expandido | g | h | f = g + h | Vecinos que agrega |
|---|---|---|---|---|---|
| 1 | Puerta | 0 | 150 | 150 | Biblioteca (f = 168), Cafetería (f = 155) |
| 2 | Cafetería | 70 | 85 | 155 | Laboratorio (f = 170), Patio (f = 160) |
| 3 | Patio | 130 | 30 | 160 | Auditorio (f = 165) |
| 4 | Auditorio | 165 | 0 | 165 | Meta alcanzada |

**Resultado:** Puerta → Cafetería → Patio → Auditorio, con **165 m**.

| Algoritmo | Ruta | Tramos | Distancia | Criterio |
|---|---|---|---|---|
| BFS | Puerta → Biblioteca → Laboratorio → Auditorio | 3 | 170 m | Menos tramos |
| A* | Puerta → Cafetería → Patio → Auditorio | 3 | **165 m** | Menor distancia, guiado por heurística |

A* aporta **dirección hacia la meta**: gracias a la heurística nunca expandió la Biblioteca ni el Laboratorio, porque estimó que por ahí la ruta salía más larga. Así encontró el camino más corto en metros explorando menos nodos. Es el mismo principio que usa Google Maps para calcular rutas.

### Cómo ejecutar

1. Abrir la carpeta `Actividad 3` en IntelliJ IDEA.
2. Ejecutar el `main` de `Grafo.java`.
3. La consola muestra la lista de adyacencia, el recorrido DFS, el recorrido BFS y la ruta con menos tramos.

--------------------------------------------------------------------------------------------------------------
## Actividad 4. Recorridos y conclusión
