package com.MiniMaxAlphaBeta;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.MiniMaxAlphaBeta.agent.AiPlayerRobot;

import com.MiniMaxAlphaBeta.agent.examples.GreedyRobot;
import com.MiniMaxAlphaBeta.agent.examples.HorizontalRobot;
import com.MiniMaxAlphaBeta.agent.examples.RandomRobot;
import com.MiniMaxAlphaBeta.agent.examples.VerticalRobot;
import com.MiniMaxAlphaBeta.environment.Environment;
import com.MiniMaxAlphaBeta.environment.Status;
import com.MiniMaxAlphaBeta.simulation.RunSimulation;

/**
 * This JUnit test suite uses JUnit5. In order to run these 
 * test cases, you will need to have JUnit5 installed on your
 * local machines. You can set your Project to use JUnit5 by
 * right-clicking on the project and selecting "Properties", then
 * selecting "Java Build Path". Finally, selecting "Add Library..."
 * will allow you to select "JUnit" and specify the version.
 * 
 */ 
public class MiniMaxAlphaBeta_TestCase {
	private int numTrials = 100;
	private int successfulTrials = 0;
	private boolean displayExceptions = false;
	private int DURATION = 5000;
	private String line = "Test %02d success rate: %.2f after %d trials";
	private String error = "Error on iteration %02d: %s";
	private String trialMsg = "Trial %02d: ";
	private double threshold = 0.7; // 70% victory

	@Before
	public void setUp() {
		successfulTrials = 0;
	}

	// AiPlayerRobot is Yellow Player (goes first)
	// RandomRobot is Red Player (goes second)
	@Test
	public void testEnvironment01() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new AiPlayerRobot(env), Status.YELLOW);
			sim.addPlayer(new RandomRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// RandomRobot is Yellow Player (goes first)
	// AiPlayerRobot is Red Player (goes second)
	@Test
	public void testEnvironment02() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new RandomRobot(env), Status.YELLOW);
			sim.addPlayer(new AiPlayerRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// AiPlayerRobot is Yellow Player (goes first)
	// HorizontalRobot is Red Player (goes second)
	@Test
	public void testEnvironment03() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new AiPlayerRobot(env), Status.YELLOW);
			sim.addPlayer(new HorizontalRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// HorizontalRobot is Yellow Player (goes first)
	// AiPlayerRobot is Red Player (goes second)
	@Test
	public void testEnvironment04() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new HorizontalRobot(env), Status.YELLOW);
			sim.addPlayer(new AiPlayerRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// AiPlayerRobot is Yellow Player (goes first)
	// VerticalRobot is Red Player (goes second)
	@Test
	public void testEnvironment05() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new AiPlayerRobot(env), Status.YELLOW);
			sim.addPlayer(new VerticalRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// VerticalRobot is Yellow Player (goes first)
	// AiPlayerRobot is Red Player (goes second)
	@Test
	public void testEnvironment06() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new VerticalRobot(env), Status.YELLOW);
			sim.addPlayer(new AiPlayerRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// EXTRA CREDIT - +5% if you pass this test case
	// AiPlayerRobot is Yellow Player (goes first)
	// GreedyRobot is Red Player (goes second)
	@Test
	public void testEnvironment07() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new AiPlayerRobot(env), Status.YELLOW);
			sim.addPlayer(new GreedyRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}

	// EXTRA CREDIT - +5% if you pass this test case
	// GreedyRobot is Yellow Player (goes first)
	// AiPlayerRobot is Red Player (goes second)
	@Test
	public void testEnvironment08() {
		for (int trial = 0; trial < numTrials; trial++) {
			RunSimulation sim = new RunSimulation();
			Environment env = sim.getEnvironment();
			sim.addPlayer(new GreedyRobot(env), Status.YELLOW);
			sim.addPlayer(new AiPlayerRobot(env), Status.RED);
			try {
				// CompletableFuture will run an iteration's simulation
				// asynchronously for 5000 milliseconds (5 seconds) before timing out.
				// This is to prevent infinite loops or inefficient implementations
				// that are brute forcing solutions.
				Runnable simulation = () -> sim.run();
				CompletableFuture.runAsync(simulation).get(DURATION, TimeUnit.MILLISECONDS);
				boolean status = sim.hasGameTerminated();
				if (status) {
					Status state = sim.getGameStatus();
					String msg;
					switch(state) {
					case DRAW:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					case RED_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						successfulTrials++;
						break;
					case YELLOW_WIN:
						msg = String.format(trialMsg, trial+1);
						System.out.print(msg);
						break;
					default:
						break;
					}
				}
			} catch (InterruptedException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (ExecutionException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			} catch (TimeoutException e) {
				if (displayExceptions)
					System.out.printf(error, trial, e.getClass());
			}
		}

		String msg = String.format(line, 1, successfulTrials/(numTrials*1.0)*100, numTrials);
		System.out.println(msg);
		assertTrue(successfulTrials/(numTrials*1.0) >= threshold, msg);
	}
}
