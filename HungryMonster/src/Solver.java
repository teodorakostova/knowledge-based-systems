import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
		Map map = new Map(pathToMap);
		MapCell start = new MapCell(startX, startY, map);
		MapCell goal = new MapCell(goalX, goalY, map);
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
		map.printMap();
		//System.out.println(goal.getxCoord() + "  " + goal.getyCoord());
		//System.out.println(goal);
		Queue<MapCell> open = new PriorityQueue<>();
		List<MapCell> closed = new ArrayList<>();
		
		open.add(start);
		start.setgScore(0);
		start.setfValue(start.findDistanceTo(goal) + start.getgScore());
		
		while (!open.isEmpty()) {
			MapCell current = open.poll();
			
			closed.add(current);
			if (current.equals(goal)) {
				System.out.println("PATH FOUND");
				reconstructPath(current, start, map);
				return;
			}
			List<MapCell> children = current.getChildren();
			for (MapCell child : children) {
				if (closed.contains(child)) {
					continue;
				}
				
				double newGScore = current.getgScore() + current.findMovementCostTo(child);
				
				if (newGScore >= child.getgScore()) {
					continue;
				}
				
				child.setgScore(newGScore);
				child.setfValue(child.getgScore() + child.findDistanceTo(goal));
				child.setParent(current);
				
				if (!open.contains(child)) {
					open.add(child);
				}
			}
		}
		
	}
	
	public void reconstructPath(MapCell begin, MapCell end, Map map) {
		Stack<MapCell> path = new Stack<>();
		while (!begin.equals(end)) {
			path.push(begin);
			begin = begin.getParent();
		}
		dummyPrintVisited(map, path);
		while (!path.isEmpty()) {
			System.out.println(path.pop());
		}
	}
	
	public void dummyPrintVisited(Map map, List<MapCell> path) {
		int pathSize = path.size();
		ArrayList<List<Character>> mazeMap = map.getMazeMap();
		
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
