public class Utilities {
	public static final char emptyCell = ' ';
	
	public static final char maximisingPlayerSymbol = 'O';
	
	public static final char minimisingPlayerSymbol = 'X';
	
	public static char getCurrentPlayerSymbol(boolean isMaximising) {
		return isMaximising ? maximisingPlayerSymbol : minimisingPlayerSymbol;
	}
	
	public static Character getCurrentPlayerSymbolWrapped(boolean isMaximising) {
		return Character.valueOf(getCurrentPlayerSymbol(isMaximising));
	}
}
