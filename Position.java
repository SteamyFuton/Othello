public abstract class Position {
    private char piece;
    public static final char EMPTY = '.';
    public static final char BLACK = '@';
    public static final char WHITE = 'O';

    public Position(char piece) {
        this.piece = piece;
    }

    public char getPiece() {
        return piece;
    }

    //return true if position is playable, false otherwise
    public abstract boolean canPlay();
}
