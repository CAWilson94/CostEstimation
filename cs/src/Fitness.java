import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class Fitness extends GPFitnessFunction {

	private Integer[] _input1;
	private Integer[] _input2;
	private int[] _output;
	private Variable v_AFP;
	private Variable v_Input;
	private Variable v_Output;
	private Variable v_Enquiry;
	private Variable v_File;
	private Variable v_Interface;
	private Variable v_Added;
	private Variable v_Changed;
	private Variable v_Deleted;
	private Variable v_PDR_AFP;
	private Variable v_PDR_UFP;
	private Variable v_NPDR_AFP;
	private Variable v_NPDU_UFP;
	private Variable v_Resource;
	private Variable v_Dev;
	private Variable v_Duration;
	private Variable N_effort;

	private static Object[] NO_ARGS = new Object[0];

	public Fitness(Integer input1[], Integer input2[], int output[], Variable v_AFP, Variable v_Input,
			Variable v_Output, Variable v_Enquiry, Variable v_File, Variable v_Interface, Variable v_Added,
			Variable v_Changed, Variable v_Deleted, Variable v_PDR_AFP, Variable v_PDR_UFP, Variable v_NPDR_AFP,
			Variable v_NPDU_UFP, Variable v_Resource, Variable v_Dev, Variable v_Duration, Variable N_effort) {
		_input1 = input1;
		_input2 = input2;
		_output = output;
		v_AFP = v_AFP;
		v_Input = v_Input;
		v_Output = v_Output;
		v_Enquiry = v_Enquiry;
		v_File = v_File;
		v_Interface = v_Interface;
		v_Added = v_Added;
		v_Changed = v_Changed;
		v_Deleted = v_Deleted;
		v_PDR_AFP = v_PDR_AFP;
		v_PDR_UFP = v_PDR_UFP;
		v_NPDR_AFP = v_NPDR_AFP;
		v_NPDU_UFP = v_NPDU_UFP;
		v_Resource = v_Resource;
		v_Dev = v_Dev;

	}

	@Override
	protected double evaluate(final IGPProgram program) {

		double result = 0.0f;

		long longResult = 0;
		System.out.println();
		for (int i = 0; i < _input1.length; i++) {
			// Set the input values
			v_AFP.set(_input1[i]);
			_yVariable.set(_input2[i]);
			// Execute the genetically engineered algorithm
			long value = program.execute_int(0, NO_ARGS);
			// The closer longResult gets to 0 the better the algorithm.
			longResult += Math.abs(value - _output[i]);
		}

		result = longResult;

		return result;
	}

}
