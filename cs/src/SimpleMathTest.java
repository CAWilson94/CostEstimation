import java.util.ArrayList;
import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Subtract;
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

	static FileParser fp = new FileParser();
	private Variable _KLOC;
	private Variable _SCRN;
	private Variable _FORM;
	private Variable _FILE;
	private Variable _ESCRN;
	private Variable _EFORM;
	private Variable _EFILE;

	/* Custom Params */
	private List<List<Double>> totalInput = fp.file();
	int size = totalInput.size() - 1;
	private List<Double> OUTPUTtotal = totalInput.get(size);

	// Labels for each column
	private ArrayList<Variable> labels = new ArrayList<Variable>();

	public SimpleMathTest() throws InvalidConfigurationException {
		super(new GPConfiguration());

		GPConfiguration config = getGPConfiguration();

		// Custom label maker
		_KLOC = Variable.create(config, "KLOC", CommandGene.DoubleClass);
		_SCRN = Variable.create(config, "SCRN", CommandGene.DoubleClass);
		_FORM = Variable.create(config, "FORM", CommandGene.DoubleClass);
		_FILE = Variable.create(config, "FILE", CommandGene.DoubleClass);
		_ESCRN = Variable.create(config, "_ESCRN", CommandGene.DoubleClass);
		_EFORM = Variable.create(config, "EFORM", CommandGene.DoubleClass);
		_EFILE = Variable.create(config, "EFILE", CommandGene.DoubleClass);

		labels.add(_KLOC);
		labels.add(_SCRN);
		labels.add(_FORM);
		labels.add(_FILE);
		labels.add(_ESCRN);
		labels.add(_EFORM);
		labels.add(_EFILE);

		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(4);
		config.setPopulationSize(1000);
		config.setMaxCrossoverDepth(8);

		List<List<Double>> INPUT = new ArrayList<List<Double>>();
		for (int i = 0; i < totalInput.size() - 1; i++) {
			INPUT.add(totalInput.get(i));
		}
		config.setFitnessFunction(new SimpleMathTestFitnessFunction(INPUT, OUTPUTtotal, labels));
		config.setStrictProgramCreation(true);
	}

	@Override
	public GPGenotype create() throws InvalidConfigurationException {
		GPConfiguration config = getGPConfiguration();

		// The return type of the GP program.
		Class[] types = { CommandGene.DoubleClass };

		// Arguments of result-producing chromosome: none
		Class[][] argTypes = { {} };

		ArrayList<CommandGene> badLabels = new ArrayList<CommandGene>();
		for (int i = 0; i < labels.size(); i++) {
			badLabels.add(labels.get(i));
		}

		badLabels.add(new Add(config, CommandGene.DoubleClass));
		badLabels.add(new Multiply(config, CommandGene.DoubleClass));
		badLabels.add(new Subtract(config, CommandGene.DoubleClass));
		badLabels.add(new Divide(config, CommandGene.DoubleClass));
		badLabels.add(new Terminal(config, CommandGene.DoubleClass, 0.0, 10.0, false));

		// Next, we define the set of available GP commands and terminals to
		// use.
		CommandGene[] badLabelArray = new CommandGene[badLabels.size()];

		for (int i = 0; i < badLabelArray.length; i++) {
			badLabelArray[i] = badLabels.get(i);
		}
		CommandGene[][] nodeSets = { badLabelArray };

		GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets, 20, true);

		return result;
	}

	public static void main(String[] args) throws Exception {
		GPProblem problem = new SimpleMathTest();
		GPGenotype gp = problem.create();
		gp.setVerboseOutput(true);
		gp.evolve(30);
		gp.outputSolution(gp.getAllTimeBest());
	}

}