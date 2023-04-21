import java.util.*;

public class GrafoMatrizAdyacencia {
    private boolean[][] matrizAdyacencia;
    private boolean dirigido;

    public GrafoMatrizAdyacencia(int numVertices, boolean dirigido) {
        matrizAdyacencia = new boolean[numVertices][numVertices];
        this.dirigido = dirigido;
    }

    public void agregarArista(int origen, int destino) {
        matrizAdyacencia[origen][destino] = true;
        if (!dirigido) {
            matrizAdyacencia[destino][origen] = true;
        }
    }

    public boolean esConexo() {
        Set<Integer> visitados = new HashSet<>();
        dfs(0, visitados);
        return visitados.size() == matrizAdyacencia.length;
    }

    private void dfs(int vertice, Set<Integer> visitados) {
        visitados.add(vertice);
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[vertice][i] && !visitados.contains(i)) {
                dfs(i, visitados);
            }
        }
    }

    public boolean esCompleto() {
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                if (i != j && !matrizAdyacencia[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> gradoVertices() {
        List<Integer> grado = new ArrayList<>();
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            int gradoActual = 0;
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                if (matrizAdyacencia[i][j]) {
                    gradoActual++;
                }
            }
            grado.add(gradoActual);
        }
        return grado;
    }

    public List<Integer> camino(int origen, int destino) {
        List<Integer> camino = new ArrayList<>();
        dfsCamino(origen, destino, new HashSet<>(), camino);
        return camino;
    }

    private boolean dfsCamino(int origen, int destino, Set<Integer> visitados, List<Integer> camino) {
        visitados.add(origen);
        camino.add(origen);

        if (origen == destino) {
            return true;
        }

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[origen][i] && !visitados.contains(i)) {
                if (dfsCamino(i, destino, visitados, camino)) {
                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }

    public int numeroComponentesConexos() {
        int componentesConexos = 0;
        Set<Integer> visitados = new HashSet<>();

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (!visitados.contains(i)) {
                dfs(i, visitados);
                componentesConexos++;
            }
        }

        return componentesConexos;
    }

    public int getNumeroVertices() {
        return matrizAdyacencia.length;
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
                int numVertices = scanner.nextInt();
                System.out.print("¿Es dirigido? (true/false): ");
                boolean dirigido = scanner.nextBoolean();
                grafo = new GrafoMatrizAdyacencia(numVertices, dirigido);

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
                    opcion = scanner.nextInt();

                    if (opcion == 1) {
                        System.out.print("Ingrese el vértice origen: ");
                        int origen = scanner.nextInt();
                        System.out.print("Ingrese el vértice destino: ");
                        int destino = scanner.nextInt();
                        grafo.agregarArista(origen, destino);
                    } else if (opcion == 2) {
                        System.out.println("¿Es conexo? " + grafo.esConexo());
                    } else if (opcion == 3) {
                        System.out.println("¿Es completo? " + grafo.esCompleto());
                    } else if (opcion == 4) {
                        System.out.println("Grado de vértices: " + grafo.gradoVertices());
                    } else if (opcion == 5) {
                        System.out.print("Ingrese el vértice origen: ");
                        int origen = scanner.nextInt();
                        System.out.print("Ingrese el vértice destino: ");
                        int destino = scanner.nextInt();
                        if (origen >= 0 && origen < grafo.getNumeroVertices() && destino >= 0 && destino < grafo.getNumeroVertices()) {
                            System.out.println("Camino entre " + origen + " y " + destino + ": " + grafo.camino(origen, destino));
                        } else {
                            System.out.println("Los vértices ingresados están fuera de rango.");
                        }
                    } else if (opcion == 6) {
                        System.out.println("Número de componentes conexos: " + grafo.numeroComponentesConexos());
                    } else if (opcion == 7) {
                        break;
                    }
                }
            } else if (opcion == 2) {
                break;
            }
        }
    }
}
