/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import arquivos.DAOs;
import entidades.Banco;
import entidades.Conta;
import entidades.ContaUniversitaria;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author ze
 */
public class JanelaEmprestimo extends javax.swing.JFrame {
    Conta contaAtual;
    public JanelaEmprestimo(Conta contaAtual) throws Exception {
        if (contaAtual == null) {
            throw new Exception("JanelaEmprestimo: inicialização com ContaBancária nula");
        }
        
        initComponents();
        
        this.contaAtual = contaAtual;
        
        lblBanco.setText(DAOs.getTabelaContas().getBanco(contaAtual).getNome());
        lblSaldo.setText(contaAtual.getSaldo().toString());
        Banco bancoAtual = DAOs.getTabelaContas().getBanco(contaAtual);
        float mesesMaximosParaPagar;
        if(contaAtual instanceof ContaUniversitaria){
            mesesMaximosParaPagar = bancoAtual.getJurosEmprestimo();
        }
        else{
            mesesMaximosParaPagar = (float) (0.7 * bancoAtual.getJurosEmprestimo());
        }
        cbxMesesParaPagar.removeAllItems();
        for(int i=1; i< mesesMaximosParaPagar;i++){
            cbxMesesParaPagar.insertItemAt(Integer.toString(i), i-1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxMesesParaPagar = new javax.swing.JComboBox<>();
        txtSenha = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSolicitar = new javax.swing.JButton();
        lblBanco = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Banco:");

        cbxMesesParaPagar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        jLabel3.setText("Senha:");

        jLabel4.setText("Valor:");

        jLabel5.setText("R$");

        jLabel6.setText("Gostaria de quitar a dívida em ");

        jLabel7.setText("mes(es)");

        btnSolicitar.setText("Solicitar Empréstimo");
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        lblBanco.setText("Banco");

        jLabel8.setText("Saldo:");

        jLabel9.setText("R$");

        lblSaldo.setText("Saldo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSolicitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblBanco)
                                            .addComponent(lblSaldo))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(147, 147, 147)
                                        .addComponent(cbxMesesParaPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                        .addComponent(jLabel7))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenha)
                                    .addComponent(txtValor))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(lblSaldo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxMesesParaPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(14, 14, 14)
                .addComponent(btnSolicitar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        try{
        Banco bancoAtual = DAOs.getTabelaContas().getBanco(contaAtual);
        BigDecimal valorMaximoEmprestimo;
        if(contaAtual instanceof ContaUniversitaria){
            valorMaximoEmprestimo = bancoAtual.getEmprestimoMaximo();
        }
        else{
            valorMaximoEmprestimo = new BigDecimal(0.7*bancoAtual.getEmprestimoMaximo().floatValue());
        }
        float valor = Float.parseFloat(txtValor.getText());
        if(valor > valorMaximoEmprestimo.floatValue()){
            JOptionPane.showMessageDialog(this, "Valor solicitado maior que o máximo de seu banco, que é R$" +valorMaximoEmprestimo.toString());
            return;
        }
        
        if (! txtSenha.getText().equals(contaAtual.getSenha())) {
            JOptionPane.showMessageDialog(this, "Senha inválida");
        } else {
            
            if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja solicitar este empréstimo?") == JOptionPane.OK_OPTION) {
                int meses = Integer.parseInt(cbxMesesParaPagar.getSelectedItem().toString());
                BigDecimal valorParaQuitar = new BigDecimal((float)Math.pow(valor *(1+bancoAtual.getJurosEmprestimo()/100.0),meses));
                
                
                DAOs.getTabelaEmprestimos().inserir(contaAtual.getCodContaBancaria(),meses , valorParaQuitar, new BigDecimal(valor));
                DAOs.getTabelaContas().incrementar(contaAtual, new BigDecimal(Float.parseFloat(txtValor.getText())));
                this.dispose();
            }
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao solicitar empréstimo! Verifique os dados e tente novamente!");
        }

    }//GEN-LAST:event_btnSolicitarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //new JanelaEmprestimo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JComboBox<String> cbxMesesParaPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
