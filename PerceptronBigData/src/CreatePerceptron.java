
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;

import org.neuroph.nnet.MultiLayerPerceptron;

import org.neuroph.nnet.learning.BackPropagation;

import org.neuroph.core.data.DataSet;

import org.neuroph.core.data.DataSetRow;

import org.neuroph.util.TransferFunctionType;

public class CreatePerceptron {

	public static void createNetworkPerceptron() {

		DataSet trainingSet = new DataSet(1, 1);

		double[] angulosn = getVectorAngulosN();

		double[] senos = getSenos(getVectorAngulos());

		for (int x = 0; x < angulosn.length; x++) {

			trainingSet.addRow(new DataSetRow(new double[] { angulosn[x] }, new double[] { senos[x] }));

		}

		// create multi layer perceptron

		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 1, 6, 10, 20, 1);

		// learn the training set

		BackPropagation learningrules = new BackPropagation();

		learningrules.setMaxError(0.00001);

		myMlPerceptron.learn(trainingSet, learningrules);

		// save trained neural network

		myMlPerceptron.save("myMlPerceptron.nnet");

		// load saved neural network

		NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

		// test loaded neural network

		System.out.println("Rede Neural com 13 angulos perceptron gerada:");

		printNetwork(loadedMlPerceptron, trainingSet);

	}

	public static void printNetwork(NeuralNetwork nnet, DataSet testSet) {

		double[] saidas = new double[13];

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

		System.out.println("Erro da rede neural encontrado: [" + erro1 + "]");

	}

	private static double[] getVectorAngulos() {

		double[] angulos = new double[13];

		angulos[0] = 0;

		angulos[1] = 30;

		angulos[2] = 60;

		angulos[3] = 90;

		angulos[4] = 120;

		angulos[5] = 150;

		angulos[6] = 180;

		angulos[7] = 210;

		angulos[8] = 240;

		angulos[9] = 270;

		angulos[10] = 300;

		angulos[11] = 330;

		angulos[12] = 360;

		return angulos;

	}

	private static double[] getVectorAngulosN() {

		double[] angulos = getVectorAngulos();

		double[] angulosn = new double[angulos.length];

		for (int x = 0; x < angulos.length; x++) {

			angulosn[x] = angulos[x] / 360;

		}

		return angulosn;

	}

	private static double[] getSenos(double[] angulos) {

		double[] senos = new double[angulos.length];

		for (int x = 0; x < angulos.length; x++) {

			senos[x] = Math.sin(Math.toRadians(angulos[x]));

		}

		return senos;

	}

}