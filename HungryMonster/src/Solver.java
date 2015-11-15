import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The Class Solver.
 */
public class Solver {

	/** The solver. */
	private static Solver solver = null;

	/**
	 * Used only for preventing instantiation.
	 */
	private Solver() {

	}

	/**
	 * Find path to goal.
	 *
	 * @param pathToMap
	 *            the path to map
	 * @param startX
	 *            the start x
	 * @param startY
	 *            the start y
	 * @param goalX
	 *            the goal x
	 * @param goalY
	 *            the goal y
	 */
	public void findPathToGoal(String pathToMap, int startX, int startY, int goalX, int goalY) {
		MapCell start = new MapCell(startX, startY);
		MapCell goal = new MapCell(goalX, goalY);
		Map map = new Map(pathToMap);
		solve(start, goal, map);
	}

	/**
	 * Gets the single instance of Solver.
	 *
	 * @return single instance of Solver
	 */
	public static Solver getInstance() {
		if (solver == null) {
			solver = new Solver();
		}
		return solver;
	}

	/**
	 * Find path to goal using A* algorithm.
	 *
	 * @param start
	 *            the start
	 * @param goal
	 *            the goal
	 * @param map
	 *            the map
	 */
	private void solve(MapCell start, MapCell goal, Map map) {
		System.out.println(map.getHeight() + " width: " + map.getWidth());
		List<Integer> closed = new ArrayList<>();
		List<MapCell> path = new ArrayList<>();
		PriorityQueue<MapCell> open = new PriorityQueue<>();
		start.calculateManhattanDistance(goal);
		open.add(start);
		closed.add(start.hashCode());
		int steps = 0;
		while (open.isEmpty() == false) {
			MapCell current = open.poll();
			closed.add(current.hashCode());
			path.add(current);
			steps++;
			if (steps >= 2000) {
				System.out.println("Cannot solve");
				break;
			}
			if (current.equals(goal)) {
				System.out.println("Goal reached");
				break;
			}
			ArrayList<MapCell> children = current.getChildren(map, goal);
			for (MapCell child : children) {
				if (!closed.contains(child.hashCode())) {
					open.add(child);
					closed.add(child.hashCode());
				}
			}
		}

		System.out.println("Steps: " + (path.size() - 1));
		printSteps(path);
		dummyPrintVisited(map, path);
	}

	public void printSteps(List<MapCell> path) {
		for (MapCell step : path) {
			System.out.println(step);
		}
	}

	public void dummyPrintVisited(Map map, List<MapCell> path) {
		ArrayList<List<Character>> mazeMap = map.getMazeMap();
		int pathSize = path.size();
		for (int i = 0; i < mazeMap.size(); i++) {
			for (int j = 0; j < mazeMap.get(i).size(); j++) {
				boolean f = false;
				for (int z = 0; z < pathSize; z++) {
					if (path.get(z).getxCoord() == i && path.get(z).getyCoord() == j) {
						System.out.print("#" + '|');
						f = true;
					}

				}
				if (!f)
					System.out.print(map.getCellCharAtPos(i, j).toString() + '|');
			}
			System.out.println();
		}
	}

}
