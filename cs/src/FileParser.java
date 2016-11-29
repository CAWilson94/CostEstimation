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
	List<List<String>> attributes = new ArrayList<List<String>>();

	public void file() {

		try {
			BufferedReader br = null;

			String currentLine = " ";

			br = new BufferedReader(new FileReader("china.arff.txt"));

			while (!(currentLine.contains("@data"))) {
				currentLine = br.readLine();
			}

			currentLine = br.readLine();
			System.out.println(currentLine + " yer maw");
			String[] tokens = currentLine.split(",");
			boop = new ArrayList<String>(Arrays.asList(tokens));
			int numAttributes = boop.size();
			System.out.println(numAttributes);
			for (int i = 0; i < numAttributes; i++) {
				List<String> list = new ArrayList<>();
				attributes.add(list);
			}

			while (currentLine != null) {
				System.out.println(currentLine + "SO IT BEGINS");
				currentLine = br.readLine();
			}

			br.close();

		} catch (

		FileNotFoundException e) {
			System.out.println("Something fucked up");
		} catch (IOException e) {
			System.out.println("readline stuffed");
		}
	}

	public void loopAttributes() {
		// Just to loop through all the attributes for later on!
		for (List<String> f : attributes) {
			for (String t : f) {

			}
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.file();
	}

}
