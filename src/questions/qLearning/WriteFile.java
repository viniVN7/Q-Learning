package questions.qLearning;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author José Vinícius
 */
public class WriteFile {

    /**
     * 
     * @param q
     * @throws IOException
     */
    public static void write(double [][]q) throws IOException{

        FileWriter fileWriter = new FileWriter("Q_Table.txt",false);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            PrintWriter printwWriter = new PrintWriter(bufferWriter); 
    
            for (int i = 0; i < q.length; i++) {
                for (int j = 0; j < 4; j++) {
                    printwWriter.append(q[i][j]+";");
                }
                printwWriter.append("\n");
            }
            printwWriter.close();
            bufferWriter.close();
            fileWriter.close();
    }

}
