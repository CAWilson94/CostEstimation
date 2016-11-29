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

	public List<List<String>> file() {

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
				String[] columnSplit = currentLine.split(",");
				// put each column into relevant array
				System.out.println(currentLine + "SO IT BEGINS");

				for (int i = 0; i < columnSplit.length; i++) {
					attributes.get(i).add(columnSplit[i]);
				}
				currentLine = br.readLine();
			}
			attributes.remove(0);
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

	public void loopAttributes(List<List<String>> attributes2) {
		// Just to loop through all the attributes for later on!
		for (List<String> f : attributes) {
			System.out.println(f.toString());
		}
	}
	
	public void something(){
		for(int i = 0; i< vars.size();i++){
			for(int j = 0; j < attributes.size()){
				var.set().attributes.get(i);
			}
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.file();
	}

}
