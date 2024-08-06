import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Player player1 = new Player(1, "Black", '@'); //initialize player1
        Player player2 = new Player(2, "White", 'O'); //initialize player2
        Game thisgame = new Game(player1, player2); //initialize the game
        Board theboard = new Board(); //initializing the board
        Scanner Sc = new Scanner(System.in);

        int choice =0;
        System.out.println("""
                Welcome to Othello!\

                1 Load a Game\

                2 Quit\

                3 Start a new game\

                What would you like to do?""");

        do {
            try{
                choice = Sc.nextInt();
                switch (choice) {
                    case 1:
                        thisgame = Game.load();
                        thisgame.setBoard(thisgame.getBoardState(), theboard);
                        break;
                    case 2:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    case 3:
                        player1.askName(); //ask for player1's name
                        player2.askName(); //ask for player2's name

                        theboard.initializeBoard();

                        theboard.displayStartingPositions(); //display starting positions
                        theboard.choosePosition(); //choose starting position
                        break;
                    default: System.out.println("Invalid input. Please enter a number (1-3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                Sc.nextLine();
            }
        } while(choice<1 || choice>3);

        //play until end
        do {
            theboard.markPlayablePositions(thisgame.getCurrent());
            theboard.drawBoard(); //draw board
            thisgame.play(theboard); //play
            theboard.resetBoard();
        } while (!theboard.checkFullBoard()); //verify end
        theboard.resetBoard();
        theboard.drawBoard();
        theboard.checkWinner(); // who wins? a draw?
    }

    //fields for the game class
    private Player player1;
    private Player player2;
    private Player current;

    int no_moves_for_both=0;
    String boardState;
    Scanner Sc = new Scanner(System.in);

    public Game(Player player1, Player player2) { // constructor for game class
        this.player1 = player1;
        this.player2 = player2;
        this.current = player1;
    }

    public Game(Player player1, Player player2, String current, String boardState) {
        this.player1 = player1;
        this.player2 = player2;

        this.current = player1.getName().equals(current) ? this.player1 : this.player2;
        this.boardState = boardState;
    }

    public Player getCurrent() {
        return current;
    }

    public String getBoardState() {
        return boardState;
    }

    public void play(Board playingboard){ //let's play
        int chosen_action = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println(current.getColour()+" "+current.getSymbol()
                    + ", Player" + current.getPlayer_number()
                    + ", " + current.getName() + ", choose what to do:\n"
                    + "1 = make a move\n"
                    + "2 = save the game\n"
                    + "3 = concede the game\n"
                    + "4 = forfeit your turn");

            try {
                chosen_action = Sc.nextInt();
                Sc.nextLine();
                if (chosen_action >= 1 && chosen_action <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Choose a valid action (1-4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1-4).");
                Sc.nextLine();  // Clear the invalid input
            }
        }

        switch (chosen_action) { //do action according to chosen action
            case 1:
                playingboard.chooseMove(current);
                if (playingboard.checkEmptySquare(playingboard.getRow(), playingboard.getCol())) {
                    playingboard.placePiece(current);
                    playingboard.flipPieces(current);

                    if (current==player1) //switching current player
                        current=player2;
                    else current=player1;
                    no_moves_for_both = 0;
                }
                else System.out.println("Moves unavailable. Choose a valid action.");
                break;
            case 2: save(playingboard);
                break;
            case 3:
                if (current==player1) //switching current player to the winner
                    current=player2;
                else current=player1;
                playingboard.concedeGame(current);
            case 4:
                if(playingboard.checkAnyValidMoves()) {
                    System.out.println("You cannot forfeit a turn if you have an available move.");
                    break;
                }
                else {
                    System.out.println("Skipping turn...");

                    no_moves_for_both++;
                    if (no_moves_for_both==2) // if both players cannot move, end the game
                        playingboard.checkWinner();

                    if (current==player1) { //switching current player
                        current=player2;
                    }
                    else {
                        current=player1;
                    }
                    break;
                }
            default:
                System.out.println("Please choose a valid action.");
        }

    }

    public static Game load(){
        String p1 = "";
        String p2 = "";
        String current_p = "";
        String boardState = "";
        String safeFile;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your save filename:");
        safeFile = scanner.nextLine();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("src/"+safeFile+".txt"));
            p1 = fileReader.readLine().trim();
            p2 = fileReader.readLine().trim();
            current_p = fileReader.readLine().trim();
            boardState = fileReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player player1 = new Player(p1, 1, "Black", '@');
        Player player2 = new Player(p2, 2, "White", 'O');

        return new Game(player1, player2, current_p, boardState);
    }

    public void setBoard(String boardState, Board aboard) {
        aboard.initializeBoard();
        aboard.loadBoardState(boardState);
    }

    private void save(Board board){ // save game
        System.out.println("Saving..." +
                "\nName your file name:");
        String fileName = Sc.nextLine();
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/"+fileName+ ".txt"));
            fileWriter.write(player1.getName()+"\n"
                    +player2.getName()+"\n"
                    +current.getName()+"\n"
                    + board.printBoard());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

