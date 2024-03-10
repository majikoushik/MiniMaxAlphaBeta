package com.MiniMaxAlphaBeta.environment;

/**
 * A list of possible statuses for the environment's tiles
 * as well as the statuses for the environment.
 */
public enum Status {
	BLANK, RED, YELLOW,
	DRAW, RED_WIN, YELLOW_WIN,
	ONGOING;
}
