import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {

	/**
	 * Make a list for each column - look at how csv's would be parsed in say
	 * Python. Emulate this.
	 */

	public List<String> boop;
	List<List<Double>> attributes = new ArrayList<List<Double>>();

	public List<List<Double>> file() {

		try {
			BufferedReader br = null;

			String currentLine = " ";

			br = new BufferedReader(new FileReader("miyazaki94.arff"));

			while (!(currentLine.contains("@data"))) {
				currentLine = br.readLine();
			}

			currentLine = br.readLine();
			String[] tokens = currentLine.split(",");
			boop = new ArrayList<String>(Arrays.asList(tokens));
			int numAttributes = boop.size();
			System.out.println(numAttributes);

			for (int i = 0; i < numAttributes; i++) {
				List<Double> list = new ArrayList<>();
				attributes.add(list);
			}

			while (currentLine != null) {
				String[] columnSplit = currentLine.split(",");
				// put each column into relevant array
				System.out.println(currentLine + "SO IT BEGINS");

				for (int i = 1; i < columnSplit.length; i++) { // Skips Id's
					attributes.get(i).add(Double.parseDouble(columnSplit[i]));
				}
				currentLine = br.readLine();
			}
			// attributes.remove(0);
			System.out.println("looping attributes/n");
			loopAttributes(attributes);

			br.close();

		} catch (

		FileNotFoundException e) {
			System.out.println("Something fucked up");
		} catch (IOException e) {
			System.out.println("readline stuffed");
		}

		return attributes;

	}

	public void loopAttributes(List<List<Double>> attributes2) {
		// Just to loop through all the attributes for later on!
		for (List<Double> f : attributes) {
			System.out.println(f.toString());
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.file();
	}

}
