package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import entidades.Emprestimo;
import java.io.File;

public class ArquivoEmprestimos extends Registro {
    public ArquivoEmprestimos() {}
    @Override
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
		streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
		String linha;
		ArrayList<Emprestimo> registros = new ArrayList<>();
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
    public int inserir(int codContaBancaria, int mesesParaPagar, BigDecimal valorParaQuitar, BigDecimal valorOriginal) throws Exception {
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
            streamOut = new BufferedWriter(new FileWriter(NOMEARQUIVO));
            streamOut.write(
                Integer.toString(proximoCodigoEmprestimo) +","+
                Integer.toString(codContaBancaria) +","+
                Integer.toString(mesesParaPagar) +","+
                valorParaQuitar.toString()+","+
                valorOriginal.toString() +","
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }
    private static final String NOMEARQUIVO = new File("").getAbsolutePath().concat("/src/arquivos/emprestimos.csv");
}
