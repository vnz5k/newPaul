import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = null;

        while (true) {
            System.out.println("Menú principal:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Ingrese el número de vértices del grafo:");
                int vertices = scanner.nextInt();
                scanner.nextLine();

                System.out.println("¿Es un grafo dirigido? (S/N):");
                String directedInput = scanner.nextLine().toUpperCase();
                boolean directed = directedInput.equals("S");

                graph = new Graph(vertices, directed);

                System.out.println("Ingrese las aristas en formato: vértice_fuente vértice_destino");
                System.out.println("Ingrese 'Q' para terminar de ingresar aristas.");

                while (true) {
                    String edgeInput = scanner.nextLine().toUpperCase();

                    if (edgeInput.equals("Q")) {
                        break;
                    }

                    String[] edgeVertices = edgeInput.split(" ");
                    int source = Integer.parseInt(edgeVertices[0]) - 1;
                    int destination = Integer.parseInt(edgeVertices[1]) - 1;


                    graph.addEdge(source, destination);
                }

                while (true) {
                    System.out.println("Menú de operaciones:");
                    System.out.println("1. Verificar si es grafo conexo");
                    System.out.println("2. Verificar si es grafo completo");
                    System.out.println("3. Imprimir grados de vértices");
                    System.out.println("4. Imprimir lista de adyacencia");
                    System.out.println("5. Contar componentes conexos");
                    System.out.println("6. Regresar al menú principal");
                    int operation = scanner.nextInt();
                    scanner.nextLine();

                    if (operation == 1) {
                        System.out.println(graph.isConnected() ? "El grafo es conexo." : "El grafo no es conexo.");
                    } else if (operation == 2) {
                        System.out.println(graph.isComplete() ? "El grafo es completo." : "El grafo no es completo.");
                    } else if (operation == 3) {
                        graph.printVertexDegrees();
                    } else if (operation == 4) {
                        graph.printAdjacencyList();
                    } else if (operation == 5) {
                        System.out.println("El grafo tiene " + graph.countConnectedComponents() + " componentes conexos.");
                    } else if (operation == 6) {
                        break;
                    }
                }
            } else if (option == 2) {
                break;
            }
        }
    }
}

class Graph {
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    private final boolean directed;

    public Graph(int vertices, boolean directed) {
        this.adjacencyList = new ArrayList<>(vertices);
        this.directed = directed;

        for (int i = 0; i < vertices; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        this.adjacencyList.get(source).add(destination);

        if (!directed) {
            this.adjacencyList.get(destination).add(source);
        }
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[this.adjacencyList.size()];
        dfs(0, visited);

        for (boolean vertexVisited : visited) {
            if (!vertexVisited) {
                return false;
            }
        }

        return true;
    }

    public boolean isComplete() {
        int vertices = this.adjacencyList.size();
        int expectedEdges = (vertices * (vertices - 1)) / 2;

        if (directed) {
            expectedEdges *= 2;
        }

        int edgesCount = 0;
        for (ArrayList<Integer> edges : this.adjacencyList) {
            edgesCount += edges.size();
        }

        return edgesCount == expectedEdges;
    }

    public void printVertexDegrees() {
        System.out.println("Grados de vértices:");
        for (int i = 0; i < this.adjacencyList.size(); i++) {
            System.out.println("Vértice " + i + ": grado " + this.adjacencyList.get(i).size());
        }
    }

    public void printAdjacencyList() {
        System.out.println("Lista de adyacencia:");
        for (int i = 0; i < this.adjacencyList.size(); i++) {
            System.out.println(i + ": " + this.adjacencyList.get(i));
        }
    }

    public int countConnectedComponents() {
        boolean[] visited = new boolean[this.adjacencyList.size()];
        int connectedComponents = 0;

        for (int i = 0; i < this.adjacencyList.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited);
                connectedComponents++;
            }
        }

        return connectedComponents;
    }

    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;

        for (int neighbor : this.adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }
}
