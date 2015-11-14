import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Map {
	
	private ArrayList<String[]> mazeMap = new ArrayList<String[]>();
	private int width;
	private int height;
	
	public Map(String filePath) {
		loadMap(filePath);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char getCellCharAtPos(int x, int y) throws ArrayIndexOutOfBoundsException{		
		return mazeMap.get(y)[x].charAt(0);
	}
	
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
	
	public void printMap() {
		for (String[] l : mazeMap) {
			for (String c : l) {
				System.out.print(c + '|');				
			}
			System.out.println();
			
		}
	}
	
}
