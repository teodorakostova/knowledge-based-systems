import java.util.Comparator;

public class MapCell implements Comparator<MapCell>{
	
	private int xCoord;
	private int yCoord;
	private MapCell parent;	
	private int movementCost;
	private int manhattanDistance;

	public MapCell(int xCoord, int yCoord) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public MapCell(int xCoord, int yCoord, MapCell parent, int movementCost) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.parent = parent;
		this.movementCost = parent.getMovementCost() + movementCost;
	}
	
	public int calculateManhattanDistance(MapCell goal) {
		return Math.abs(goal.getxCoord() - xCoord) + Math.abs(goal.getyCoord() - yCoord);
	}
	
	public int getManhattanDistance() {
		return 0;
	}
	
	public int getMovementCost() {
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
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

	@Override
	public int compare(MapCell o1, MapCell o2) {
		// TODO Auto-generated method stub
		return o1.getFvalue() + o2.getManhattanDistance();
	}
	
	public int getFvalue() {		
		return movementCost + getManhattanDistance();
	}
	
	
}
