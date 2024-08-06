import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private Position[][] board;
    private int row;
    private int col;
    private Scanner Sc = new Scanner(System.in);

    public Board(){
        board = new Position[8][8];
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    //draw board
    public void drawBoard() {
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int row = 0; row < 8; row++) {
            System.out.print((row) + "\t");
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col].getPiece() + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new PlayablePosition(Position.EMPTY);
            }
        }
    }

    public void Position0() {
            System.out.print("Position 0\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board[row][col] = new PlayablePosition(Position.EMPTY);
                }
            }

            for (int row = 2; row <= 5; row++) {
                for (int col = 2; col <= 5; col++) {
                    board[row][col] = new UnplayablePosition(Position.WHITE);
                }
            }
            for (int row = 2; row <= 3; row++) {
                for (int col = 4; col <= 5; col++) {
                    board[row][col] = new UnplayablePosition(Position.BLACK);
                }
            }
            for (int row = 4; row <= 5; row++) {
                for (int col = 2; col <= 3; col++) {
                    board[row][col] = new UnplayablePosition(Position.BLACK);
                }
            }
        }
    public void Position1() {
            System.out.print("Position 1\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board[row][col] = new PlayablePosition(Position.EMPTY);;
                }
            }

            board[2][2] = new UnplayablePosition(Position.WHITE);;
            board[3][3] = new UnplayablePosition(Position.WHITE);;
            board[2][3] = new UnplayablePosition(Position.BLACK);
            board[3][2] = new UnplayablePosition(Position.BLACK);;
        }
    public void Position2() {
            System.out.print("Position 2\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board[row][col] = new PlayablePosition(Position.EMPTY);;
                }
            }

            board[2][4] = new UnplayablePosition(Position.WHITE);
            board[3][5] = new UnplayablePosition(Position.WHITE);;
            board[2][5] = new UnplayablePosition(Position.BLACK);;
            board[3][4] = new UnplayablePosition(Position.BLACK);;
        }
    public void Position3() {
            System.out.print("Position 3\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board[row][col] = new PlayablePosition(Position.EMPTY);
                }
            }

            board[4][2] = new UnplayablePosition(Position.WHITE);;
            board[5][3] = new UnplayablePosition(Position.WHITE);
            board[4][3] = new UnplayablePosition(Position.BLACK);
            board[5][2] = new UnplayablePosition(Position.BLACK);;
        }
    public void Position4() {
            System.out.print("Position 4\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board[row][col] = new PlayablePosition(Position.EMPTY);;
                }
            }

            board[4][4] = new UnplayablePosition(Position.WHITE);
            board[5][5] = new UnplayablePosition(Position.WHITE);
            board[4][5] = new UnplayablePosition(Position.BLACK);
            board[5][4] = new UnplayablePosition(Position.BLACK);
    }

    public void displayStartingPositions(){
        Board temp_board = new Board();
        temp_board.initializeBoard();

        System.out.println("Here are the starting positions:");

        temp_board.Position0();
        temp_board.drawBoard();

        temp_board.Position1();
        temp_board.drawBoard();

        temp_board.Position2();
        temp_board.drawBoard();

        temp_board.Position3();
        temp_board.drawBoard();

        temp_board.Position4();
        temp_board.drawBoard();
    }

    //choose a starting position
    public void choosePosition(){
        int chosen_position = -1;

        while (chosen_position < 0 || chosen_position > 4) {
            System.out.println("Choose your starting position (0-4):");

            try {
                chosen_position = Sc.nextInt();
                if (chosen_position < 0 || chosen_position > 4) {
                    System.out.println("Invalid position. Choose a number from 0 to 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                Sc.nextLine();  // Clear the invalid input from the scanner
            }
        }

        switch (chosen_position) {
            case 0: Position0();
                break;
            case 1: Position1();
                break;
            case 2: Position2();
                break;
            case 3: Position3();
                break;
            case 4: Position4();
                break;
        }
    }

    // choose a position on the board to place a piece
    public void chooseMove(Player thecurrentplayer) {
        boolean validMove = false;

        while (!validMove) {
                System.out.println(thecurrentplayer.getColour()
                        + ", Player" + thecurrentplayer.getPlayer_number() + ", "
                        + thecurrentplayer.getName() + ", choose where to place your piece (row column):");

                try {
                    row = Sc.nextInt();
                    col = Sc.nextInt();
                    Sc.nextLine();

                    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
                        throw new ArrayIndexOutOfBoundsException("Invalid input. Please enter a valid move (row column).");
                    }
                    validMove = true;
                } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Please enter an action again (1-4).");
                    Sc.nextLine();  // Clear the invalid input from the scanner
            }
        }
    }

    // place a piece
    public void placePiece(Player thecurrentplayer) {
        if (thecurrentplayer.getPlayer_number()==1) {
            board[row][col]= new UnplayablePosition(Position.BLACK);
        }
        else {
            board[row][col] = new UnplayablePosition(Position.WHITE);
        }
    }

    // check if the space is not already occupied by a piece on the board
    public boolean checkEmptySquare(int row, int col){
        return board[row][col].canPlay();
    }

    //display positions that the current player may play legally
    public void markPlayablePositions(Player currentplayer){
        Player temp_player;
        if (currentplayer.getColour().equals("Black")) {
            temp_player = new Player("White", 'O');
        }
        else {
            temp_player = new Player("Black", '@');
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].canPlay()) {
                    if (checkNorth(row, col, currentplayer, temp_player) ||
                            checkNorthEast(row, col, currentplayer, temp_player) ||
                            checkEast(row, col, currentplayer, temp_player) ||
                            checkSouthEast(row, col, currentplayer, temp_player) ||
                            checkSouth(row, col, currentplayer, temp_player) ||
                            checkSouthWest(row, col, currentplayer, temp_player) ||
                            checkWest(row, col, currentplayer, temp_player) ||
                            checkNorthWest(row, col, currentplayer, temp_player)) {
                        board[row][col] = new PlayablePosition(Position.EMPTY);
                    }
                    else board[row][col] = new UnplayablePosition(UnplayablePosition.UNPLAYABLE);
                }
            }
        }
    }
    public boolean checkNorth(int row, int col, Player current, Player temp) {
        if (row>=2 && row <8) {
            if (board[row-1][col].getPiece() == temp.getSymbol()
                    && board[row-2][col].getPiece() == current.getSymbol())
                return true;

            if (board[row-1][col].getPiece() == temp.getSymbol()
                    && board[row-2][col].getPiece() == temp.getSymbol())
                return checkNorth(row-1, col, current, temp);
        }
        return false;
    }
    public boolean checkNorthEast(int row, int col, Player current, Player temp){
        if (row>=2 && row <8 && col>=0 && col <6){
            if (board[row-1][col+1].getPiece() == temp.getSymbol()
                    && board[row-2][col+2].getPiece() == current.getSymbol())
                return true;

            if (board[row-1][col+1].getPiece() == temp.getSymbol()
                    && board[row-2][col+2].getPiece() == temp.getSymbol())
                return checkNorthEast(row-1, col+1, current, temp);
        }
        return false;
    }
    public boolean checkEast(int row, int col, Player current, Player temp){
        if (col>=0 && col <6) {
            if (board[row][col+1].getPiece() == temp.getSymbol()
                    && board[row][col+2].getPiece() == current.getSymbol())
                return true;

            if (board[row][col+1].getPiece() == temp.getSymbol()
                    && board[row][col+2].getPiece() == temp.getSymbol())
                return checkEast(row, col+1, current, temp);
        }
        return false;
    }
    public boolean checkSouthEast(int row, int col, Player current, Player temp){
        if (row>=0 && row <6 && col>=0 && col <6){
            if (board[row+1][col+1].getPiece() == temp.getSymbol()
                    && board[row+2][col+2].getPiece() == current.getSymbol())
                return true;

            if (board[row+1][col+1].getPiece() == temp.getSymbol()
                    && board[row+2][col+2].getPiece() == temp.getSymbol())
                return checkSouthEast(row+1, col+1, current, temp);
        }
        return false;
    }
    public boolean checkSouth(int row, int col, Player current, Player temp){
        if (row>=0 && row <6){
            if (board[row+1][col].getPiece() == temp.getSymbol()
                    && board[row+2][col].getPiece() == current.getSymbol())
                return true;

            if (board[row+1][col].getPiece() == temp.getSymbol()
                    && board[row+2][col].getPiece() == temp.getSymbol())
                return checkSouth(row+1, col, current, temp);
        }
        return false;
    }
    public boolean checkSouthWest(int row, int col, Player current, Player temp){
        if (row >= 0 && row <6 && col>=2 && col <8) {
            if (board[row+1][col-1].getPiece() == temp.getSymbol()
                    && board[row+2][col-2].getPiece() == current.getSymbol())
                return true;

            if (board[row+1][col-1].getPiece() == temp.getSymbol()
                    && board[row+2][col-2].getPiece() == temp.getSymbol())
                return checkSouthWest(row+1, col-1, current, temp);
        }
        return false;
    }
    public boolean checkWest(int row, int col, Player current, Player temp){
        if (col>=2 && col <8) {
            if (board[row][col-1].getPiece() == temp.getSymbol()
                    && board[row][col-2].getPiece() == current.getSymbol())
                return true;

            if (board[row][col-1].getPiece() == temp.getSymbol()
                    && board[row][col-2].getPiece() == temp.getSymbol())
                return checkWest(row, col-1, current, temp);
        }
        return false;
    }
    public boolean checkNorthWest(int row, int col, Player current, Player temp){
        if (row >= 2 && row <8 && col>=2 && col <8) {
            if (board[row-1][col-1].getPiece() == temp.getSymbol()
                    && board[row-2][col-2].getPiece() == current.getSymbol())
                return true;

            if (board[row-1][col-1].getPiece() == temp.getSymbol()
                    && board[row-2][col-2].getPiece() == temp.getSymbol())
                return checkNorthWest(row-1,col-1, current, temp);
        }
        return false;
    }

    public void resetBoard(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].getPiece() == UnplayablePosition.UNPLAYABLE){
                    board[row][col] = new PlayablePosition(Position.EMPTY);
                }
            }
        }
    }

    public void flipPieces(Player currentplayer){
        Player temp_player;
        if (currentplayer.getColour().equals("Black")) {
            temp_player = new Player("White", 'O');
        }
        else {
            temp_player = new Player("Black", '@');
        }

        flipNorth(row, col, currentplayer, temp_player);
        flipNorthEast(row, col, currentplayer, temp_player);
        flipEast(row, col, currentplayer, temp_player);
        flipSouthEast(row, col, currentplayer, temp_player);
        flipSouth(row, col, currentplayer, temp_player);
        flipSouthWest(row, col, currentplayer, temp_player);
        flipWest(row, col, currentplayer, temp_player);
        flipNorthWest(row, col, currentplayer, temp_player);
    }
    public void flipNorth(int row, int col, Player current, Player temp) {
        if (checkNorth(row, col, current, temp)) {
            int rows = row - 1;
            while (board[rows][col].getPiece() == temp.getSymbol()) {
                if (board[rows][col].getPiece() == '@') {
                    board[rows][col] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[rows][col] = new UnplayablePosition(Position.BLACK);
                }
                rows--;
            }
        }
    }
    public void flipNorthEast(int row, int col, Player current, Player temp){
        if (checkNorthEast(row, col, current, temp)) {
            int rows = row - 1;
            int cols = col + 1;
            while (board[rows][cols].getPiece() == temp.getSymbol()) {
                if (board[rows][cols].getPiece() == '@'){
                    board[rows][cols] = new UnplayablePosition(Position.WHITE);
                }
                else board[rows][cols] = new UnplayablePosition(Position.BLACK);
                rows--;
                cols++;
            }
        }
    }
    public void flipEast(int row, int col, Player current, Player temp) {
        if (checkEast(row, col, current, temp)) {
            int cols = col + 1;
            while (board[row][cols].getPiece() == temp.getSymbol()) {
                if (board[row][cols].getPiece() == '@') {
                    board[row][cols] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[row][cols] = new UnplayablePosition(Position.BLACK);
                }
                cols++;
            }
        }
    }
    public void flipSouthEast(int row, int col, Player current, Player temp) {
        if (checkSouthEast(row, col, current, temp)) {
            int rows = row + 1;
            int cols = col + 1;
            while (board[rows][cols].getPiece() == temp.getSymbol()) {
                if (board[rows][cols].getPiece() == '@') {
                    board[rows][cols] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[rows][cols] = new UnplayablePosition(Position.BLACK);
                }
                rows++;
                cols++;
            }
        }
    }
    public void flipSouth(int row, int col, Player current, Player temp) {
        if (checkSouth(row, col, current, temp)) {
            int rows = row + 1;
            while (board[rows][col].getPiece() == temp.getSymbol()) {
                if (board[rows][col].getPiece() == '@') {
                    board[rows][col] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[rows][col] = new UnplayablePosition(Position.BLACK);
                }
                rows++;
            }
        }
    }
    public void flipSouthWest(int row, int col, Player current, Player temp) {
        if (checkSouthWest(row, col, current, temp)) {
            int rows = row + 1;
            int cols = col - 1;
            while (board[rows][cols].getPiece() == temp.getSymbol()) {
                if (board[rows][cols].getPiece() == '@') {
                    board[rows][cols] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[rows][cols] = new UnplayablePosition(Position.BLACK);
                }
                rows++;
                cols--;
            }
        }
    }
    public void flipWest(int row, int col, Player current, Player temp) {
        if (checkWest(row, col, current, temp)) {
            int cols = col - 1;
            while (board[row][cols].getPiece() == temp.getSymbol()) {
                if (board[row][cols].getPiece() == '@') {
                    board[row][cols] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[row][cols] = new UnplayablePosition(Position.BLACK);
                }
                cols--;
            }
        }
    }
    public void flipNorthWest(int row, int col, Player current, Player temp) {
        if (checkNorthWest(row, col, current, temp)) {
            int rows = row - 1;
            int cols = col - 1;
            while (board[rows][cols].getPiece() == temp.getSymbol()) {
                if (board[rows][cols].getPiece() == '@') {
                    board[rows][cols] = new UnplayablePosition(Position.WHITE);
                } else {
                    board[rows][cols] = new UnplayablePosition(Position.BLACK);
                }
                rows--;
                cols--;
            }
        }
    }

    public boolean checkAnyValidMoves(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].canPlay()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFullBoard(){ //true if board is full, false otherwise
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].canPlay()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkWinner(){
        int white_pts = 0;
        int black_pts = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].getPiece() == 'O')
                    white_pts++;
                else if (board[row][col].getPiece() == '@')
                    black_pts++;
            }
        }
        System.out.println("🎉🎉🎉 Congratulations!!! 🎉🎉🎉" +
                "\nwhite: "+white_pts+"pts" +
                "\nblack: "+black_pts+"pts");
        if (white_pts > black_pts)
            System.out.println("White wins!");
        else if (white_pts < black_pts)
            System.out.println("Black wins!");
        else System.out.println("Draw!");
        System.out.println("**********************************************************");
        System.out.println("\t\t\tThank you for playing Othello!");
        System.out.println("**********************************************************");
        System.exit(0);
    }

    public void concedeGame(Player winner){
        System.out.println("🎉🎉🎉 Congratulations!!! 🎉🎉🎉");
        System.out.println("Player"+winner.getPlayer_number()+", "+winner.getColour()+", "+winner.getName()+" wins!");
        System.out.println("**********************************************************");
        System.out.println("\t\t\tThank you for playing Othello!");
        System.out.println("**********************************************************");
        System.exit(0);
    }

    public void loadBoardState(String boardState) {
        int index = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char ch = boardState.charAt(index++);

                if (ch == 'O')
                    board[row][col] = new UnplayablePosition(Position.WHITE);
                else if (ch == '@')
                    board[row][col] = new UnplayablePosition(Position.BLACK);
                else if (ch == '*')
                    board[row][col] = new UnplayablePosition(UnplayablePosition.UNPLAYABLE);
                else board[row][col] = new PlayablePosition(Position.EMPTY);
            }
        }
    }

    public String printBoard() {
        StringBuilder printedBoard = new StringBuilder();
        for (Position[] positions : board) {
            for (Position position : positions) {
                printedBoard.append(position.getPiece());
            }
        }
        return printedBoard.toString();
    }


}

