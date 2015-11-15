import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class Map.
 */
public class Map {
	
	/** The maze map. */
	private ArrayList<String[]> mazeMap = new ArrayList<String[]>();
	
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
	public char getCellCharAtPos(int x, int y) throws ArrayIndexOutOfBoundsException{		
		return mazeMap.get(y)[x].charAt(0);
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
				mazeMap.add(line.split(","));
				rowsCount++;
			}
			
			this.height = rowsCount;
			this.width = mazeMap.get(0).length;
			
			
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
		for (String[] l : mazeMap) {
			for (String c : l) {
				System.out.print(c + '|');				
			}
			System.out.println();
			
		}
	}
	
}
