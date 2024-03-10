package com.MiniMaxAlphaBeta.environment;

/**
 * Represents a particular [row, col] coordinate as
 * a "Node" within the Environment to produce a graph-like
 * representation.
 */
public class Position {
	private int row, col;
	private Status status;

	public Position(int row, int col, Status status) {
		this.row = row;
		this.col = col;
		this.status = status;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	protected void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Point (" + this.row + ", " + this.col + ")";
	}
}
