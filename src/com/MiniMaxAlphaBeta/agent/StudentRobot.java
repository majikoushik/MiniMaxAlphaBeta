package com.MiniMaxAlphaBeta.agent;

import com.MiniMaxAlphaBeta.environment.Environment;
import com.MiniMaxAlphaBeta.environment.Position;
import com.MiniMaxAlphaBeta.environment.Status;

public class StudentRobot extends Robot {

    private Status opponent;
    private Status self;
    private static final int MAX_DEPTH = 6;
    private static final int MAX_WINNING_SCORE = 999999;
    private static final int MIN_WINNING_SCORE = -999999;

    public StudentRobot(Environment env) {
        super(env);
    }

    /**
     * Design an agent that can play
     * Connect Four. The goal of Connect Four is to "connect" four (4) markers of
     * the same color (role) horizontally, vertically, or diagonally. In this
     * exercise your getAction method should return an integer between 0 and 6
     * (inclusive), representing the column you would like to "drop" your marker.
     * 
     * There are multiple example agents found in the com.MiniMaxAlphaBeta.examples
     * package. Each example agent provides a brief explanation on its decision
     * process, as well as demonstrations on how to use the various methods from
     * Environment. In order to pass this Problem Set, you must successfully beat
     * RandomRobot, VerticalRobot, and HorizontalRobot 70% of the time as both the
     * 
     * While Simple Reflex or Model-based agent may be able to succeed, consider
     * exploring the Minimax search algorithm to maximize your chances of winning.
     * While the first two will be easier, you may want to place priority on moves
     * that prevent the adversary from winning.
     */

    @Override
    public int getAction() {
        // getting the opponent's color or role
        opponent = super.role == Status.RED ? Status.YELLOW : Status.RED;
        self = super.role;
        int[] decision = alphaBetaMinimax(env.clonePositions(), Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true);
        return decision[0];
    }

    private int[] alphaBetaMinimax(Position[][] board, int alpha, int beta, int depth, boolean maxTurn) {

        int score = calculateScore(board);

        if (isDone(depth, board, score)) {
            return new int[] { -1, score };
        }

        if (maxTurn) {

            int[] max = new int[] { -1, Integer.MIN_VALUE };

            for (int column = 0; column < env.getCols(); column++) {
                Position[][] clonedBoard = cloneBoard(board);
                if (place(clonedBoard, column, self)) {
                    int[] result = alphaBetaMinimax(clonedBoard, alpha, beta, depth + 1, false);

                    if (result[1] > max[1]) {
                        max[0] = column;
                        max[1] = result[1];
                    }
                    alpha = Math.max(alpha, max[1]);
                    if (beta <= alpha)
                        break;
                }
            }
            return max;
        } else {
            int[] min = new int[] { -1, Integer.MAX_VALUE };

            for (int column = 0; column < env.getCols(); column++) {
                Position[][] clonedBoard = cloneBoard(board);
                if (place(clonedBoard, column, opponent)) {
                    int[] result = alphaBetaMinimax(clonedBoard, alpha, beta, depth + 1, true);

                    if (result[1] < min[1]) {
                        min[0] = column;
                        min[1] = result[1];
                    }
                    beta = Math.min(beta, min[1]);
                    if (beta <= alpha)
                        break;
                }
            }
            return min;
        }
    }

    private Position[][] cloneBoard(Position[][] board) {
        // Create a blank version of the board
        Position[][] clone = new Position[env.getRows()][env.getCols()];
        for (int row = 0; row < env.getRows(); row++) {
            for (int col = 0; col < env.getCols(); col++) {
                // Update the square to either blank, red, or yellow
                if (board[row][col].getStatus() == Status.BLANK)
                    clone[row][col] = new Position(row, col, Status.BLANK);
                else if (board[row][col].getStatus() == Status.RED)
                    clone[row][col] = new Position(row, col, Status.RED);
                else if (board[row][col].getStatus() == Status.YELLOW)
                    clone[row][col] = new Position(row, col, Status.YELLOW);
            }
        }
        return clone;
    }

    private boolean isBoardFull(Position[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getStatus() == Status.BLANK)
                    return false;
            }
        }
        return true;
    }

    private boolean isDone(int depth, Position[][] board, int score) {
        return depth >= MAX_DEPTH || isBoardFull(board) || score >= MAX_WINNING_SCORE || score <= MIN_WINNING_SCORE;
    }

    private boolean place(Position[][] board, int col, Status player) {
        if (col >= 0 && col < this.env.getCols()) {
            for (int row = this.env.getRows() - 1; row >= 0; row--) {
                if (board[row][col].getStatus() == Status.BLANK) {
                    board[row][col] = new Position(row, col, player);
                    return true;
                }
            }
        }
        return false;
    }

    private int calculateScore(Position[][] board) {
        int vertical_points = 0, horizontal_points = 0, descDiagonal_points = 0, ascDiagonal_points = 0, total_points = 0;
        
        //Vertical point calculation
        for (int row = 0; row < env.getRows() - 3; row++) {
            for (int column = 0; column < env.getCols(); column++) {
                int tempScore = calculateScorePosition(board, row, column, 1, 0);
                vertical_points += tempScore;
                if (tempScore >= MAX_WINNING_SCORE || tempScore <= MIN_WINNING_SCORE)
                    return tempScore;
            }
        }
        
        //Horizontal point calculation
        for (int row = 0; row < env.getRows(); row++) {
            for (int column = 0; column < env.getCols() - 3; column++) {
                int tempScore = calculateScorePosition(board, row, column, 0, 1);
                horizontal_points += tempScore;
                if (tempScore >= MAX_WINNING_SCORE || tempScore <= MIN_WINNING_SCORE)
                    return tempScore;
            }
        }
        
        //Diagonal BR point calculation
        for (int row = 0; row < env.getRows() - 3; row++) {
            for (int column = 0; column < env.getCols() - 3; column++) {
                int tempScore = calculateScorePosition(board, row, column, 1, 1);
                descDiagonal_points += tempScore;
                if (tempScore >= MAX_WINNING_SCORE || tempScore <= MIN_WINNING_SCORE)
                    return tempScore;
            }
        }

        //Diagonal TR point calculation
        for (int row = 3; row < env.getRows(); row++) {
            for (int column = 0; column < env.getCols() - 3; column++) {
                int tempScore = calculateScorePosition(board, row, column, -1, 1);
                ascDiagonal_points += tempScore;
                if (tempScore >= MAX_WINNING_SCORE || tempScore <= MIN_WINNING_SCORE)
                    return tempScore;
            }
        }

        total_points = vertical_points + horizontal_points + descDiagonal_points + ascDiagonal_points;
        return total_points;
    }

    private int calculateScorePosition(Position[][] board, int row, int column, int increment_row, int increment_col) {
        int ai_points = 0, player_points = 0;

        for (int i = 0; i < 4; i++) // connect "4"
        {
            if (board[row][column].getStatus() == self) {
                ai_points++;
            } else if (board[row][column].getStatus() == opponent) {
                player_points++;
            }

            row += increment_row;
            column += increment_col;
        }

        if (player_points == 4)
            return MIN_WINNING_SCORE;
        else if (ai_points == 4)
            return MAX_WINNING_SCORE;
        else
            return ai_points;
    }

}
