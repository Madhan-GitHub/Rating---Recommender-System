package recommenderSystem.handler;

import recommenderSystem.util.FileProcessor;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * Interface - Recommender System class to predict User, Item rating value.
 */
public interface RecommederSystemInterfaceI {

	public void setInputMatrix(int row, int column, int data);

	public void insertToInputMatrix(RecommenderSystem recommenderSystem, FileProcessor fileProcessor);

	public void allignMatrix();

	public void getPredictions();

	public void applyPearsonCorrelation();

	public void printPredictionMatrix(FileProcessor ofileProcessor);
}
