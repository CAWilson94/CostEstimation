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

public class CostEst extends GPProblem {

	static FileParser fp = new FileParser();

	/* Custom Params */
	private List<List<Double>> totalInput = fp.file();
	int size = totalInput.size() - 1;
	private List<Double> OUTPUTtotal = totalInput.get(size);

	// Labels for each column
	private List<String> labels = fp.getLabels();
	List<Variable> labels4dayz = new ArrayList<Variable>();

	public CostEst() throws InvalidConfigurationException {
		super(new GPConfiguration());

		GPConfiguration config = getGPConfiguration();

		for (int i = 0; i < labels.size() - 1; i++) {
			labels4dayz.add(Variable.create(config, labels.get(i), CommandGene.DoubleClass));
		}

		/**
		 * *****************YOUR SETTINGS ARE HERE********************
		 */
		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(100);
		config.setPopulationSize(1000);
		config.setMaxCrossoverDepth(200); // Does this make a blind bit of difference?

		List<List<Double>> INPUT = new ArrayList<List<Double>>();
		for (int i = 0; i < totalInput.size() - 1; i++) {
			INPUT.add(totalInput.get(i));
		}

		config.setFitnessFunction(new FitnessFunction(INPUT, OUTPUTtotal, labels4dayz));
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
		for (int i = 0; i < labels4dayz.size(); i++) {
			badLabels.add(labels4dayz.get(i));
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
		csvWriter csv = new csvWriter();
		csv.extractResults();
		long startTime = System.currentTimeMillis();
		GPProblem problem = new CostEst();
		GPGenotype gp = problem.create();
		gp.setVerboseOutput(true);
		gp.evolve(100);
		gp.outputSolution(gp.getAllTimeBest());
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}

}