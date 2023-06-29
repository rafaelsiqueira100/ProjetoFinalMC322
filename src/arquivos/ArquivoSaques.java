package arquivos;

import entidades.Saque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ArquivoSaques extends Registro{

    public ArquivoSaques() {    }
    @Override
    public int getProximoCodigo() throws Exception{
        ArrayList<Saque> saques = getSaques();
        int codigoMaximo = 0;
        for(Saque s: saques){
            if(s.getCodSaque() > codigoMaximo)
                codigoMaximo = s.getCodSaque();
        }
        return codigoMaximo+1;
    }
    public ArrayList<Saque> getSaques() throws Exception{
        ArrayList<Saque> registros;
        registros = new ArrayList<>();
        streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
        String linha;
        while((linha = streamIn.readLine()) != null){
            String[] valores = linha.split(",");
                registros.add(
                    new Saque(Integer.parseInt(valores[0]),
                    Integer.parseInt(valores[1]),
                    new BigDecimal(Float.parseFloat(valores[2]))
                ));
        }
        streamIn.close();
        return registros;
    }

    public int inserir(int codContaBancaria, BigDecimal valor) throws Exception {
        if (codContaBancaria < 0) {
            throw new Exception("Saques: inclusão de saque com código de conta bancária inválido");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Saques: inclusão de saque com valor inválido");
        }
		try{
            int proximoCodigoSaque = getProximoCodigo();
            streamOut = new BufferedWriter( new FileWriter(NOMEARQUIVO));
            streamOut.write(
                Integer.toString(proximoCodigoSaque) +","+
                Integer.toString(codContaBancaria) +","+
                valor.toString()
            );
            streamOut.close();
        }
        catch(Exception e){
            return -1;
        }
        return 1;
    }

    public int quantosSaques(int codContaBancaria) throws Exception {
		        if (codContaBancaria < 1) {
		            throw new Exception("Saques: busca por depósitos com código de conta bancária inválido");
		        }
		        return getSaques().size();
	    }

	    public ArrayList<Saque> getSaques(int codContaBancaria) throws Exception {
		        if (codContaBancaria < 1) {
		            throw new Exception("Saques: busca por depósitos com código de conta bancária inválido");
		        }

				ArrayList<Saque> registros = new ArrayList<>();
				streamIn = new BufferedReader( new FileReader(NOMEARQUIVO));
				String linha;
				while((linha = streamIn.readLine()) != null){
					String[] valores = linha.split(",");
					int codContaBancariaAtual = Integer.parseInt(valores[1]);
					if(codContaBancaria == codContaBancariaAtual){
						registros.add(
							new Saque(Integer.parseInt(valores[0]),
							Integer.parseInt(valores[1]),
							new BigDecimal(Float.parseFloat(valores[2]))
						));
					}
				}
				streamIn.close();
				return registros;
    }
    private static final String NOMEARQUIVO = "C:\\Users\\Rafael Siqueira\\OneDrive\\Área de Trabalho\\Projeto Final MC322\\Projeto Prática Profissional\\ProjetoPPII\\ProjetoFinalMC322\\src\\arquivos\\saques.csv";

}
