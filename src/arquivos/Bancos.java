package arquivos;

import DBOs.Banco;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bancos{
	 /**
     * Construtor de objeto DAO
     * @throws Exception  por conta do construtor da superclasse lan�ar exce��o
     */
    private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "bancos.csv";
    public Bancos() throws Exception {
    }
    
    public int quantosBancos() throws Exception {
        return getBancos().length;
    }
    
    public Banco[] getBancos() throws Exception {
        streamIn = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        ArrayList<Banco> registros = new ArrayList<>();
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(',');
            registros.add(
                new Banco(Integer.parseInt(valores[0]),
                valores[1],
                Float.parseFloat(valores[2]), 
                Float.parseFloat(valores[3]),
                new BigDecimal(Float.parseFloat(valores[4])),
                new BigDecimal(Float.parseFloat(valores[5])),
                Integer.parseInt(valores[6])
              ));
        }
        streamIn.close();
        return registros;
    }
    
    public int inserir(String nome, float jurosPoupanca, float jurosEmprestimo, int mesesEmprestimo, BigDecimal emprestimoMinimo, 
    BigDecimal emprestimoMaximo) throws Exception {
        if (nome == null || nome.equals("")) {
            throw new Exception("Bancos: inserção com nome inválido");
        }
        
        if (jurosPoupanca < 0) {
            throw new Exception("Bancos: inserção com valor de juros sobre poupança inválido");
        }
        
        if (jurosEmprestimo < 0) {
            throw new Exception("Bancos: inserção com valor de juros sobre empréstimo inválido");
        }
        
        if (mesesEmprestimo < 1) {
            throw new Exception("Bancos: inserção com valor de meses para pagamento de empréstimo inválido");
        }
        
        if (emprestimoMinimo == null || emprestimoMinimo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Bancos: inserção com valor de empréstimo mínimo inválido");
        }
        
        if (emprestimoMaximo == null || emprestimoMaximo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Bancos: inserção com valor de empréstimo máximo inválido");
        }
        try{
            int quantosBancos = quantosBancos();
            streamOut = new BufferedWriter( new FileReader(nomeArquivo));
            streamOut.write(
                Integer.toString(quantosBancos+1) +","+
                nome +","+
                Float.toString(jurosPoupanca)+","+
                Float.toString(jurosEmprestimo)+","+
                Integer.toString(mesesEmprestimo)+","+
                BigDecimal.toString(emprestimoMinimo)+","+
                BigDecimal.toString(emprestimoMaximo)
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }
}
