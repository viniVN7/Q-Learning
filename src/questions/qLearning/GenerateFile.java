package questions.qLearning;

import java.io.BufferedReader;
import java.io.File;

/**
 * @author José Vinícius 
 */
public class GenerateFile {

    public static void generateAndRead (double[][]q, boolean flagRandom) {
        File qTable = new File("Q_Table.txt");
        BufferedReader br = null;
        try {
            java.io.FileReader fr = new java.io.FileReader(qTable);
            br = new BufferedReader(fr);
            String line = "";
    
            while (br.ready()){
                for (int i = 0; i < q.length; i++) {
                    line = br.readLine();
                    for (int j = 0; j < 4; j++) {
                        q[i][j] = Double.parseDouble(line.split(";")[j]); 
                    }
                }
            }
    
            flagRandom = false;
            br.close();
    
        } catch (Exception e) {
            for (int i = 0; i < q.length; i++) {
                for (int j = 0; j < 4; j++) {
                    q[i][j] = 0;
            }
        }
    }
}
}
