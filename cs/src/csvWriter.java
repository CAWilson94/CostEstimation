import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class csvWriter {

	public void extractResults() throws IOException {
		String csvFile = "abc.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

		// custom separator + quote
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');

		// custom separator + quote
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

		// double-quotes
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));

		writer.flush();
		writer.close();
	}

}