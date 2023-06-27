/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import DBOs.TipoCobranca;
import java.sql.ResultSet;

/**
 *
 * @author u16189
 */
public class TipoCobrancas extends Dao {
    public TipoCobrancas() throws Exception {
        super();
    }
    
    public TipoCobranca getTipoCobranca(int codTipoCobranca) throws Exception {
        if (codTipoCobranca < 1) {
            throw new Exception("TipoCobrancas: busca por tipo de cobrança com  código inválido");
        }
        
        TipoCobranca aRetornar = null;
        
        String sql = "SELECT * FROM TipoCobranca WHERE codTipoCobranca = ?;";
        
        this.bd.prepareStatement(sql);
        
        this.bd.setInt(1, codTipoCobranca);
        
        ResultSet resultadoTipoCobranca = this.bd.executeQuery();
        
        if (resultadoTipoCobranca.next()) {
            aRetornar = new TipoCobranca(resultadoTipoCobranca.getInt(1), resultadoTipoCobranca.getString(2));
        }
        
        return aRetornar;
    }
}
