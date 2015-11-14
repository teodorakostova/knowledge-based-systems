import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.print.attribute.standard.PrinterName;

public class Solver {
	
	private Solver() {
		
	}
	
	public void findPathToGoal(String pathToMap, int startX, int startY, int goalX, int goalY) {
		MapCell start = new MapCell(startX, startY);
		MapCell goal = new MapCell(goalX, goalY);
		Map map = new Map(pathToMap);
		solve(start, goal, map);
		
	}
	
	public static Solver getSolver() {
		return new Solver();
	}
	
	private void solve(MapCell start, MapCell goal, Map map) {
		map.printMap();
		List<MapCell> result = new ArrayList<>();
		List<Integer> closed = new ArrayList<>();
		PriorityQueue<MapCell> open = new PriorityQueue<>();
		start.calculateManhattanDistance(goal);
		open.add(start);
		closed.add(start.hashCode());
		int steps = 0;
		while (open.isEmpty() == false) {
			MapCell current = open.poll();
			closed.add(current.hashCode());
			System.out.println("Current " + current);
			steps++;
			if (steps >= 200) {
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
					System.out.println("Child: " + child);
					open.add(child);
					closed.add(child.hashCode());
				}
			}
		}

	}
	
	
	
}
