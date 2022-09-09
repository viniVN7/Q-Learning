package questions.qLearning;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author José Vinícius
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        
        int goalMaze = 29;
        int question;

        Scanner input = new Scanner(System.in);
        
        System.out.println("----- QUESTÕES -----");
        System.out.println("Questão 1: Explorando o tabuleiro");
        System.out.println("Questão 2: Explorando o labirinto");
        System.out.println("");
        System.out.println("Escolha a questão que deseja verificar: ");
        
        question = input.nextInt();

        if (question == 1) {
            QLearning question1 = new QLearning(1, random.nextInt(36), 10000);
            question1.run();
        } else if (question == 2) {
            QLearning question2 = new QLearning(goalMaze, 1000000);
            question2.run();
        }
    }
}
