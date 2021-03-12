package recommenderSystem.driver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import recommenderSystem.handler.RecommenderSystem;
import recommenderSystem.util.FileProcessor;

/**
 * @author Madhan Thangavel
 *
 */
public class Driver {

	/**
	 * Driver code to program Recommender System using predictive algorithm.
	 * 
	 * @param args
	 * @return String The processed line read from the input file.
	 * @exception Exception On error encountered when handling the input.
	 */
	public static void main(String args[]) {
		try {
			/*
			 * As the build.xml specifies the arguments as input and output in case the
			 * argument value is not given java takes the default value specified in
			 * build.xml. To avoid that, below condition is used.
			 */
			if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
				System.err.println("Enter correct number of arguments");
				System.exit(0);
			}
			/**
			 * Input file validation.
			 */
			if (!Files.exists(Paths.get(args[0]))) {
				System.err.printf("Error: Given Input.txt or train.txt doesn't exist..");
				System.exit(0);
			}
			/**
			 * To validate wheather the given input is empty.
			 */
			if (new File(args[0]).length() > 0) {
				System.out.println("Lets gets started with Data Mining Project - Recommender System");

				System.out.println("Recommender System - Instantiated");
				RecommenderSystem recommenderSystem = new RecommenderSystem();

				System.out.println("File Processor - Instantiated");
				FileProcessor fileProcessor = new FileProcessor(args[0], true);

				System.out.println("Process - Input Matrix - Filling - started.");
				recommenderSystem.insertToInputMatrix(recommenderSystem, fileProcessor);
				System.out.println("Process - Input Matrix - Filling - completed.");

				System.out.println("Applying Prediction Algorithm.");
				recommenderSystem.applyPearsonCorrelation();
				recommenderSystem.getPredictions();

				System.out.println("Writing the result to file - " + args[1] + " - started.");
				FileProcessor ofileProcessor = new FileProcessor(args[1], false);
				recommenderSystem.printPredictionMatrix(ofileProcessor);
				System.out.println("Writing the result to file - " + args[1] + " - completed.");

				System.out.println("Check the result in " + args[1]);
			} else {
				String msg = "Input File is either empty or blank. Terminating code....";
				throw new Exception(msg);
			}
		} catch (Exception e) {
			System.out.println("Exception Caught");
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
}