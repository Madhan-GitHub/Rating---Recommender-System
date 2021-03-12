package recommenderSystem.handler;

import java.util.Map.Entry;
import java.util.TreeMap;
import recommenderSystem.util.FileProcessor;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * Class - Recommender System to predict User, Item rating based on Centered
 * Cosine Similarity.
 */
public class RecommenderSystem implements RecommederSystemInterfaceI {

	public final int users = 943;
	public final int items = 1682;
	/*
	 * Matrix Declaration with its type.
	 */
	int[][] input_matrix = new int[users + 1][items + 1];
	int[][] output_matrix = new int[users + 1][items + 1];

	double[][] similarityMatrix = new double[users + 1][users + 1];
	double[][] adjustedMatrix = new double[users + 1][items + 1];

	/**
	 * Funtion to set Input data in input matrix
	 * 
	 * @param row
	 * @param column
	 * @param value
	 */
	public void setInputMatrix(int row, int column, int value) {
		input_matrix[row][column] = value;
	}

	/**
	 * Funtion to fill initial input matrix by parsing input file line by line.
	 * 
	 * @param recommenderSystem
	 * @param fileProcessor
	 */
	public void insertToInputMatrix(RecommenderSystem recommenderSystem, FileProcessor fileProcessor) {
		String inputLine = null;
		while ((inputLine = fileProcessor.readInput()) != null) {
			String[] inputArray = inputLine.split(" ");
			recommenderSystem.setInputMatrix(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
					Integer.parseInt(inputArray[2]));
		}
	}

	/**
	 * Funtion to set the input matrix by based on average.
	 * 
	 */
	public void allignMatrix() {
		for (int iIndex = 1; iIndex <= this.users; iIndex++) {
			double avg = 0;
			double count = 0;
			int jIndex = 1;
			while (jIndex <= this.items) {
				if (!checkzero(input_matrix[iIndex][jIndex])) {
					avg += input_matrix[iIndex][jIndex];
					count++;
				}
				jIndex++;
			}
			avg = avg / count;
			for (int indexJ = 1; indexJ <= this.items; indexJ++) {
				if (!checkzero(input_matrix[iIndex][indexJ])) {
					adjustedMatrix[iIndex][indexJ] = input_matrix[iIndex][indexJ] - avg;
				}
			}
		}
	}

	/**
	 * Funtion to get Final predictions value to fill the null values in adjusted
	 * matrix with predicted ratings
	 */
	public void getPredictions() {
		for (int user1 = 1; user1 <= this.users; user1++) {
			for (int item = 1; item <= this.items; item++) {
				if (checkzero(input_matrix[user1][item])) {
					double numerator = 0;
					double denominator = 0;
					double prediction = 0;
					TreeMap<Integer, Double> sortedMap = new TreeMap<Integer, Double>();
					for (int user2 = 1; user2 < this.users; user2++) {
						/**
						 * Calculations for prediction of rating for user1 and item. based on all user
						 * who have rated the same item.
						 */
						if (user1 != user2 && !checkzero(input_matrix[user2][item])) {
							sortedMap.put(user2, similarityMatrix[user1][user2]);
						}
					}
					int i = 0;
					for (Entry<Integer, Double> entry : sortedMap.entrySet()) {
						if (i < Math.min(10, sortedMap.size())) {
							numerator += input_matrix[entry.getKey()][item] * entry.getValue();
							denominator += Math.abs(similarityMatrix[user1][entry.getKey()]);
							i++;
						}
					}
					prediction = numerator / denominator;
					int final_prediction = setPrediction(prediction);
					setRating(user1, item, final_prediction);
				} else {
					setRating(user1, item, input_matrix[user1][item]);
				}
			}
		}
	}

	/**
	 * Funtion to normalize the predictions in int values and values in [1,5] range.
	 * 
	 * @param prediction
	 * @return
	 */
	private int setPrediction(double prediction) {
		int final_prediction = (int) Math.round(prediction);
		if (final_prediction < 1) {
			final_prediction = 1;
		} else if (final_prediction > 5) {
			final_prediction = 5;
		}
		return final_prediction;
	}

	/**
	 * Funtion to calculate mean rating value of user i.
	 * 
	 * @param row
	 * @return
	 */
	private double calculateMean(int row) {
		double avg = 0;
		int j = 1;
		while (j <= this.items) {
			avg += input_matrix[row][j];
			++j;
		}
		avg = avg / items;
		return avg;
	}

	/**
	 * Funtion for calculating similarity between all users. Fills the Similarity
	 * Matrix Similarity Formula: Pearson Correlation
	 */
	public void applyPearsonCorrelation() {
		for (int user1 = 1; user1 <= this.users; user1++) {
			double numerator = 0;
			double denomenator = 0;
			double mean1 = 0;
			double mean2 = 0;
			mean1 = this.calculateMean(user1);
			for (int user2 = 1; user2 <= this.users; user2++) {
				if (user1 == user2) {
					continue;
				}
				mean2 = this.calculateMean(user2);
				double xsq = 0;
				double ysq = 0;
				int intersectionCount = 0;
				for (int item = 1; item <= this.items; item++) {
					if (checkzero(input_matrix[user1][item]) && checkzero(input_matrix[user2][item])) {
						numerator = (input_matrix[user1][item] - mean1) * (input_matrix[user2][item] - mean2);
						xsq += Math.pow((input_matrix[user1][item] - mean1), 2);
						ysq += Math.pow((input_matrix[user2][item] - mean2), 2);
						intersectionCount++;
					}
				}
				denomenator = Math.sqrt(xsq * ysq);
				if (!checkzero(denomenator)) {
					if (!checkzero((double) intersectionCount)) {
						int adjustment = (Math.min(intersectionCount, 50)) / 50;
						double similarity = (numerator / denomenator) * adjustment;
						setSimilarity(user1, user2, similarity);
					}
				}
			}
		}
	}

	/**
	 * Funtion to set value in similarity Matrix.
	 * 
	 * @param row
	 * @param column
	 * @param value
	 */
	private void setSimilarity(int row, int column, double value) {
		similarityMatrix[row][column] = value;
	}

	/**
	 * Funtion to set value in output Matrix
	 * 
	 * @param row
	 * @param column
	 * @param value
	 */
	private void setRating(int row, int column, int value) {
		output_matrix[row][column] = value;
	}

	/**
	 * Funtion to check if value is zero or not
	 * 
	 * @param a
	 * @return boolean
	 */
	private boolean checkzero(double a) {
		return (a == 0);
	}

	/**
	 * Funtion to print final predicted ratings value to the output file.
	 * 
	 * @param ofileProcessor
	 */
	public void printPredictionMatrix(FileProcessor ofileProcessor) {
		for (int i = 1; i <= users; i++) {
			for (int j = 1; j <= items; j++) {
				StringBuilder strBuild = new StringBuilder();
				strBuild.append(i).append(" ");
				strBuild.append(j).append(" ");
				strBuild.append(output_matrix[i][j]);
				ofileProcessor.writeToFile(strBuild.toString());
			}
		}
	}
}