package entidades;
import java.math.BigDecimal;
/**
*
* @author Rafael Andre Siqueira
*  Classe DBO que representa a entidade de Deposito
*/
public class Deposito implements Operacao{
private int codDeposito;
private int codContaBancaria;
private BigDecimal valor;
/**
 * Construtor do objeto DBO
 * @param codDeposito c�digo do Deposito
 * @param codContaBancaria c�digo da conta banc�ria
 * @param valor valor do Deposito
 * @throws Exception caso algum par�metro seja inv�lido
 */
public Deposito(int codDeposito , int codContaBancaria , BigDecimal valor )throws Exception {

 if(codDeposito <1){
	 throw new Exception("Boleto: inicializa��o com c�digo da cobran�a inv�lido.");
 }
 if(codContaBancaria <1){
	 throw new Exception("Boleto: inicializa��o com c�digo do boleto inv�lido.");}

BigDecimal zero = new BigDecimal(0.0);
if(valor == null || valor.compareTo(zero)<=0){
	throw new Exception("Boleto: inicializa��o com valor do boleto inv�lido.");
}
    this.codDeposito   = codDeposito;
    this.codContaBancaria        = codContaBancaria;
    this.valor  = valor ;
}
/**
 * M�todo tradicional equals
 * @param obj objeto que ser� comparado
 * @return true, se os conte�dos dos dois objetos s�o compat�veis e exatamente iguais
 */
@Override
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

        Deposito aComparar = (Deposito) obj;

        if (this.codDeposito != aComparar.getCodDeposito()) {
            return false;
        }

        if ( this.codContaBancaria!=(aComparar.getCodContaBancaria())) {
            return false;
        }

       return (this.valor.equals((aComparar.getValor()))) ;
    }
    /**
     * M�todo tradicional hashCode
     * @return o c�digo de hash do objeto
     */
@Override
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
@Override
           public  String toString() {
               return "Deposito{" + "codDeposito=" + codDeposito + ", codContaBancaria=" + codContaBancaria + ", valor=" +valor + '}';
           }

	 /*
 N�o tem construtor de c�pia, pois n�o tem setters

    public Deposito(Deposito aCopiar) throws Exception */
    /*
	N�o tem clone, pois n�o tem setters
    public Object clone() */
    /**
     * Getter do c�digo do Deposito
     * @return o c�digo do Deposito
     */
    public  int getCodDeposito() {
        return this.codDeposito;
    }
    /**
     * Getter do c�digo da conta banc�ria
     * @return o c�digo da conta banc�ria
     */
@Override
    public  int getCodContaBancaria() {
        return this.codContaBancaria;
    }
   /**
     * Getter do valor do Deposito
     * @return o valor do Deposito
     */
@Override
    public  BigDecimal getValor() {
        return this.valor;
    } 
}
