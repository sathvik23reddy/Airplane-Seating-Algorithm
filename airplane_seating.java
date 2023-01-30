import java.util.*;

public class airplane_seating {

	public static void main(String[] args) {
		List<int[]> seating = new ArrayList<>();
		seating.add(new int[] { 3, 2 });
		seating.add(new int[] { 4, 3 });
		seating.add(new int[] { 2, 3 });
		seating.add(new int[] { 3, 4 });
		int nPassengers = 30;
		Queue<int[]> q = new LinkedList<>();
		List<int[][]> ls = new LinkedList<>();
		getOrder(q, seating);
		tagSeats(ls, seating);
		for (int[][] i : ls) {
			for (int j[] : i) {
				for (int k : j) {
					System.out.print(k + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		printSol(q, ls, nPassengers);
	}

	private static void printSol(Queue<int[]> q, List<int[][]> ls, int nPassengers) {
		int z = 1, i = 1;
		while (i <= nPassengers) {
			Queue<int[]> o = new LinkedList<>(q);
			while (!o.isEmpty()) {
				int x[] = o.poll();
				if (ls.get(x[0])[x[1]][x[2]] == z) {
					System.out.println("Seat Number " + i + " is placed at Compartment No. " + (x[0] + 1) + ", Row No. "
							+ (x[1] + 1) + ", Col No. " + (x[2] + 1));
					i++;
					if (i > nPassengers)
						return;
				}
			}
			z++;
			if (z == 3)
				z = 0;
		}
	}

	private static void tagSeats(List<int[][]> ls, List<int[]> seating) {
		for (int[] x : seating) {
			ls.add(new int[x[1]][x[0]]);
		}
		tagWindow(ls, seating);
		tagAisle(ls, seating);
	}

	private static void tagWindow(List<int[][]> ls, List<int[]> seating) {
		int[][] x = ls.get(0);
		for (int i = 0; i < x.length; i++) {
			x[i][0] = 2;
		}
		int y = seating.size() - 1;
		x = ls.get(y);
		for (int i = 0; i < x.length; i++) {
			x[i][x[0].length - 1] = 2;
		}
	}

	private static void tagAisle(List<int[][]> ls, List<int[]> seating) {
		for (int[][] x : ls) {
			int r = x.length, c = x[0].length;
			for (int i = 0; i < r; i++) {
				if (x[i][0] == 0)
					x[i][0] = 1;
				if (x[i][c - 1] == 0)
					x[i][c - 1] = 1;
			}
		}
	}

	private static void getOrder(Queue<int[]> q, List<int[]> seating) {
		int stop = seating.get(0)[1];
		for (int[] x : seating)
			stop = Math.max(x[1], stop);
		for (int i = 0; i < stop; i++) {
			for (int j = 0; j < seating.size(); j++) {
				int r = seating.get(j)[1], c = seating.get(j)[0];
				if (i >= r)
					continue;
				for (int k = 0; k < c; k++) {
					q.add(new int[] { j, i, k });
				}
			}

		}
	}

}
