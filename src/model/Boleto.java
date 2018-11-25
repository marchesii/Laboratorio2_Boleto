package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Boleto {
	private long numeros; //14 digitos do boleto
	private LocalDate dataVencimento; //Data de vencimento do boleto
	private double valor; //valor do boleto
	private double valorJuros;
	private long dias;
	private double multa;
	
	public Boleto(long numeros) {
		this.numeros = numeros;
		extrairValor(numeros);
		extrairVencimento(numeros);
		extrairDiasVencidos();
	}
	
	public void setMulta(double valor) {
		if(dias > 0) {
			multa = valor;
		}else {
			multa = 0;
		}
	}
	
	public double getMulta() {
		return multa;
	}
	public long getDiasVencidos() {
		return dias;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	
	public double getValor() {
		return valor;
	}

	private void extrairValor(long numero) {
		long value;
		value = (long) (numero % Math.pow(10, 10));
		valor = value / 100.0;
		
	}
	
	private void extrairVencimento(long numero) {
		int dias;
		dias = (int) (numero / Math.pow(10, 10));
		LocalDate dataBase = LocalDate.of(1997, Month.OCTOBER, 07);
		this.dataVencimento = dataBase.plusDays(dias);
	}
	
	public String getVencimento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataVencimento.format(formatter);
	}
	
	public void extrairDiasVencidos() {
		long dias = 0;
		Duration duracao;
		LocalDate dataHoje = LocalDate.now();
		LocalDate dataPassada = dataVencimento;
		
		while(dataPassada.isBefore(dataHoje)) {
			dataPassada = dataPassada.plusDays(1);
			dias++;
		}

		this.dias = dias;
	}
}
