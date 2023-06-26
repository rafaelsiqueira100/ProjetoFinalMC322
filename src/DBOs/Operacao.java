package DBOs;

public interface Operacao{
    
    private int codContaBancaria;
    
    private BigDecimal valor;
    
    public int getCodContaBancaria();
    
    public BigDecimal getValor();
}