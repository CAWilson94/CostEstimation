import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {

	/**
	 * Make a list for each column - look at how csv's would be parsed in say
	 * Python. Emulate this.
	 */

	public void file() {

		try {
			BufferedReader br = null;

			String currentLine;

			br = new BufferedReader(new FileReader("china.arff.txt"));

			while ((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
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
