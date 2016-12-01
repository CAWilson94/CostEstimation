import java.util.List;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class SimpleMathTestFitnessFunction extends GPFitnessFunction {

	// Custom inputs etc
	private List<List<Double>> totalInput;
	List<Double> out_put;
	List<Variable> labels;

	private static Object[] NO_ARGS = new Object[0];

	public SimpleMathTestFitnessFunction(List<List<Double>> input, List<Double> outPut, List<Variable> labs) {
		totalInput = input;
		out_put = outPut;
		labels = labs;
	}

	@Override
	protected double evaluate(final IGPProgram program) {
		double result = 0.0f;
		double longResult = 0;

		for (int i = 0; i < out_put.size(); i++) {

			for (int j = 0; j < totalInput.size(); j++) {
				labels.get(j).set(totalInput.get(j).get(i));
			}

			double value = program.execute_double(0, NO_ARGS);
			longResult += Math.abs(value - out_put.get(i)) / out_put.get(i);

		}
		result = longResult;
		return result;
	}

}
