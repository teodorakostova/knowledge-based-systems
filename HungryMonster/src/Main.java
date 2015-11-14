
public class Main {

	public static void main(String[] args) {
		//String filePath = args[0];
		String pathToMap = "C:\\Users\\Teodora\\Downloads\\mymap.csv";
		Solver solver = Solver.getSolver();
		solver.findPathToGoal(pathToMap, 0, 0, 5, 5);
	}

}
