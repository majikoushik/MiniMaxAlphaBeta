package com.MiniMaxAlphaBeta.agent.examples;

import com.MiniMaxAlphaBeta.agent.Robot;
import com.MiniMaxAlphaBeta.environment.Environment;

/**
 * A simple agent that drops game pieces horizontally along
 * the game board. If it reaches the edge of the game board, it
 * will reset back to the left most column. Does not care if the
 * column is already full. 
 *
 */
public class HorizontalRobot extends Robot {
	
	private int col;

	public HorizontalRobot(Environment env) {
		super(env);
	}

	/** Returns columns in a left-to-right order. */
	@Override
	public int getAction() {
		int choice = this.col;
		this.col = (choice + 1) % super.env.getCols();
		return choice;
	}

}
