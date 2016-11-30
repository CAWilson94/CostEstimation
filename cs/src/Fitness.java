import java.util.ArrayList;
import java.util.List;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class Fitness extends GPFitnessFunction {

	private List<List<Double>> inputs;
	public List<Double> _output;
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

	public Fitness(List<List<Double>> inputs, List<Double> _output, Variable v_AFP, Variable v_Input, Variable v_Output,
			Variable v_Enquiry, Variable v_File, Variable v_Interface, Variable v_Added, Variable v_Changed,
			Variable v_Deleted, Variable v_PDR_AFP, Variable v_PDR_UFP, Variable v_NPDR_AFP, Variable v_NPDU_UFP,
			Variable v_Resource, Variable v_Dev, Variable v_Duration, Variable N_effort) {
		inputs = inputs;
		_output = _output;
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

	public List<Variable> labs() {
		List<Variable> labels = new ArrayList<Variable>();
		labels.add(v_AFP);
		labels.add(v_Input);
		labels.add(v_Output);
		labels.add(v_Enquiry);
		labels.add(v_File);
		labels.add(v_Interface);
		labels.add(v_Added);
		labels.add(v_Changed);
		labels.add(v_Deleted);
		labels.add(v_PDR_AFP);
		labels.add(v_PDR_UFP);
		labels.add(v_NPDR_AFP);
		labels.add(v_NPDU_UFP);
		labels.add(v_Resource);
		labels.add(v_Dev);
		return labels;

	}

	@Override
	protected double evaluate(final IGPProgram program) {

		double result = 0.0f;

		List<Variable> labels = labs();

		// System.out.println(_output.toString());
		System.out.println(inputs.size() + "----------------------------------------------------------");

		long longResult = 10;
		/*
		 * 
		 * for (int i = 0; i < _output.size(); i++) { for (int j = 0; j <
		 * inputs.size(); j++) { labels.get(j).set(inputs.get(j).get(i)); }
		 * double value = program.execute_double(0, NO_ARGS); longResult +=
		 * Math.abs(value - _output.get(i)); }
		 */

		result = longResult;

		return result;
	}

}
