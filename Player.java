import java.util.Scanner;

public class Player {
    private String name;
    private String colour;
    private int Player_number;
    private char symbol;
    Scanner Sc = new Scanner(System.in);

    public Player(int Player_number, String colour, char symbol) {
        this.Player_number = Player_number;
        this.colour = colour;
        this.symbol = symbol;
    }

    public Player(String name, int Player_number, String colour, char symbol) {
        this.name = name;
        this.Player_number = Player_number;
        this.colour = colour;
        this.symbol = symbol;
    }

    public Player(String colour, char symbol) {
        this.colour = colour;
        this.symbol = symbol;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public int getPlayer_number() {
        return Player_number;
    }

    public char getSymbol() {
        return symbol;
    }

    //ask for player name
    public void askName(){
        System.out.println("Please enter Player"+getPlayer_number()+"'s name: ");
        name = Sc.nextLine();
        System.out.println("Player"+getPlayer_number()+"'s name is registered as: "+getName()+"\n");
    }
}
