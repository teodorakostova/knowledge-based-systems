
public class Main {

	public static void main(String[] args) {
		//String filePath = args[0];
		String filePath = "C:\\Users\\Teodora\\Downloads\\mymap.csv";
		Map start = new Map(filePath);
		start.printMap();
	}

}
