package setting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DataManager {

    private static final String DATA_FILE = "src/main/resources/data/rank.csv";

    private DataManager(){

    }

    public static void writeData(String playerName, int score){
        File file = new File(DATA_FILE);
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fileWriter = new FileWriter(file, true);){
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
        try (FileReader reader = new FileReader(file);){
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
        list = list.stream().sorted((x1, x2) -> x2.getScore().compareTo(x1.getScore())).collect(Collectors.toList());
        if (list.size() > 5) {
            list = list.subList(0, 5);
        }
        return list;
    }


}
