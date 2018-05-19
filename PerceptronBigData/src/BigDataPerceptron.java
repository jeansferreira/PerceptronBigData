import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import org.neuroph.core.NeuralNetwork;

import org.neuroph.core.data.DataSet;

import org.neuroph.core.data.DataSetRow;

import org.neuroph.nnet.MultiLayerPerceptron;

import org.neuroph.nnet.learning.BackPropagation;

import org.neuroph.util.TransferFunctionType;

public class BigDataPerceptron {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		List<Integer> neuronsInLayers = new ArrayList<Integer>();
		System.out.println("Criar a rede neural:");
		System.out.println("<==============================================>");
		CreatePerceptron.createNetworkPerceptron();
		System.out.println("<==============================================>");
		System.out.println("Testar a rede neural:");
		LoadPerceptron.generateNeural();
		System.out.println("<==============================================>");

	}

}