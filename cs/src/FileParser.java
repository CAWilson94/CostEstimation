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

			br = new BufferedReader(new FileReader("china.arff.txt"));

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

				for (int i = 0; i < columnSplit.length; i++) {
					attributes.get(i).add(Double.parseDouble(columnSplit[i]));
				}
				currentLine = br.readLine();
			}
			attributes.remove(0);
			br.close();

		} catch (

		FileNotFoundException e) {
			System.out.println("Something fucked up");
		} catch (IOException e) {
			System.out.println("readline stuffed");
		}

		return attributes;

	}

	public List<List<Double>> input(List<List<Double>> attributes) {
		int size = attributes.size();
		attributes.remove(size - 1);
		return attributes;
	}

	public void loopAttributes(List<List<Double>> attributes) {
		// Just to loop through all the attributes for later on!
		for (List<Double> f : attributes) {
			System.out.println(f.toString());
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.loopAttributes(fp.input(fp.file()));

	}

}
