import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

/**
 * @author carlos
 *
 */
public class SimpleMathTest extends GPProblem {
	FileParser fp = new FileParser();
	private List<List<Double>> INPUTS = fp.file();
	private static int[] OUTPUT = { 829, 141, 467, 1215, 1517 };

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

	public SimpleMathTest() throws InvalidConfigurationException {
		super(new GPConfiguration());

		GPConfiguration config = getGPConfiguration();

		v_AFP = Variable.create(config, "v_AFP", CommandGene.IntegerClass);
		v_Input = Variable.create(config, "v_Input", CommandGene.IntegerClass);
		v_Output = Variable.create(config, "v_Output", CommandGene.IntegerClass);
		v_Enquiry = Variable.create(config, "v_Enquiry", CommandGene.IntegerClass);
		v_File = Variable.create(config, "v_File", CommandGene.IntegerClass);
		v_Interface = Variable.create(config, "v_Interface", CommandGene.IntegerClass);
		v_Added = Variable.create(config, "v_Added", CommandGene.IntegerClass);
		v_Changed = Variable.create(config, "v_Changed", CommandGene.IntegerClass);
		v_Deleted = Variable.create(config, "v_Deleted", CommandGene.IntegerClass);
		v_PDR_AFP = Variable.create(config, "v_PDR_AFP", CommandGene.IntegerClass);
		v_NPDR_AFP = Variable.create(config, "v_NPDR_AFP", CommandGene.IntegerClass);
		v_NPDU_UFP = Variable.create(config, "v_NPDU_UFP", CommandGene.IntegerClass);
		v_Resource = Variable.create(config, "v_Resource", CommandGene.IntegerClass);
		v_Dev = Variable.create(config, "v_Dev", CommandGene.IntegerClass);
		v_Duration = Variable.create(config, "v_Duration", CommandGene.IntegerClass);
		N_effort = Variable.create(config, "N_effort", CommandGene.IntegerClass);

		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(4);
		config.setPopulationSize(1000);
		config.setMaxCrossoverDepth(8);
		config.setFitnessFunction(new Fitness(INPUTS, OUTPUT, v_AFP, v_Input, v_Output, v_Enquiry, v_File, v_Interface,
				v_Added, v_Changed, v_Deleted, v_PDR_AFP, v_PDR_UFP, v_NPDR_AFP, v_NPDU_UFP, v_Resource, v_Dev,
				v_Duration, N_effort));
		config.setStrictProgramCreation(true);
	}

	@Override
	public GPGenotype create() throws InvalidConfigurationException {
		GPConfiguration config = getGPConfiguration();

		// The return type of the GP program.
		Class[] types = { CommandGene.IntegerClass };

		// Arguments of result-producing chromosome: none
		Class[][] argTypes = { {} };

		// Next, we define the set of available GP commands and terminals to
		// use.
		CommandGene[][] nodeSets = { { v_AFP, v_Input, v_Output, v_Enquiry, v_File, v_Interface, v_Added, v_Changed,
				v_Deleted, v_PDR_AFP, v_PDR_UFP, v_NPDR_AFP, v_NPDU_UFP, v_Resource, v_Dev, v_Duration, N_effort,
				new Add(config, CommandGene.IntegerClass), new Multiply(config, CommandGene.IntegerClass),
				new Terminal(config, CommandGene.IntegerClass, 0.0, 10.0, true) } };

		GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets, 20, true);

		return result;
	}

	public static void main(String[] args) throws Exception {
		GPProblem problem = new SimpleMathTest();

		GPGenotype gp = problem.create();
		gp.setVerboseOutput(true);
		gp.evolve(30);

		System.out.println("Formula to discover: x^2 + 2y + 3x + 5");
		gp.outputSolution(gp.getAllTimeBest());

	}

}
