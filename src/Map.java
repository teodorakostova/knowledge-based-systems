import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Map {
	
	private ArrayList<Object> mazeMap = new ArrayList<>();
	
	public Map(String filePath) {
		loadMap(filePath);
	}
	
	private void loadMap(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			//for (int i = 0; i < line.length(); i++) {
				mazeMap.add(line.split(","));
			//}
			System.out.println(line);
			for (int i = 0; i < line.length(); i++) {
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file on the specified path");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An exception occured while opening input file.");
			e.printStackTrace();
		}
	}
	
	public void printMap() {
		for (int i = 0; i < mazeMap.size(); i++) {
			System.out.print(mazeMap.get(i));
			
		}
	}
	
	public ArrayList<MapCell> findPath(MapCell start, MapCell end) {
		ArrayList<MapCell> result = new ArrayList<>();
		PriorityQueue<MapCell> q = new PriorityQueue<>(); 
		return result;
	}
}
