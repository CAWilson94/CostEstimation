import java.util.ArrayList;
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
	@SuppressWarnings("boxing")
	// private static Integer[] INPUT_1 = { 26, 8 };
	static FileParser fp = new FileParser();
	private static List<Double> INPUT_1 = fp.file().get(1);

	@SuppressWarnings("boxing")
	private static Integer[] INPUT_2 = { 35, 24 };

	private static int[] OUTPUT = { 829, 141, 467 };

	private Variable _xVariable;
	private Variable _yVariable;
	

	/* Custom Params */
	private List<List<Double>> totalInput = fp.file();
	private List<Double> OUTPUTtotal = totalInput.get(totalInput.size() - 1);
	// Labels for each column
	private List<Variable> labels = new ArrayList<Variable>(); 

	public SimpleMathTest() throws InvalidConfigurationException {
		super(new GPConfiguration());

		GPConfiguration config = getGPConfiguration();

		_xVariable = Variable.create(config, "X", CommandGene.IntegerClass);
		_yVariable = Variable.create(config, "Y", CommandGene.IntegerClass);

		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(4);
		config.setPopulationSize(1000);
		config.setMaxCrossoverDepth(8);
		config.setFitnessFunction(new SimpleMathTestFitnessFunction(totalInput, OUTPUT, _xVariable, _yVariable));
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
		CommandGene[][] nodeSets = { { _xVariable, _yVariable, new Add(config, CommandGene.IntegerClass),
				new Multiply(config, CommandGene.IntegerClass),
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