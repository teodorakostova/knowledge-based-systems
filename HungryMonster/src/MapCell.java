import java.util.ArrayList;


public class MapCell implements Comparable<MapCell> {
	
	private int xCoord;
	private int yCoord;
	private MapCell parent;	
	private double movementCost;
	private int manhattanDistance;
		
	public MapCell(int xCoord, int yCoord) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public MapCell(int xCoord, int yCoord, MapCell parent, double movementCost) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.parent = parent;
		if (parent == null) {
			this.movementCost = 0 + movementCost;
		}
		else {
			this.movementCost = parent.getMovementCost() + movementCost;
		}
	}
	
	public void calculateManhattanDistance(MapCell goal) {
		manhattanDistance = Math.abs(goal.getxCoord() - xCoord) + Math.abs(goal.getyCoord() - yCoord);
	}
	
	public int getManhattanDistance() {
		return manhattanDistance;
	}
	
	public double getMovementCost() {
		return movementCost;
	}
	
	public int getxCoord() {
		return xCoord;
	}
	
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public int getyCoord() {
		return yCoord;
	}
	
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	 
	public MapCell getParent() {
		return parent;
	}
	
	public void setParent(MapCell parent) {
		this.parent = parent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapCell other = (MapCell) obj;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MapCell [xCoord=" + xCoord + ", yCoord=" + yCoord + "]";
	}

	
	public double getFvalue() {		
		return movementCost + getManhattanDistance();
	}
	
	@Override
	public int compareTo(MapCell arg0) {
		return Double.compare(this.getFvalue(), arg0.getFvalue());
	}
	
	public ArrayList<MapCell> getChildren(Map map, MapCell goal) {
		ArrayList<MapCell> children = new ArrayList<>();
		
		move(children, map, goal, xCoord + 1, yCoord, false); // east
		move(children, map, goal, xCoord - 1, yCoord, false); // west
		move(children, map, goal, xCoord, yCoord - 1, false); // south
		move(children, map, goal, xCoord, yCoord + 1, false); // north
		move(children, map, goal, xCoord + 1, yCoord - 1, true); // north-east
		move(children, map, goal, xCoord - 1, yCoord - 1, true); // north-west
		move(children, map, goal, xCoord + 1, yCoord + 1, true); // south-east
		move(children, map, goal, xCoord - 1, yCoord + 1, true); // south-west
		
		return children;
	}
	
	public void move(ArrayList<MapCell> children, Map map, MapCell goal, int xPos, int yPos, boolean isDiagonal) {
		if (!inBounds(map, xPos, yPos)) {
			System.out.println("not in bounds " + xPos + " y: " + yPos);
			return;
		}
		//System.out.println("Try to move X: " + xPos + " Y: " + yPos);
		char cellSymbol = map.getCellCharAtPos(xPos, yPos);		
		if (cellSymbol == Utils.wallSymbol) {
			return;
		}
		double childMovementCost = 0;
		if (cellSymbol == Utils.waterSymbol) {
			childMovementCost = 2;
		} else {
			childMovementCost = isDiagonal ? Utils.diagonalCost : Utils.straightCost;
		}
		MapCell child = new MapCell(xPos, yPos, this, childMovementCost);
		child.calculateManhattanDistance(goal); // TODO: refactor this
		children.add(child);
	}
	
	private boolean inBounds(Map map, int xPos, int yPos) {
		return xPos >= 0 && xPos < map.getWidth() && yPos >= 0 && yPos < map.getHeight();
	}

	
	
}
