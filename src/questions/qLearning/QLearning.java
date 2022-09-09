package questions.qLearning;

import java.io.IOException;
import java.util.Random;

//import questao2.labirinto.Maze;

/**
 * @author José Vinícius
 *         QLearning
 */
public class QLearning {
    private int ambient[][] = new Maze().getmaze();

    private int sizeMat = ambient.length;

    private int sizeState = (int) Math.pow(sizeMat, 2);

    private int sizeAction = 4;

    private int state[] = new int[sizeState];

    private int action[] = new int[sizeAction];

    private double Q[][] = new double[sizeState][sizeAction];

    private int goal;
    private int start;
    private int booster;
    private int totalOfMoves;
    private int currentState;
    private int previousState;
    private int currentAction;
    private boolean flagRandom = true;
    private Random random = new Random();

    private int up = 0;
    private int down = 1;
    private int left = 2;
    private int right = 3;

    /**
     * 
     * @param start
     * @param goal
     * @param totalOfMoves
     */
    public QLearning(int start, int goal, int totalOfMoves) {

        this.ambient = new Board().getBoard();
        this.sizeMat = ambient.length;
        this.sizeState = (int) Math.pow(sizeMat, 2);
        this.sizeAction = 4;
        this.state = new int[this.sizeState];
        this.action = new int[this.sizeAction];
        this.Q = new double[this.sizeState][this.sizeAction];

        setStart(start);
        settotalOfMoves(totalOfMoves);
        setCurrentState(getStart());
        setGoal(goal);

        for (int i = 0; i < state.length; i++) {
            if (i == goal) {
                this.state[i] = 10;
                booster = this.state[i];
            } else {
                this.state[i] = 0;
                booster = this.state[i];
            }
        }

        GenerateFile.generateAndRead(Q, flagRandom);

        action[0] = up;
        action[1] = down;
        action[2] = left;
        action[3] = right;
    }

    /**
     * 
     * @param goal
     * @param totalOfMoves
     * @throws IOException
     */
    public QLearning(int goal, int totalOfMoves) throws IOException {

        setStart(update(random.nextInt(sizeMat)));
        settotalOfMoves(totalOfMoves);
        setCurrentState(getStart());
        setGoal(goal);

        for (int i = 0; i < state.length; i++) {
            if (i == goal) {
                this.state[i] = 10;
                booster = this.state[i];
            } else {
                this.state[i] = 0;
                booster = this.state[i];
            }
        }

        GenerateFile.generateAndRead(Q, flagRandom);

        action[0] = up;
        action[1] = down;
        action[2] = left;
        action[3] = right;
    }

    /**
     * 
     * @throws IOException
     */
    public void run() throws IOException {

        int numberOfMoves = 0;
        int vector[] = new int[2];
        System.out.println("Gerando arquivo");

        while (numberOfMoves < totalOfMoves) {
            if (flagRandom)
                currentAction = random.nextInt(4);
            else
                currentAction = current(currentState);

            vector = checkMovement(action[currentAction], currentState);

            if (vector != null) {
                toMove(currentAction, vector);
                numberOfMoves++;
            }
        }
        WriteFile.write(Q);
    }

    /**
     * 
     * @param currentAction
     * @param vector
     */
    public void toMove(int currentAction, int[] vector) {

        if (action[currentAction] == up) {
            vector[0] += -1;
            previousState = currentState;
            currentState = 0;

            for (int i = 0; i < sizeMat; i++) {
                for (int j = 0; j < sizeMat; j++) {
                    if (j == vector[1] && i == vector[0]) {
                        i = sizeMat;
                        break;
                    }
                    currentState++;
                }
            }
            Calculation.calculateQ(Q, action, state, currentAction, currentState, previousState);
        }

        else if (action[currentAction] == down) {
            vector[0] += 1;
            previousState = currentState;
            currentState = 0;

            for (int i = 0; i < sizeMat; i++) {
                for (int j = 0; j < sizeMat; j++) {
                    if (j == vector[1] && i == vector[0]) {
                        i = sizeMat;
                        break;
                    }
                    currentState++;
                }
            }
            Calculation.calculateQ(Q, action, state, currentAction, currentState, previousState);
        }

        else if (action[currentAction] == left) {
            vector[1] += -1;
            previousState = currentState;
            currentState = 0;

            for (int i = 0; i < sizeMat; i++) {
                for (int j = 0; j < sizeMat; j++) {
                    if (j == vector[1] && i == vector[0]) {
                        i = sizeMat;
                        break;
                    }
                    currentState++;
                }
            }
            Calculation.calculateQ(Q, action, state, currentAction, currentState, previousState);
        }

        else if (action[currentAction] == right) {
            vector[1] += 1;
            previousState = currentState;
            currentState = 0;

            for (int i = 0; i < sizeMat; i++) {
                for (int j = 0; j < sizeMat; j++) {
                    if (j == vector[1] && i == vector[0]) {
                        i = sizeMat;
                        break;
                    }
                    currentState++;
                }
            }

            Calculation.calculateQ(Q, action, state, currentAction, currentState, previousState);
        }
    }

    /**
     * 
     * @param movement
     * @param state
     * @return
     */
    public int[] checkMovement(int movement, int state) {

        int state_aux = 0;
        int vector[] = new int[2];

        for (int i = 0; i < sizeMat; i++) {
            for (int j = 0; j < sizeMat; j++) {
                if (state_aux == state) {
                    if (movement == up) {
                        if (i != 0 && ambient[i - 1][j] != 1) {
                            vector[0] = i;
                            vector[1] = j;
                            return vector;
                        }
                    }

                    if (movement == down) {
                        if (i + 1 != sizeMat && ambient[i + 1][j] != 1) {
                            vector[0] = i;
                            vector[1] = j;
                            return vector;
                        }
                    }

                    if (movement == left) {
                        if (j != 0 && ambient[i][j - 1] != 1) {
                            vector[0] = i;
                            vector[1] = j;
                            return vector;
                        }
                    }

                    if (movement == right) {
                        if (j + 1 != sizeMat && ambient[i][j + 1] != 1) {
                            vector[0] = i;
                            vector[1] = j;
                            return vector;
                        }
                    }
                    return null;
                }
                state_aux++;
            }
        }
        return null;
    }

    /**
     * 
     * @param state
     * @return
     */
    public int current(int state) {
        int currentAction = random.nextInt(sizeAction);
        double highestValue = 0;

        for (int i = 0; i < sizeAction; i++) {
            if (Q[state][i] > highestValue) {
                currentAction = i;
            }
        }
        return currentAction;
    }

    /**
     * 
     * @param start
     * @return
     */
    private int update(int start) {
        int newStart = 0;

        for (int i = 0; i < sizeMat; i++) {
            for (int j = 0; j < sizeMat; j++) {
                if (newStart == start) {
                    if (ambient[i][j] == 0) {
                        setStart(start);
                        return start;
                    } else
                        start = -1;
                }

                if (start == -1) {
                    if (ambient[i][j] == 0) {
                        return newStart;
                    }
                }
                newStart++;
            }
        }
        return -1;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getGoal() {
        return goal;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void settotalOfMoves(int totalOfMoves) {
        this.totalOfMoves = totalOfMoves;
    }

    public int gettotalOfMoves() {
        return totalOfMoves;
    }
}
