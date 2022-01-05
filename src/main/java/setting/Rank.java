package setting;

public class Rank {

    private String player;

    private Integer score;

    public Rank(String player, int score){
        this.player = player;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public String getPlayer() {
        return player;
    }
}
