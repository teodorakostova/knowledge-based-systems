import java.util.ArrayList;


/**
 * The Class MapCell.
 */
public class MapCell implements Comparable<MapCell> {
	
	/** The x coord. */
	private int xCoord;
	
	/** The y coord. */
	private int yCoord;
	
	/** The parent. */
	private MapCell parent;	
	
	/** The movement cost. */
	private double movementCost;
	
	/** The manhattan distance. */
	private int manhattanDistance;
		
	/**
	 * Instantiates a new map cell.
	 *
	 * @param xCoord
	 *            the x coord
	 * @param yCoord
	 *            the y coord
	 */
	public MapCell(int xCoord, int yCoord) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**
	 * Instantiates a new map cell.
	 *
	 * @param xCoord
	 *            the x coord
	 * @param yCoord
	 *            the y coord
	 * @param parent
	 *            the parent
	 * @param movementCost
	 *            the movement cost
	 */
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
	
	/**
	 * Calculate manhattan distance.
	 *
	 * @param goal
	 *            the goal
	 */
	public void calculateManhattanDistance(MapCell goal) {
		manhattanDistance = Math.abs(goal.getxCoord() - xCoord) + Math.abs(goal.getyCoord() - yCoord);
	}
	
	/**
	 * Gets the manhattan distance.
	 *
	 * @return the manhattan distance
	 */
	public int getManhattanDistance() {
		return manhattanDistance;
	}
	
	/**
	 * Gets the movement cost.
	 *
	 * @return the movement cost
	 */
	public double getMovementCost() {
		return movementCost;
	}
	
	/**
	 * Gets the x coord.
	 *
	 * @return the x coord
	 */
	public int getxCoord() {
		return xCoord;
	}
	
	/**
	 * Sets the x coord.
	 *
	 * @param xCoord
	 *            the new x coord
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * Gets the y coord.
	 *
	 * @return the y coord
	 */
	public int getyCoord() {
		return yCoord;
	}
	
	/**
	 * Sets the y coord.
	 *
	 * @param yCoord
	 *            the new y coord
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	 
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[xCoord=" + xCoord + ", yCoord=" + yCoord + "]";
	}

	
	/**
	 * Gets the fvalue.
	 *
	 * @return the fvalue
	 */
	public double getFvalue() {		
		return movementCost + getManhattanDistance();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MapCell arg0) {
		return Double.compare(this.getFvalue(), arg0.getFvalue());
	}
	
	/**
	 * Gets the children.
	 *
	 * @param map
	 *            the map
	 * @param goal
	 *            the goal
	 * @return the children
	 */
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
	
	/**
	 * Move.
	 *
	 * @param children
	 *            the children
	 * @param map
	 *            the map
	 * @param goal
	 *            the goal
	 * @param xPos
	 *            the x pos
	 * @param yPos
	 *            the y pos
	 * @param isDiagonal
	 *            the is diagonal
	 */
	public void move(ArrayList<MapCell> children, 
					Map map, 
					MapCell goal, 
					int xPos, 
					int yPos, 
					boolean isDiagonal) {
		
		if (!inBounds(map, xPos, yPos)) {
			return;
		}
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
	
	/**
	 * In bounds.
	 *
	 * @param map
	 *            the map
	 * @param xPos
	 *            the x pos
	 * @param yPos
	 *            the y pos
	 * @return true, if successful
	 */
	private boolean inBounds(Map map, int xPos, int yPos) {
		return xPos >= 0 && xPos < map.getWidth() && yPos >= 0 && yPos < map.getHeight();
	}
	
}
