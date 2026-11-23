package pe.edu.isil;

import java.util.LinkedList;

public class Grafo {

    private int V;                      // número de vértices (ubicaciones)
    private LinkedList<Integer>[] adj;  // lista de adyacencia
    private String[] nombres;           // nombre de cada ubicación

    public Grafo(int v, String[] nombres) {
        this.V = v;
        this.nombres = nombres;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Grafo no dirigido: la conexión va en ambos sentidos
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public void mostrar() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " " + nombres[i] + " -> ");
            for (int n : adj[i]) {
                System.out.print(nombres[n] + " ");
            }
            System.out.println();
        }
    }
    // DFS recursivo (sesion 13): explora hasta el nodo mas profundo
    public void DFS(int v, boolean[] visitado) {
        visitado[v] = true;                     // marca el nodo actual
        System.out.print(nombres[v] + " ");
        for (int n : adj[v]) {
            if (!visitado[n]) {                 // caso recursivo: vecino sin visitar
                DFS(n, visitado);
            }
        }                                       // caso base: ya no hay vecinos sin visitar
    }

    // Usa DFS para saber si todas las ubicaciones estan conectadas
    public boolean estaConectado() {
        boolean[] visitado = new boolean[V];
        System.out.print("Recorrido DFS desde " + nombres[0] + ": ");
        DFS(0, visitado);
        System.out.println();
        for (int i = 0; i < V; i++) {
            if (!visitado[i]) {
                return false;
            }
        }
        return true;
    }

    // BFS con cola (sesion 13): recorre por niveles
    public void BFS(int s) {
        boolean[] visitado = new boolean[V];
        LinkedList<Integer> cola = new LinkedList<>();

        visitado[s] = true;
        cola.add(s);

        System.out.print("Recorrido BFS desde " + nombres[s] + ": ");
        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            System.out.print(nombres[nodo] + " ");

            for (int n : adj[nodo]) {
                if (!visitado[n]) {
                    visitado[n] = true;
                    cola.add(n);
                }
            }
        }
        System.out.println();
    }

    // BFS que guarda de donde vino cada nodo para reconstruir la ruta con menos tramos
    public void rutaMenosTramos(int origen, int destino) {
        boolean[] visitado = new boolean[V];
        int[] padre = new int[V];
        LinkedList<Integer> cola = new LinkedList<>();

        visitado[origen] = true;
        padre[origen] = -1;
        cola.add(origen);

        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            if (nodo == destino) {
                break;
            }
            for (int n : adj[nodo]) {
                if (!visitado[n]) {
                    visitado[n] = true;
                    padre[n] = nodo;
                    cola.add(n);
                }
            }
        }

        LinkedList<Integer> ruta = new LinkedList<>();
        for (int actual = destino; actual != -1; actual = padre[actual]) {
            ruta.addFirst(actual);
        }

        System.out.print("Ruta con menos tramos de " + nombres[origen] + " a " + nombres[destino] + ": ");
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(nombres[ruta.get(i)]);
            if (i < ruta.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("  (" + (ruta.size() - 1) + " tramos)");
    }

    public static void main(String[] args) {
        String[] ubicaciones = {"Puerta", "Biblioteca", "Cafeteria", "Laboratorio", "Patio", "Auditorio"};
        Grafo campus = new Grafo(6, ubicaciones);

        campus.addEdge(0, 1); // Puerta - Biblioteca (50 m)
        campus.addEdge(0, 2); // Puerta - Cafeteria (70 m)
        campus.addEdge(1, 3); // Biblioteca - Laboratorio (40 m)
        campus.addEdge(2, 3); // Cafeteria - Laboratorio (30 m)
        campus.addEdge(2, 4); // Cafeteria - Patio (60 m)
        campus.addEdge(3, 5); // Laboratorio - Auditorio (80 m)
        campus.addEdge(4, 5); // Patio - Auditorio (35 m)

        System.out.println("Lista de adyacencia del campus:");
        campus.mostrar();

        System.out.println();
        boolean conectado = campus.estaConectado();
        System.out.println("Todas las ubicaciones estan conectadas? " + conectado);

        System.out.println();
        campus.BFS(0);
        campus.rutaMenosTramos(0, 5);
    }
}