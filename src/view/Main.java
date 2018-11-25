package view;

import java.util.Scanner;

import model.Boleto;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long sequencia;
		double multa;
		double juros;
		Boleto boleto;
		double total;
		
		System.out.println("Informa a ultima sequencia numérica do boleto: ");
		sequencia = input.nextLong();
		System.out.println("Informe quanto de juros é cobrado por dia: ");
		juros = input.nextDouble()/100d;
		System.out.println("Informe a multa a ser paga: ");
		multa = input.nextDouble();
		
		boleto = new Boleto(sequencia);
		boleto.setMulta(multa);
		total = boleto.getValor();
		if(boleto.getDiasVencidos() == 0) {
			juros = 0;
		}else {
			for(int i = 1; i <= boleto.getDiasVencidos(); i++) {
				total+=(juros*total);
			}
			juros = total;
		}
		total += boleto.getMulta() + boleto.getValor();
		
		System.out.println("Data de vencimento: " + boleto.getVencimento());
		System.out.println("Valor: R$ " + boleto.getValor());
		System.out.println("Valor de multa: R$ " + boleto.getMulta());
		System.out.println("Valor de juros: R$ " + juros);
		System.out.println("Valor total: R$ " + total);
	}
}
