import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

	/**
	 * Make a list for each column - look at how csv's would be parsed in say
	 * Python. Emulate this.
	 */

	public List<String> boop = new ArrayList<String>();

	public void file() {

		try {
			BufferedReader br = null;

			String currentLine;

			br = new BufferedReader(new FileReader("china.arff.txt"));

			while ((currentLine = br.readLine()) != null) {

				String[] tokens = currentLine.split(",");
				if (currentLine.contains("@data")) {
					br.readLine();
					currentLine = br.readLine();
					System.out.println(currentLine);
					boop.add(currentLine);
				}
			}

			System.out.println("some shit");
			for (String c : boop) {
				System.out.println(c + " boop");
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Something fucked up");
		} catch (IOException e) {
			System.out.println("readline stuffed");
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.file();
	}

}
