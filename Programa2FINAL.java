import java.util.*;
import java.util.stream.Collectors;

class Graph {
    int vertices;
    List<List<Integer>> adjacencyList;
    boolean isDirected;

    public Graph(int vertices, boolean isDirected) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        this.isDirected = isDirected;

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        if (!isDirected) {
            adjacencyList.get(destination).add(source);
        }
    }

    public int getVertexDegree(int vertex) {
        return adjacencyList.get(vertex).size();
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        dfs(0, visited);
        for (boolean visitStatus : visited) {
            if (!visitStatus) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;
        List<Integer> neighbors = adjacencyList.get(vertex);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    public boolean isComplete() {
        int expectedDegree = vertices - 1;
        for (int i = 0; i < vertices; i++) {
            if (getVertexDegree(i) != expectedDegree) {
                return false;
            }
        }
        return true;
    }

    public int countConnectedComponents() {
        boolean[] visited = new boolean[vertices];
        int connectedComponents = 0;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                connectedComponents++;
                dfs(i, visited);
            }
        }

        return connectedComponents;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú principal:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Salir");
            int mainMenuOption = scanner.nextInt();

            if (mainMenuOption == 2) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.println("Ingrese el número de vértices del grafo:");
            int vertices = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            System.out.println("¿Es un grafo dirigido? (S/N):");
            boolean isDirected = scanner.next().equalsIgnoreCase("S");
            scanner.nextLine(); // Clear newline character

            Graph graph = new Graph(vertices, isDirected);

            System.out.println("Ingrese las aristas en formato: vértice_fuente vértice_destino");
            System.out.println("Ingrese 'Q' para terminar de ingresar aristas.");

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    break;
                }
                String[] edgeVertices = input.split(" ");
                int source = Integer.parseInt(edgeVertices[0]);
                int destination = Integer.parseInt(edgeVertices[1]);
                graph.addEdge(source, destination);
            }

            while (true) {
                System.out.println("\nMenú de operaciones:");
                System.out.println("1. Verificar si es grafo conexo");
                System.out.println("2. Verificar si es grafo completo");
                System.out.println("3. Imprimir grados de vértices");
                System.out.println("4. Imprimir lista de adyacencia");
                System.out.println("5. Contar componentes conexos");
                System.out.println("6. Regresar al menú principal");
                int operation = scanner.nextInt();

                if (operation == 1) {
                    if (graph.isConnected()) {
                        System.out.println("El grafo es conexo.");
                    } else {
                        System.out.println("El grafo no es conexo.");
                    }
                } else if (operation == 2) {
                    if (graph.isComplete()) {
                        System.out.println("El grafo es completo.");
                    } else {
                        System.out.println("El grafo no es completo.");
                    }
                } else if (operation == 3) {
                    System.out.println("Grados de vértices:");
                    for (int i = 0; i < graph.vertices; i++) {
                        System.out.println("Vértice " + i + ": grado " + graph.getVertexDegree(i));
                    }
                } else if (operation == 4) {
                    System.out.println("Lista de adyacencia:");
                    for (int i = 0; i < graph.vertices; i++) {
                        System.out.println(i + ": " + graph.adjacencyList.get(i).stream().sorted().collect(Collectors.toList()));
                    }
                } else if (operation == 5) {
                    int connectedComponents = graph.countConnectedComponents();
                    System.out.println("Número de componentes conexos: " + connectedComponents);
                } else if (operation == 6) {
                    System.out.println("Regresando al menú principal...\n");
                    break;
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            }
        }
    }
}
