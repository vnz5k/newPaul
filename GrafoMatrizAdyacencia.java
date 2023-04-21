import java.util.*;

public class GrafoMatrizAdyacencia {
    private int[][] matriz;
    private boolean dirigido;

    // Constructor
    public GrafoMatrizAdyacencia(int vertices, boolean dirigido) {
        matriz = new int[vertices][vertices];
        this.dirigido = dirigido;
    }

    // Agrega arista
    public void agregarArista(int origen, int destino) {
        matriz[origen][destino] = 1;
        if (!dirigido) {
            matriz[destino][origen] = 1;
        }
    }

    // Determina si el grafo es conexo
    public boolean esConexo() {
        boolean[] visitados = new boolean[matriz.length];
        dfs(0, visitados);

        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }

    // Función DFS para recorrer el grafo
    private void dfs(int vertice, boolean[] visitados) {
        visitados[vertice] = true;

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] == 1 && !visitados[i]) {
                dfs(i, visitados);
            }
        }
    }

    // Determina si el grafo es completo
    public boolean esCompleto() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i != j && matriz[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Obtiene el grado de cada vértice
    public List<Integer> gradoVertices() {
        List<Integer> grados = new ArrayList<>();

        for (int i = 0; i < matriz.length; i++) {
            int grado = 0;
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == 1) {
                    grado++;
                }
            }
            grados.add(grado);
        }
        return grados;
    }

    // Encuentra un camino entre dos vértices
    public List<Integer> camino(int origen, int destino) {
        List<Integer> camino = new ArrayList<>();
        boolean[] visitados = new boolean[matriz.length];
        dfsCamino(origen, destino, visitados, camino);
        return camino;
    }

    // Función DFS para encontrar un camino
    private boolean dfsCamino(int vertice, int destino, boolean[] visitados, List<Integer> camino) {
        visitados[vertice] = true;
        camino.add(vertice);

        if (vertice == destino) {
            return true;
        }

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] == 1 && !visitados[i]) {
                if (dfsCamino(i, destino, visitados, camino)) {
                    return true;
                }
            }
        }
        camino.remove(camino.size() - 1);
        return false;
    }

    // Encuentra el número de componentes conexos
    public int numeroComponentesConexos() {
        int componentesConexos = 0;
        boolean[] visitados = new boolean[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            if (!visitados[i]) {
                dfs(i, visitados);
                componentesConexos++;
            }
        }
        return componentesConexos;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        GrafoMatrizAdyacencia grafo = null;

        while (true) {
            System.out.println("Menú principal:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.print("Ingrese el número de vértices: ");
                int vertices = scanner.nextInt();
                System.out.print("¿Es dirigido? (true/false): ");
                boolean dirigido = scanner.nextBoolean();
                grafo = new GrafoMatrizAdyacencia(vertices, dirigido);

                int subOpcion;
                while (true) {
                    System.out.println("\nMenú de operaciones:");
                    System.out.println("1. Agregar arista");
                    System.out.println("2. Verificar si es conexo");
                    System.out.println("3. Verificar si es completo");
                    System.out.println("4. Mostrar grado de vértices");
                    System.out.println("5. Mostrar camino entre dos vértices");
                    System.out.println("6. Mostrar número de componentes conexos");
                    System.out.println("7. Regresar al menú principal");
                    System.out.print("Ingrese la opción deseada: ");
                    subOpcion = scanner.nextInt();

                    if (subOpcion == 1) {
                        System.out.print("Ingrese el vértice origen: ");
                        int origen = scanner.nextInt();
                        System.out.print("Ingrese el vértice destino: ");
                        int destino = scanner.nextInt();
                        grafo.agregarArista(origen, destino);
                    } else if (subOpcion == 2) {
                        System.out.println("¿Es conexo? " + grafo.esConexo());
                    } else if (subOpcion == 3) {
                        System.out.println("¿Es completo? " + grafo.esCompleto());
                    } else if (subOpcion == 4) {
                        System.out.println("Grado de vértices: " + grafo.gradoVertices());
                    } else if (subOpcion == 5) {
                        System.out.print("Ingrese el vértice origen: ");
                        int origen = scanner.nextInt();
                        System.out.print("Ingrese el vértice destino: ");
                        int destino = scanner.nextInt();
                        System.out.println("Camino entre " + origen + " y " + destino + ": " + grafo.camino(origen, destino));
                    } else if (subOpcion == 6) {
                        System.out.println("Número de componentes conexos: " + grafo.numeroComponentesConexos());
                    } else if (subOpcion == 7) {
                        break;
                    } else {
                        System.out.println("Opción inválida. Intente de nuevo.");
                    }
                }
            } else if (opcion == 2) {
                break;
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
