import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    private boolean[] visited;
    private boolean directed;
    private int vertices;

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        adjacencyList = new HashMap<>();
        visited = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        if (!directed) {
            adjacencyList.get(destination).add(source);
        }
    }

    public void displayList() {
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": " + adjacencyList.get(i));
        }
    }

    // Resto de métodos sin cambios

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

                    System.out.println("Ingrese el número de aristas (máximo 20):");
                    int edges = scanner.nextInt();
                    
                    if (edges > 20) {
                        System.out.println("El número de aristas excede el límite de 20. Intente de nuevo.");
                        break;
                    }

                    for (int i = 0; i < edges; i++) {
                        System.out.println("Ingrese el vértice de origen y destino (separados por espacio) para la arista " + (i + 1) + ":");
                        int source = scanner.nextInt();
                        int destination = scanner.nextInt();
                        graph.addEdge(source, destination);
                    }

                    // Resto del código sin cambios
                    // Reemplazar graph.displayMatrix() con graph.displayList()
            }
        }
        scanner.close();
    }
}
