package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import entidades.Deposito;
import entidades.Emprestimo;

public class ArquivoEmprestimos extends Registro {
    public ArquivoEmprestimos() {
    }
    
    private int codContaBancaria;
	private int mesesParaPagar;
	private BigDecimal valorParaQuitar;
	private BigDecimal valorOriginal;
    
	private BufferedReader streamIn;
    private BufferedWriter streamOut;
    private static final String nomeArquivo = "/home/rafaelsiqueira/ProjetoFinalMC322/src/arquivos/emprestimos.csv";
    public int getProximoCodigo() throws Exception{
            ArrayList<Emprestimo> emprestimos = getEmprestimos();
            int codigoMaximo = 0;
            for(Emprestimo e: emprestimos){
                if(e.getCodEmprestimo() > codigoMaximo)
                    codigoMaximo = e.getCodEmprestimo();
            }
            return codigoMaximo + 1;
    }
    public ArrayList<Emprestimo> getEmprestimos() throws Exception {
		streamIn = new BufferedReader( new FileReader(nomeArquivo));
		String linha;
		ArrayList<Emprestimo> registros = new ArrayList<Emprestimo>();
		while((linha = streamIn.readLine()) != null){
			String[] valores = linha.split(",");
			registros.add(
				new Emprestimo(Integer.parseInt(valores[0]),
				Integer.parseInt(valores[1]),
                Integer.parseInt(valores[2]),
				new BigDecimal(Float.parseFloat(valores[3])),
                new BigDecimal(Float.parseFloat(valores[4]))
			));
		}
		streamIn.close();
		return registros;

    }
    public int inserir(int codContaBancaria, int mesesParaPagar, BigDecimal valorOriginal) throws Exception {
        if (codContaBancaria < 1) {
            throw new Exception("Emprestimos: inserção com código de conta bancária inválido");
        }
        
        if (mesesParaPagar < 1) {
            throw new Exception("Emprestimos: inserção com número de meses para pagar inválido");
        }
        
        if (valorOriginal == null || valorOriginal.equals(BigDecimal.ZERO)) {
            throw new Exception("Emprestimos: inserção com valor original inválido");
        }
        try{
            int proximoCodigoEmprestimo = getProximoCodigo();
            streamOut = new BufferedWriter(new FileWriter(nomeArquivo));
            streamOut.write(
                Integer.toString(proximoCodigoEmprestimo) +","+
                Integer.toString(codContaBancaria) +","+
                Integer.toString(mesesParaPagar) +","+
                valorOriginal.toString()+","+
                valorOriginal.toString() +","
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }
}
