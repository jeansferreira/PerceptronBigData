import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

public class LoadPerceptron {

	public static void generateNeural() {

		// create training set (logical XOR function)

		DataSet trainingSet = new DataSet(1, 1);

		double[] angulosn = getVectorAngulosN();

		double[] senos = getSenos(getVectorAngulos());

		for (int x = 0; x < angulosn.length; x++) {

			trainingSet.addRow(new DataSetRow(new double[] { angulosn[x] }, new double[] { senos[x] }));

		}

		// Carregar rede neural criada

		NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");

		// test loaded neural network

		System.out.println("Testar rede neural criada com 75 angulos:");

		testNeuralNetwork(loadedMlPerceptron, trainingSet);

	}

	private static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

		double[] saidas = new double[75];

		int x = 0;

		for (DataSetRow dataRow : testSet.getRows()) {

			nnet.setInput(dataRow.getInput());

			nnet.calculate();

			double[] networkOutput = nnet.getOutput();

			System.out.print("Input: " + Arrays.toString(dataRow.getInput()));

			System.out.println(" Output: " + Arrays.toString(networkOutput));

			saidas[x] = networkOutput[0];

			x++;

		}

		double[] senos = getSenos(getVectorAngulos());

		double erro1 = 0;

		for (int z = 0; z < senos.length; z++) {

			erro1 += Math.abs((saidas[z] - senos[z]));

		}

		System.out.println("Erro Total do teste com 72 angulos: [" + erro1 + "]");

	}

	/**
	 * 
	 * Metodo que gera os 72 angulos para testar a rede neural
	 * 
	 * @return
	 * 
	 */

	private static double[] getVectorAngulos() {

		int inicio = 0;

		int fim = 360;

		int i = 5;

		double qtde = fim / i;

		double[] angulos = new double[(int) (qtde) + 1];

		for (int x = inicio; x <= qtde; x++) {

			angulos[x] = x * i;

		}

		return angulos;

	}

	/**
	 * 
	 * Metodo que normaliza os valores dos angulos
	 * 
	 * @return
	 * 
	 */

	private static double[] getVectorAngulosN() {

		double[] angulos = getVectorAngulos();

		// Normaliza os valores dos Angulos

		double[] angulosn = new double[angulos.length];

		for (int x = 0; x < angulos.length; x++) {

			angulosn[x] = angulos[x] / 360;

		}

		return angulosn;

	}

	/**
	 * 
	 * Metodo que gera os senos dos angulos
	 * 
	 * @param angulos
	 * 
	 * @return
	 * 
	 */

	private static double[] getSenos(double[] angulos) {

		double[] senos = new double[angulos.length];

		for (int x = 0; x < angulos.length; x++) {

			senos[x] = Math.sin(Math.toRadians(angulos[x]));

		}

		return senos;

	}
}