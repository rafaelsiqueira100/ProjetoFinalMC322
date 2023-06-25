package DBOs;

import java.math.BigDecimal;

public class Emprestimo {
	private int codEmprestimo;
	private int codContaBancaria;
	private int mesesParaPagar;
	private BigDecimal valorParaQuitar;
	private BigDecimal valorOriginal;	
	
	public Emprestimo(int codEmprestimo, int codContaBancaria, int mesesParaPagar, BigDecimal valorParaQuitar, BigDecimal valorOriginal) {
		this.codEmprestimo    = codEmprestimo;
		this.codContaBancaria = codContaBancaria;
		this.mesesParaPagar   = mesesParaPagar;
		this.valorOriginal    = valorParaQuitar;
		this.valorOriginal    = valorOriginal;
	}
	
	public int getCodEmprestimo() {
		return this.codEmprestimo;
	}
	
	public int getCodContaBancaria() {
		return this.codContaBancaria;
	}
	
	public int getMesesParaPagar() {
		return this.mesesParaPagar;
	}
	
	public BigDecimal getValorParaQuitar() {
		return this.valorParaQuitar;
	}
	
	public BigDecimal getValorOriginal() {
		return this.valorOriginal;
	}
}