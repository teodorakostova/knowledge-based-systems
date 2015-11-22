import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Map.
 */
public class Map {
	
	/** The maze map. */
	private ArrayList<List<Character>> mazeMap = new ArrayList<>();
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	
	/**
	 * Instantiates a new map.
	 *
	 * @param filePath
	 *            the file path
	 */
	public Map(String filePath) {
		loadMap(filePath);
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	public ArrayList<List<Character>> getMazeMap() {
		return mazeMap;
	}
	
	/**
	 * Gets the cell char at position.
	 *
	 * @param x
	 *            the x coordinates
	 * @param y
	 *            the y coordinates
	 * @return the cell char at the specified position
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public Character getCellCharAtPos(int x, int y) throws ArrayIndexOutOfBoundsException{		
		return mazeMap.get(x).get(y);
	}
	
	/**
	 * Load map.
	 *
	 * @param filePath
	 *            the file path
	 */
	private void loadMap(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = null;			
			int rowsCount = 0;
			while ((line = br.readLine()) != null) {
				//mazeMap.add(line.split(","));
				List<Character> currentLine = new ArrayList<Character>();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) != ',') {
						currentLine.add(Character.valueOf(line.charAt(i)));
					}
				}
				mazeMap.add(currentLine);
				rowsCount++;
			}
			
			this.height = rowsCount;
			this.width = mazeMap.get(0).size();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file on the specified path");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An exception occured while opening input file.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("The loaded map is empty");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Prints the map.
	 */
	public void printMap() {
		for (List<Character> line : mazeMap) {
			for (Character c : line) {
				System.out.print(c.charValue() + "" + '|');				
			}
			System.out.println();
			
		}
	}
	
	public boolean areCoordinatesInBounds(int xCoord, int yCoord) {
		return (yCoord >= 0 && yCoord < this.getWidth() && xCoord >= 0 && xCoord < this.getHeight());
	}
	
	public boolean isWall(int xCoord, int yCoord) {
		return getCellCharAtPos(xCoord, yCoord) == Utils.wallSymbol;
	}
	
	public boolean isWater(int xCoord, int yCoord) {
		return getCellCharAtPos(xCoord, yCoord) == Utils.wallSymbol;
	}
	
}
