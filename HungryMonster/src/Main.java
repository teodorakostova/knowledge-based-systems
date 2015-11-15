public class Main {

	public static void main(String[] args) {
		if (args.length < 5) {
			System.out.println("Please specify path to file and coordinates of starting and ending point");
			return;
		}
		String pathToMap = args[0];
		Solver solver = Solver.getInstance();
		solver.findPathToGoal(pathToMap, Integer.parseInt(args[1]), Integer.parseInt(args[2]),
				Integer.parseInt(args[3]), Integer.parseInt(args[4]));
	}

}
