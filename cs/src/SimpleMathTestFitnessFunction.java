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

	private Variable _KLOC;
	private Variable _SCRN;
	private Variable _FORM;
	private Variable _FILE;
	private Variable _ESCRN;
	private Variable _EFORM;
	private Variable _EFILE;

	private static Object[] NO_ARGS = new Object[0];

	public SimpleMathTestFitnessFunction(List<Double> input1, Integer input2[], int output[], Variable x, Variable y) {
		_input1 = input1;
		_input2 = input2;
		_output = output;
		_xVariable = x;
		_yVariable = y;
	}

	public SimpleMathTestFitnessFunction(List<List<Double>> input, List<Double> outPut, Variable _KLOC, Variable _SCRN,
			Variable _FORM, Variable _FILE, Variable _ESCRN, Variable _EFORM, Variable _EFILE) {
		totalInput = input;
		out_put = outPut;
		this._KLOC = _KLOC;
		this._SCRN = _SCRN;
		this._FORM = _FORM;
		this._FILE = _FILE;
		this._ESCRN = _ESCRN;
		this._EFORM = _EFORM;
		this._EFILE = _EFILE;

	}

	@Override
	protected double evaluate(final IGPProgram program) {
		double result = 0.0f;

		long longResult = 0;

		/*
		 * for (int i = 0; i < _input1.size(); i++) { // Set the input values
		 * _xVariable.set(_input1.get(i)); _xVariable.set(_input1.get(i)); // //
		 * // Execute the genetically engineered algorithm long value =
		 * program.execute_int(0, NO_ARGS); // issues with int to // // //
		 * double
		 * 
		 * // The closer longResult gets to 0 the better the algorithm.
		 * longResult += Math.abs(value - _output[i]); }
		 * 
		 * for (int i = 0; i < out_put.size(); i++) { for (int j = 0; j <
		 * totalInput.size(); j++) {
		 * varLabels.get(j).set(totalInput.get(i).get(i)); long value =
		 * program.execute_int(0, NO_ARGS); longResult += Math.abs(value -
		 * _output[i]); } }
		 */

		int count = 0;

		System.out.println(totalInput.size());
		System.out.println();

		for (List<Double> input : totalInput) {
			_KLOC.set(input.get(0));
			_SCRN.set(input.get(1));
			_FORM.set(input.get(2));
			_FILE.set(input.get(3));
			_ESCRN.set(input.get(4));
			_EFORM.set(input.get(5));
			_EFILE.set(input.get(6));

			long value = (long) program.execute_double(0, NO_ARGS); // issues with int to
			// // // double

			// The closer longResult gets to 0 the better the algorithm.
			longResult += Math.abs(value - out_put.get(count));
			count++;

		}

		result = longResult;

		return result;
	}

}
