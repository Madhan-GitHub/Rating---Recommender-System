package recommenderSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author Madhan Thangavel
 */

/*
 * FileProcessor is a utility to used to read in the contents of the input file
 * and to write the result to output file.
 */
public class FileProcessor {

	private static BufferedReader buffer_reader;
	private static BufferedWriter buffer_writer;
	private static FileInputStream input_stream;

	/**
	 * Method To initialte the input files.
	 * 
	 * @param file
	 * @param mode Read/Write
	 * @throws Exception On error encountered when handling the input.
	 */
	public FileProcessor(String file, boolean mode) {
		if (mode) {
			try {
				input_stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			try {
				buffer_reader = new BufferedReader(new InputStreamReader(input_stream));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			/*
			 * Create a output file to store result if doestn't exists.
			 */
			File resultantFile = new File(file);
			if (!resultantFile.exists()) {
				try {
					resultantFile.createNewFile();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			try {
				buffer_writer = new BufferedWriter(new FileWriter(resultantFile));
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * To read user, item and rating value from input file (train.txt).
	 * 
	 * @return line
	 */
	public String readInput() {
		String line = "";
		try {
			line = buffer_reader.readLine();
			return line;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return line;
	}

	/**
	 * To write user, item and rating value to output file.
	 * 
	 * @param line
	 */
	public void writeToFile(String line) {
		try {
			buffer_writer.write(line);
			buffer_writer.write("\n");
			buffer_writer.flush();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Function to close the file processor objects.
	 */
	public void closeFile() {
		try {
			buffer_reader.close();
			buffer_writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}