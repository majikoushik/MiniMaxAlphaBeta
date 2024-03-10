package com.MiniMaxAlphaBeta.agent.examples;

import java.util.ArrayList;

import com.MiniMaxAlphaBeta.agent.Robot;
import com.MiniMaxAlphaBeta.environment.Environment;

/**
 * A simple agent that drops game pieces by randomly selecting
 * one of the valid columns called from env.getValidActions().
 *
 */
public class RandomRobot extends Robot {

	public RandomRobot(Environment env) {
		super(env);
	}

	/**
	 * Rather than playing optimally, RandomRobot is going to pick
	 * a random possible move.
	 */
	@Override
	public int getAction() {
		ArrayList<Integer> possibleActions = env.getValidActions();
		//System.out.println(possibleActions.toString());
		int randomIndex = (int)(Math.random() * possibleActions.size());
		return possibleActions.get(randomIndex);
	}

}
