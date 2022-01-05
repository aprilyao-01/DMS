package setting;


/**
 * <h1>Class: {@link Rank}</h1>
 * This class is used to define the relationship between users and scores.
 *
 * @version 1.1
 * @since 1.0
 * @see controller.HighScoresController
 * @author Siyu Yao
 */
public class Rank {

    private String m_player;
    private Integer m_score;

    // accessor methods
    public Integer getScore() {return m_score;}
    public String getPlayer() {return m_player;}


    public Rank(String m_player, int m_score){
        this.m_player = m_player;
        this.m_score = m_score;
    }


}
