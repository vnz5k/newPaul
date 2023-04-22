import java.util.*;

class Graph {
    // Cambiado de matriz de adyacencia a lista de adyacencia
    private List<List<Integer>> adjacencyList;
    private boolean[] visited;
    private boolean directed;
    private int vertices;

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        adjacencyList = new ArrayList<>(vertices);
        visited = new boolean[vertices];

        // Inicializa la lista de adyacencia
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Método modificado para agregar aristas en la lista de adyacencia
    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        if (!directed) {
            adjacencyList.get(destination).add(source);
        }
    }

    // Método modificado para mostrar la lista de adyacencia en lugar de la matriz de adyacencia
    public void displayList() {
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vértice " + i + ": " + adjacencyList.get(i));
        }
    }

    // Resto del código (métodos) adaptado para listas de adyacencia
    // ...
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

                    // Aquí puedes agregar 20 aristas al grafo
                    for (int i = 0; i < edges; i++) {
                        System.out.println("Ingrese el vértice de origen y destino (separados por espacio) para la arista " + (i + 1) + ":");
                        int source = scanner.nextInt();
                        int destination = scanner.nextInt();
                        graph.addEdge(source, destination);
                    }

                    // Resto del menú principal
                    // ...
            }
        }
    }
}
