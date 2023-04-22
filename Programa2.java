import java.util.*;

class Grafo {
    private int vertices;
    private LinkedList<Integer> adjList[];

    public Grafo(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList();
        }
    }

    // Agrega aristas en ambas direcciones para un grafo no dirigido
    public void agregarArista(int origen, int destino) {
        adjList[origen].add(destino);
        adjList[destino].add(origen);
    }

    public LinkedList<Integer> obtenerAdyacentes(int vertice) {
        return adjList[vertice];
    }

    public int obtenerVertices() {
        return vertices;
    }
}

class Programa2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        Grafo grafo = null;

        while (true) {
            System.out.println("Menú inicial:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.print("Ingrese la cantidad de vértices del grafo: ");
                int vertices = sc.nextInt();
                grafo = new Grafo(vertices);
                System.out.println("Ingrese las aristas en formato 'origen destino'. Ingrese -1 -1 para terminar.");
                while (true) {
                    int origen = sc.nextInt();
                    int destino = sc.nextInt();
                    if (origen == -1 && destino == -1) {
                        break;
                    }
                    grafo.agregarArista(origen, destino);
                }
                ejecutarOperaciones(grafo);
            } else if (opcion == 2) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
        sc.close();
    }

    private static void ejecutarOperaciones(Grafo grafo) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        while (true) {
            System.out.println("\nOperaciones:");
            System.out.println("1. Verificar si el grafo es conexo");
            System.out.println("2. Verificar si el grafo es completo");
            System.out.println("3. Calcular el grado de cada vértice");
            System.out.println("4. Calcular el grado de entrada y salida de cada vértice (solo grafos dirigidos)");
            System.out.println("5. Calcular un camino entre dos vértices");
            System.out.println("6. Calcular el número de componentes conexos");
            System.out.println("7. Imprimir matriz de adyacencia");
            System.out.println("8. Regresar al menú inicial");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();

            if (opcion == 3) {
                // Calcular el grado de cada vértice
                for (int i = 0; i < grafo.obtenerVertices(); i++) {
                    System.out.println("Grado del vértice " + i + ": " + grafo.obtenerAdyacentes(i).size());
                }
            } else if (opcion == 7) {
                // Imprimir matriz de adyacencia
                System.out.println("Matriz de adyacencia:");
                int[][] matriz = new int[grafo.obtenerVertices()][grafo.obtenerVertices()];
                for (int i = 0; i < grafo.obtenerVertices(); i++) {
                    for (int j = 0; j < grafo.obtenerVertices(); j++) {
                        matriz[i][j] = grafo.obtenerAdyacentes(i).contains(j) ? 1 : 0;
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println();
                }
            } else if (opcion == 8) {
                // Regresar al menú inicial
                System.out.println("Regresando al menú inicial...");
                break;
            } else {
                System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
    }
}
