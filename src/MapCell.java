import java.util.ArrayList;
import java.util.List;

public class MapCell implements Comparable<MapCell>{
	
	private int xCoord;
	
	private int yCoord;
	
	private Map map;
	
	private double gScore;
	
	private double fValue;
	
	private MapCell parent;
	
	public MapCell(int xCoord, int yCoord, Map map) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.gScore = Double.MAX_VALUE;
		this.fValue = Double.MAX_VALUE;
		this.parent = null;
		this.setMap(map);
	}
	
	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * @return the gScore
	 */
	public double getgScore() {
		return gScore;
	}

	/**
	 * @param gScore the gScore to set
	 */
	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	/**
	 * @return the fValue
	 */
	public double getfValue() {
		return fValue;
	}

	/**
	 * @param fValue the fValue to set
	 */
	public void setfValue(double fValue) {
		this.fValue = fValue;
	}

	/**
	 * @return the parent
	 */
	public MapCell getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(MapCell parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapCell [xCoord=" + xCoord + ", yCoord=" + yCoord + "]" 
				+ " f: " + fValue + " g: " + gScore ;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MapCell)) {
			return false;
		}
		MapCell other = (MapCell) obj;
		if (xCoord != other.xCoord) {
			return false;
		}
		if (yCoord != other.yCoord) {
			return false;
		}
		return true;
	}

	public boolean isCellInBounds() {
		return map.areCoordinatesInBounds(this.xCoord, this.yCoord);
	}
	
	public boolean isCellPassable() {
		return map.isWall(this.xCoord, this.yCoord);
	}
	
	private boolean isDiagonalTo(MapCell mapCell) {
		return ((xCoord == mapCell.getxCoord() - 1 && yCoord == mapCell.getyCoord() - 1) ||
				(xCoord == mapCell.getxCoord() + 1 && yCoord == mapCell.getyCoord() + 1) ||
				(xCoord == mapCell.getxCoord() - 1 && yCoord == mapCell.getyCoord() + 1) ||
				(xCoord == mapCell.getxCoord() + 1 && yCoord == mapCell.getyCoord() - 1));
	}
	
	public double findDistanceTo(MapCell mapCell) {
		return Math.abs(mapCell.getxCoord() - xCoord) + Math.abs(mapCell.getyCoord() - yCoord);
	}
	
	public double findMovementCostTo(MapCell mapCell) {
		if (map.isWater(mapCell.getxCoord(), mapCell.getyCoord())) {
			return Utils.waterCost;
		}
		if (isDiagonalTo(mapCell)) {
			return Utils.diagonalCost;
		}
		return Utils.straightCost;
	}
	
	public List<MapCell> getChildren() {
		List<MapCell> children = new ArrayList<>();
		addNewChild(children, xCoord + 1, yCoord);
		addNewChild(children, xCoord - 1, yCoord);
		addNewChild(children, xCoord, yCoord + 1);
		addNewChild(children, xCoord, yCoord - 1);
		addNewChild(children, xCoord + 1, yCoord + 1);
		addNewChild(children, xCoord - 1, yCoord - 1);
		addNewChild(children, xCoord - 1, yCoord + 1);
		addNewChild(children, xCoord + 1, yCoord - 1);
		return children;
	}
	
	/**
	 * Adds a child of this cell if it is passable and is in bounds
	 * of the map
	 * @param children the children
	 * @param childXCoord the new child x coordinates
	 * @param childYCoord the new child y coordinates
	 */
	private void addNewChild(List<MapCell> children, int childXcoord, int childYcoord) {
		if (map.areCoordinatesInBounds(childXcoord, childYcoord) && !map.isWall(childXcoord, childYcoord)) {
			MapCell child = new MapCell(childXcoord, childYcoord, map);
			children.add(child);
		}
	}

	@Override
	public int compareTo(MapCell o) {
		if (Double.compare(o.getfValue(), fValue) == 1)
			return -1;
		else if (Double.compare(o.getfValue(), fValue) == -1) {
			return 1;
		}
		return 0;
	}
	
}