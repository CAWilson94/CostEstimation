import java.util.List;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class SimpleMathTestFitnessFunction extends GPFitnessFunction {

	private List<Double> _input1;
	private Integer[] _input2;
	private int[] _output;
	private Variable _xVariable;
	private Variable _yVariable;

	// Custom inputs etc
	private List<List<Double>> totalInput;
	List<Double> out_put;
	List<Variable> varLabels;

	private static Object[] NO_ARGS = new Object[0];

	public SimpleMathTestFitnessFunction(List<Double> input1, Integer input2[], int output[], Variable x, Variable y) {
		_input1 = input1;
		_input2 = input2;
		_output = output;
		_xVariable = x;
		_yVariable = y;
	}

	public SimpleMathTestFitnessFunction(List<List<Double>> input, List<Double> outPut, List<Variable> labels) {
		totalInput = input;
		out_put = outPut;
		varLabels = labels;
	}

	@Override
	protected double evaluate(final IGPProgram program) {
		double result = 0.0f;

		long longResult = 0;
		/*for (int i = 0; i < _input1.size(); i++) {
			// Set the input values
			_xVariable.set(_input1.get(i));
			_xVariable.set(_input1.get(i));
			// Execute the genetically engineered algorithm
			long value = program.execute_int(0, NO_ARGS); // issues with int to
															// double

			// The closer longResult gets to 0 the better the algorithm.
			longResult += Math.abs(value - _output[i]);
		}*/

		for (int i = 0; i < out_put.size(); i++) {
			for (int j = 0; j < totalInput.size(); j++) {
				varLabels.get(j).set(totalInput.get(i).get(i));
				long value = program.execute_int(0, NO_ARGS);
				longResult += Math.abs(value - _output[i]);
			}
		}

		result = longResult;

		return result;
	}

}
