import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class PostmanProblem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of post offices: ");
		int n = scanner.nextInt();
		int[][] dist = new int[n][n];
		System.out.println("Enter the distance matrix: ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = scanner.nextInt();
			}
		}

		Combined combined = new Combined(dist);

		for (int i = 0; i < n; i++) {
			combined.addVertex(new Vertex(i));
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dist[i][j] > 0) {
					combined.addEdge(new Edge(i, j, dist[i][j]));
				}
			}
		}

		String connectivity = combined.checkConnectivity();
		System.out.println(connectivity);

		int[] tour = combined.getTour();
		System.out.println("Optimal tour: " + Arrays.toString(tour));
	}
}

class Combined {
	private Graph graph;
	private TSP tsp;

	public Combined(int[][] dist) {
		this.graph = new Graph();
		this.tsp = new TSP(dist);
	}

	public void addVertex(Vertex v) {
		graph.addVertex(v);
	}

	public void addEdge(Edge e) {
		graph.addEdge(e);
	}

	public String checkConnectivity() {
		// Implement this method to check the connectivity of the graph
		return "Graph connectivity not implemented";
	}

	public int[] getTour() {
		return tsp.getTour();
	}
}

class Graph {
	private List<Vertex> vertices;
	private List<Edge> edges;

	public Graph() {
		this.vertices = new ArrayList<>();
		this.edges = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		vertices.add(vertex);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}
}

class Vertex {
	private int id;

	public Vertex(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}

class Edge {
	private int from;
	private int to;
	private int weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}
}

public class TSP {
	int[][] dist;
	int n;
	int[][] dp;
	int[][] next;

	public TSP(int[][] dist) {
		this.dist = dist;
		this.n = dist.length;
		this.dp = new int[1 << n][n];
		this.next = new int[1 << n][n];
		for (int[] row : dp)
			Arrays.fill(row, Integer.MAX_VALUE / 2);
		for (int[] row : next)
			Arrays.fill(row, -1);
		for (int i = 0; i < n; i++)
			dp[1 << i][i] = 0;
		for (int mask = 1; mask < 1 << n; mask += 2) {
			for (int i = 0; i < n; i++) {
				if ((mask & 1 << i) != 0) {
					for (int j = 0; j < n; j++) {
						if ((mask & 1 << j) != 0) {
							int newMask = mask ^ (1 << i);
							if (dp[newMask][j] + dist[j][i] < dp[mask][i]) {
								dp[mask][i] = dp[newMask][j] + dist[j][i];
								next[mask][i] = j;
							}
						}
					}
				}
			}
		}
	}

	public int[] getTour() {
		int last = -1;
		int state = (1 << n) - 1;
		int[] tour = new int[n + 1];
		for (int i = n - 1; i >= 1; i--) {
			tour[i] = last = next[state][last == -1 ? 0 : last];
			state ^= 1 << last;
		}
		tour[0] = tour[n] = last;
		return tour;
	}
}