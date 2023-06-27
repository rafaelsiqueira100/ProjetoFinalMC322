/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.math.BigDecimal;

/**
 *
 * @author rafaelsiqueira
 */
public class ContaUniversitaria extends Conta{
    String RA;
    /**
     * Construtor do objeto 
     * @param codContaBancaria c�digo da conta banc�ria
     * @param codCliente c�digo do cliente
     * @param codBanco c�digo do banco
     * @param senha senha da conta banc�ria
     * @param saldo saldo da conta banc�ria
     * @param codAgencia c�digo da ag�ncia da conta banc�ria
     * @param RA registro acadêmico

     * @throws Exception caso algum par�metro seja inv�lido
     */
    public ContaUniversitaria(int codContaBancaria, int codCliente, int codBanco, String senha, BigDecimal saldo, String codAgencia , String RA) throws Exception {
        if (senha == null || senha.equals("")) {
            throw new Exception ("ContaBancaria: inicializa��o com senha inv�lida.");
        }
        if (saldo == null ) {
            throw new Exception ("ContaBancaria: inicializa��o com saldo inv�lido.");
        }
     if(codContaBancaria <1){
             throw new Exception("ContaBancaria: inicializa��o com c�digo da conta bancaria inv�lido.");
     }
     if(codCliente <1){
             throw new Exception("ContaBancaria: inicializa��o com c�digo do cliente inv�lido.");}
     if(codBanco <1){
             throw new Exception("ContaBancaria: inicializa��o com c�digo do banco inv�lido.");}
     if(codAgencia.compareTo("0")<0){
             throw new Exception("ContaBancaria: inicializa��o com c�digo da ag�ncia inv�lido.");
     }


        this.codContaBancaria    = codContaBancaria;
        this.codCliente          = codCliente;
        this.codBanco = codBanco;
        this.saldo = saldo;
        this.senha =senha;
        this.codAgencia = codAgencia;
        this.RA = RA;
    }
    /**
     * M�todo tradicional equals
     * @param obj objeto que ser� comparado
     * @return true, se os conte�dos dos dois objetos s�o compat�veis e exatamente iguais
     */
    public  boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        if (obj == this) {
            return true;
        }
        
        ContaUniversitaria aComparar = (ContaUniversitaria) obj;
        
        if (this.codContaBancaria != aComparar.getCodContaBancaria()) {
            return false;
        }
        
        if (! this.senha.equals(aComparar.getSenha())) {
            return false;
        }
        
        if (! this.saldo.equals((aComparar.getSaldo()))) {
            return false;
        }
        if ( this.codCliente != aComparar.getCodCliente()) {
            return false;
        }
        if ( this.codBanco != aComparar.getCodBanco()) {
            return false;
        }
        if(! this.codAgencia.equals(aComparar.getCodAgencia())){
        	return false;
        }
        if(! this.RA.equals(aComparar.getRA())){
            return false;
        }
        return true;
    }
    public String getRA(){
        return this.RA;
    }
    /**
     * M�todo tradicional hashCode
     * @return o c�digo de hash do objeto
     */
    public  int hashCode() {
        final int INICIAL = 2;
        final int PRIMO   = 13;

        int hashCode = INICIAL;
        
        hashCode = PRIMO * hashCode + this.senha.hashCode();
        hashCode = PRIMO * hashCode + this.saldo.hashCode();
        hashCode = PRIMO * hashCode + this.codAgencia.hashCode();
        hashCode = PRIMO * hashCode + this.RA.hashCode();

        return hashCode;
    }
    /**
     * M�todo tradicional toString
     * @return o objeto na forma de String
     */
    public  String toString() {
        return "ContaUniversitaria{" + "codContaBancaria=" + codContaBancaria + ", senha=" + senha + ", saldo=" + saldo +", codCliente=" +codCliente + ", codBanco=" + codBanco+", codAgencia=" + codAgencia+", RA="+RA+'}';
    }
    
}
