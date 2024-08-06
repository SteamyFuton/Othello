public class PlayablePosition extends Position {

    public PlayablePosition(char piece){
        super(piece);
    }

    public boolean canPlay(){
        return true;
    }
}
