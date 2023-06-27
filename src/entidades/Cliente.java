package entidades;

/**
* @author Rafael Andre Siqueira
* Classe DBO que representa a entidade de Cliente
*/
public class Cliente { 
    private int codCliente;
    private String nome;

    /**
     * Construtor do objeto DBO
     * @param codCliente c�digo do cliente
     * @param nome nome do cliente
     * @throws Exception caso algum par�metro seja inv�lido
     */
    public Cliente(int codCliente, String nome) throws Exception {
        if (nome == null || nome.equals("")) {
            throw new Exception ("Cliente: inicializa��o com nome inv�lido.");
        }
        
        if(codCliente <1){
            throw new Exception("Cliente: inicializa��o com c�digo cliente inv�lido.");
        }
        this.codCliente    = codCliente;
        this.nome          = nome;
    }
    
    /**
     * Construtor usado para inserção, logo, ainda não se sabe o código
     * @param nome nome do cliente
     */
    public Cliente(String nome) throws Exception {
        if (nome == null || nome.equals("")) {
            throw new Exception ("Cliente: inicializa��o com nome inv�lido.");
        }
        
        this.nome = nome;
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
     
     Cliente aComparar = (Cliente) obj;
     
     if (this.codCliente != aComparar.getCodCliente()) {
         return false;
     }
     
     if (! this.nome.equals(aComparar.getNome())) {
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
        
        hashCode = PRIMO * hashCode + this.nome.hashCode();
        
        return hashCode;
    }

    /**
     * M�todo tradicional toString
     * @return o objeto na forma de String
     */
        public  String toString() {
            return "Cliente{" + "codCliente=" + codCliente + ", nome=" + nome +  '}';
        }
       
        
        /*
     N�o tem construtor de c�pia, pois n�o tem setters
     
        public Cliente (Cliente aCopiar) throws Exception */
        /*
    	N�o tem clone, pois n�o tem setters
        public Object clone() */
        /**
         * Getter do c�digo cliente
         * @return o c�digo cliente
         */
        public  int getCodCliente() {
            return this.codCliente;
        }
        /**
         * Getter do nome do cliente
         * @return o nome do cliente
         */
        public  String getNome() {
            return this.nome;
        }


}