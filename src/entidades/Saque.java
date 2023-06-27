package entidades;
import java.math.BigDecimal;

public class Saque implements Operacao{
    private int codSaque;
    private int codContaBancaria;
    private BigDecimal valor;
/**
 * Construtor do objeto DBO
 * @param codSaque c�digo do saque
 * @param codContaBancaria c�digo da conta banc�ria
 * @param valor valor do saque
 * @throws Exception caso algum par�metro seja inv�lido
 */
public Saque(int codSaque , int codContaBancaria , BigDecimal valor )throws Exception {

 if(codSaque <1){
	 throw new Exception("Boleto: inicializa��o com c�digo da cobran�a inv�lido.");
 }
 if(codContaBancaria <1){
	 throw new Exception("Boleto: inicializa��o com c�digo do boleto inv�lido.");}

BigDecimal zero = new BigDecimal(0.0);
if(valor == null || valor.compareTo(zero)<=0){
	throw new Exception("Boleto: inicializa��o com valor do boleto inv�lido.");
}
    this.codSaque   = codSaque;
    this.codContaBancaria        = codContaBancaria;
    this.valor  = valor ;
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

        Saque aComparar = (Saque) obj;

        if (this.codSaque != aComparar.getCodSaque()) {
            return false;
        }

        if ( this.codContaBancaria!=(aComparar.getCodContaBancaria())) {
            return false;
        }

       if (! this.valor.equals((aComparar.getValor()))) {
            return false;
        }
       
        return true;
    }
    /**
     * M�todo tradicional hashCode
     * @return o c�digo de hash do objeto
     */
       public  int hashCode() {
           final int INICIAL = 2;
           final int PRIMO   = 13;

           int hashCode = INICIAL;

  
       
          hashCode = PRIMO * hashCode + this.valor.hashCode();


           return hashCode;
       }
       /**
        * M�todo tradicional toString
        * @return o objeto na forma de String
        */
           public  String toString() {
               return "Saque{" + "codSaque=" + codSaque + ", codContaBancaria=" + codContaBancaria + ", valor=" +valor + '}';
           }

	 /*
 N�o tem construtor de c�pia, pois n�o tem setters

    public Saque(Saque aCopiar) throws Exception */
    /*
	N�o tem clone, pois n�o tem setters
    public Object clone() */
    /**
     * Getter do c�digo do saque
     * @return o c�digo do saque
     */
    public  int getCodSaque() {
        return this.codSaque;
    }
    /**
     * Getter do c�digo da conta banc�ria
     * @return o c�digo da conta banc�ria
     */
    public  int getCodContaBancaria() {
        return this.codContaBancaria;
    }
   /**
     * Getter do valor do saque
     * @return o valor do saque
     */
    public  BigDecimal getValor() {
        return this.valor;
    } 
}
