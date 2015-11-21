import java.util.ArrayList;
import java.util.List;

public class GameState {
	private List<List<Character>> currentState;
	private boolean isMaximisingPlayer;
	
	
	public GameState(List<List<Character>> currentState, boolean isMaximisingPlayer) {
		this.currentState = currentState;
		this.isMaximisingPlayer = isMaximisingPlayer;
	}
	
	public List<GameState> getPossibleMoves() {
		List<GameState> possibleMoves = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (currentState.get(i).get(j).charValue() == Utilities.emptyCell) {
					List<List<Character>> childStateRepresentation = currentState;
					List<Character> currentLine = currentState.get(i);
					currentLine.add(j , Utilities.getCurrentPlayerSymbolWrapped(isMaximisingPlayer));
					childStateRepresentation.add(i, currentLine);
					
					possibleMoves.add(new GameState(childStateRepresentation, !isMaximisingPlayer));
				}
			}
		}
		return possibleMoves;
		
	}
	
	public void printGameState() {
		for (List<Character> line : currentState) {
			for (Character c : line) {
				System.out.print(c + "|");
			}
			System.out.println();
		}
	}
}
