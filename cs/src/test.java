import java.util.List;

public class test {

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		List<List<Double>> totalInput = fp.file();
		List<Double> OUTPUTtotal = totalInput.get(totalInput.size() - 1);

		System.out.println(OUTPUTtotal.toString());

	}

}
