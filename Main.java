import java.util.*;

class Graph {
    private int[][] adjacencyMatrix;
    private boolean[] visited;
    private boolean directed;
    private int vertices;

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;1
        adjacencyMatrix = new int[vertices][vertices];
        visited = new boolean[vertices];
    }

    public void addEdge(int source, int destination) {
        adjacencyMatrix[source][destination] = 1;
        if (!directed) {
            adjacencyMatrix[destination][source] = 1;
        }
    }

    public void displayMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printDegree() {
        for (int i = 0; i < vertices; i++) {
            int degree = 0;
            for (int j = 0; j < vertices; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    degree++;
                }
            }
            System.out.println("Degree of vertex " + i + ": " + degree);
        }
    }

    public void printDirectedDegrees() {
        for (int i = 0; i < vertices; i++) {
            int outDegree = 0;
            int inDegree = 0;
            for (int j = 0; j < vertices; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    outDegree++;
                }
                if (adjacencyMatrix[j][i] == 1) {
                    inDegree++;
                }
            }
            System.out.println("Out-degree of vertex " + i + ": " + outDegree);
            System.out.println("In-degree of vertex " + i + ": " + inDegree);
        }
    }

    public boolean isConnected() {
        Arrays.fill(visited, false);
        int startVertex = findStartVertex();
        if (startVertex == -1) {
            return false;
        }
        dfs(startVertex);
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    private int findStartVertex() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void dfs(int vertex) {
        visited[vertex] = true;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i != j && adjacencyMatrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para encontrar un camino
    public List<Integer> findPath(int source, int destination) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        boolean pathExists = findPathDFS(source, destination, visited, path);
        if (pathExists) {
            return path;
        } else {
            return null;
        }
    }

    private boolean findPathDFS(int source, int destination, boolean[] visited, List<Integer> path) {
        if (source == destination) {
            path.add(source);
            return true;
        }
        visited[source] = true;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[source][i] == 1 && !visited[i]) {
                if (findPathDFS(i, destination, visited, path)) {
                    path.add(0, source);
                    return true;
                }
            }
        }
        return false;
    }

    // Método para calcular el número de componentes conexos
    public int connectedComponents() {
        int count = 0;
        Arrays.fill(visited, false);
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean directed;

        while (!exit) {
            System.out.println("1. Crear grafo");
            System.out.println("2. Salir");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el número de vértices:");
                    int vertices = scanner.nextInt();

                    System.out.println("Es dirigido? (true/false):");
                    directed = scanner.nextBoolean();

                    Graph graph = new Graph(vertices, directed);

                    System.out.println("Ingrese el número de aristas:");
                    int edges = scanner.nextInt();

                    for (int i = 0; i < edges; i++) {
                        System.out.println("Ingrese el vértice de origen y destino (separados por espacio) para la arista " + (i + 1) + ":");
                        int source = scanner.nextInt();
                        int destination = scanner.nextInt();
                        graph.addEdge(source, destination);
                    }

                    boolean subMenu = true;
                    while (subMenu) {
                        System.out.println("1. Imprimir matriz");
                        System.out.println("2. Verificar si es grafo conexo");
                        System.out.println("3. Verificar si es grafo completo");
                        System.out.println("4. Imprimir grados de vértices");
                        System.out.println("5. Encontrar camino entre dos vértices");
                        System.out.println("6. Calcular número de componentes conexos");
                        System.out.println("7. Salir");
                        int subOption = scanner.nextInt();

                        switch (subOption) {
                            case 1:
                                graph.displayMatrix();
                                break;
                            case 2:
                                System.out.println(graph.isConnected() ? "El grafo es conexo." : "El grafo no es conexo.");
                                break;
                            case 3:
                                System.out.println(graph.isComplete() ? "El grafo es completo." : "El grafo no es completo.");
                                break;
                            case 4:
                                if (directed) {
                                    graph.printDirectedDegrees();
                                } else {
                                    graph.printDegree();
                                }
                                break;
                            case 5:
                                System.out.println("Ingrese el vértice de origen y destino (separados por espacio):");
                                int source = scanner.nextInt();
                                int destination = scanner.nextInt();
                                List<Integer> path = graph.findPath(source, destination);
                                if (path != null) {
                                    System.out.println("Camino encontrado: " + path);
                                } else {
                                    System.out.println("No se encontró un camino.");
                                }
                                break;
                            case 6:
                                System.out.println("Número de componentes conexos: " + graph.connectedComponents());
                                break;
                            case 7:
                                subMenu = false;
                                break;
                            default:
                                System.out.println("Opción no válida. Intente de nuevo.");
                        }
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}
