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
		List<Integer> closed = new ArrayList<>();
		PriorityQueue<MapCell> open = new PriorityQueue<>();
		start.calculateManhattanDistance(goal);
		open.add(start);
		closed.add(start.hashCode());
		int steps = 0;
		while (open.isEmpty() == false) {
			MapCell current = open.poll();
			closed.add(current.hashCode());
			System.out.println(current);
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
	}

}
