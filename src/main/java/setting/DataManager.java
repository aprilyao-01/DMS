package setting;

import properties.PropertiesSetter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <h1>Class: {@link DataManager}</h1>
 *
 * <p>This class implements SINGLETON design pattern,
 * manage the players' scores with file and update the dates.
 *
 * @version 1.2
 * @since 1.0
 * @see controller.HighScoresController
 * @author Siyu Yao
 */
public final class DataManager {

    private static final String DATA_FILE = "src/main/resources/data/rank.csv";
    private static final int MAX_NUMBER = PropertiesSetter.getMaxHighScore();

    private static DataManager m_newDataManager = null;


    /** get the current data manager, if it is first use, create a new instance
	 * @return the current DataManager
	 * */
    public static DataManager getNewDataManager(){
        if(m_newDataManager == null){
            m_newDataManager = new DataManager();
        }
        return m_newDataManager;
    }

    /**
	 * Blank constructor to ensure no other class tries to create an instance of the
	 * utility class.
	 */
    private DataManager(){
        // do nothing
    }

    /**
	 * When new user finish their game, this function will update the score to the file.
     * @param  playerName the name of the user
     * @param score the total score of the player
	 */
    public static void writeData(String playerName, int score){
        File file = new File(DATA_FILE);
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fileWriter = new FileWriter(file, true)){
            fileWriter.write(playerName + " " + score + System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * show the first 3 player and score
     */
    public static List<Rank> loadData(){
        File file = new File(DATA_FILE);
        if (! file.exists()) {
            return new ArrayList<>();
        }

        List<Rank> list = new ArrayList<>();
        try (FileReader reader = new FileReader(file)){
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line!= null) {
                String[] split = line.split(" ");
                list.add(new Rank(split[0], Integer.parseInt(split[1])));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = list.stream().sorted((x1, x2)
                -> x2.getScore().compareTo(x1.getScore())).collect(Collectors.toList());

        // always make sure it's the highest score
        if (list.size() > MAX_NUMBER) {
            list = list.subList(0, MAX_NUMBER);
        }
        return list;
    }


}
