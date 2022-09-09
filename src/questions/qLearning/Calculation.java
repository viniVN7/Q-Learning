package questions.qLearning;

/**
 * @author José Vinícius
 */
public class Calculation {
    private static double learningFactor = 0.9;
    
    /**
     * 
     * @param q
     * @param action
     * @param state
     * @param currentAction
     * @param currentState
     * @param previousState
     * @return
     */
    public static double calculateQ(double[][] q,int action[],int state[],int currentAction, int currentState, int previousState) {
        return q[previousState][action[currentAction]] = state[currentState] + (learningFactor * max(currentState,q));
    }

    /**
     * 
     * @param estado
     * @param q
     * @return
     */
    public static double max(int estado, double [][]q) {
        double highestValue = 0;
        for (int i = 0; i < 4; i++) {
            if (q[estado][i] > highestValue) {
                highestValue = q[estado][i];
            }
        }
        return highestValue;
    }

    }

