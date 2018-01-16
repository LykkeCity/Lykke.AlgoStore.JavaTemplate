package com.lykke.algos;

/**
 * The main entry point of a java algo
 */
public class Main {

	public static void main(String[] args) {
		// Load the environment variables and algo parameters
		try {
			Environment.load();
		} catch (Throwable t) {
			System.err.println("Exception was thrown while running the algo: " + t.getMessage());
			t.printStackTrace();
			// Without the properties the algo will fail on a random place. Exit while
			// the issue is still obvious.
			System.exit(1);
		}

		// Instantiate an algo. An algo is expected to be a class with the following
		// name com.lykke.algos.Algo
		Algo algo = new Algo();

		// Run the algo
		try {
			algo.run();
		} catch (Throwable t) {
			System.err.println("Exception was thrown while running the algo: " + t.getMessage());
			t.printStackTrace();
		}
	}
}
