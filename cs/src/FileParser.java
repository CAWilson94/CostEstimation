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
	List<String> labels4dayz = new ArrayList<String>();

	public List<List<Double>> file() {

		try {
			BufferedReader br = null;

			String currentLine = " ";

			br = new BufferedReader(new FileReader("china.arff.txt"));

			while (!(currentLine.contains("@data"))) {
				currentLine = br.readLine();
				if (currentLine.contains("@attribute")) {
					String[] tokens = currentLine.split(" ");
					labels4dayz.add(tokens[1]);
				}

			}

			currentLine = br.readLine();
			String[] tokens = currentLine.split(",");
			boop = new ArrayList<String>(Arrays.asList(tokens));
			int numAttributes = boop.size();
			// System.out.println(numAttributes);

			for (int i = 1; i < numAttributes; i++) {
				List<Double> list = new ArrayList<>();
				attributes.add(list);
			}

			while (currentLine != null) {
				// System.out.println("Current Line: " + currentLine);
				String[] columnSplit = currentLine.split(",");
				// put each column into relevant array
				// System.out.println(currentLine + "SO IT BEGINS");

				for (int i = 1; i < columnSplit.length; i++) { // Skips Id's
					attributes.get(i - 1).add(Double.parseDouble(columnSplit[i]));
				}
				currentLine = br.readLine();
			}
			// attributes.remove(0);
			// System.out.println("looping attributes/n");
			// loopAttributes(attributes);
			// System.out.println("Done looping");

			br.close();

		} catch (

		FileNotFoundException e) {
			System.out.println("Something fucked up");
		} catch (IOException e) {
			System.out.println("readline stuffed");
		}

		return attributes;

	}

	public List<String> getLabels() {
		labels4dayz.remove(0);
		return labels4dayz;
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
		System.out.println(fp.getLabels());
	}

}
