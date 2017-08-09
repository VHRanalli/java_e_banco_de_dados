/*
 * Simples Agenda de Contatos (CRUD)
 * @author Victor Henrique Ranalli Barbosa
 * Classe principal que herda caracteristicas (código) do jFrame
 *extends -> HERANÇA
 */
package br.com.agenda.tela;



//importaçao de pacotes e recursos

import br.com.agenda.dal.Dal;
import java.sql.*;
import javax.swing.JOptionPane;

public class TelaContato extends javax.swing.JFrame {
     //iniciando variaveis e objetos para trabalhar com o banco de dados
    
    
    Connection conexao = null; //conexão com o banco
    PreparedStatement pst = null; //execultar comandos sql
    ResultSet rs = null; //retorno (banco de dados
    
    
    /**
     * Creates new form TelaContato
     */
    
    //metodo construtor
    public TelaContato() {
        initComponents();
        //iniciar a conexão (Modulo de Conexão
        conexao = Dal.conector();
        //System.out.println(conexao); //troble shooting (apoio ao entendimento de problemas
        //a estrutura abaixo troca o icone de acordo com o status do banco de dados (String conectado, null nao conectado
        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/dbok.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/dberror.png")));
        }
        
    }
    
    /**************************metodos******************/
    
    public void limpar(){
        txtId.setText(null);
        txtNome.setText(null);
        txtFone.setText(null);
        txtEmail.setText(null);
    }
    
    private void consultar(){
        //criar a query
        //iremos substituir o parametro ? pelo conteudo da caixa 
        String consulta = "select * from tb_contatos where id=?";
        //usaremos uma estrutura do tipo try catch para o tratamento de exeções
        try {
            //logica principal
            pst = conexao.prepareStatement(consulta);
            //a linha abaixo captura a caixa de texto txtid substitui  pelo parametro ?
            pst.setString(1, txtId.getText());
            //a linha abaixo execulta a query e recupera os dados do banco 
            rs = pst.executeQuery();
            // se existir um registro(contato) correspondente ao id , preencher as caixas de texto
            if (rs.next()) {
                txtNome.setText(rs.getString(2));// preencher o conteudo com o segundo (2) campo da tabela la do my sql
                //a linha abaixo preenche os campos txt do formulario com o conteudo do campo 2 da tabela tb_contatos
                txtFone.setText(rs.getString(3));
                txtEmail.setText(rs.getString(4));
            }else{
                limpar();
               JOptionPane.showMessageDialog(null, "usuario nao cadastrado", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e) {
            //caso ocorra uma exeção, exibir a mesagem de erro
            JOptionPane.showMessageDialog(null, e);
            //usando as variaveis conexao,pst,rs para recuperar e pesquisar o conteudo do banco de dados
            
            
        }
    }
    
    //metodo adicionar (create)
    private void adicionar(){
        String inserir = "insert into tb_contatos(id,nome,fone,email) values (?,?,?,?)";
        try {
            pst = conexao.prepareStatement(inserir);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtFone.getText());
            pst.setString(4, txtEmail.getText());
            
            //logica usada para validação ds campos do formulario
             if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtFone.getText().isEmpty())){
                 JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatorios", "ATENÇAO", JOptionPane.WARNING_MESSAGE);
             }  else {
                 
             
            
            
            //a logica abaixo e usada para identificar que a tabela atualizou
            //pst.executeUpdate; > comando para atualizar a tabela
            int confirma = pst.executeUpdate();
            if (confirma == 1) {
                JOptionPane.showMessageDialog(null,"Usuario cadastrado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                
                
                 limpar();
                
                
            } 
           }
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "O campo ID nao permite valores duplicados", "Informação", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, e);
            // passagem de parametros ?,?,?,?
            
        }
        
    }
    
    private void alterar(){
        String atualizar = "update tb_contatos set nome=?,fone=?,email=? where id=?";
        try {
            pst = conexao.prepareStatement(atualizar);
            pst.setString(1, txtNome.getText()); //atenção !!! Neste caso o 1º parametro e o nome 
            pst.setString(2, txtFone.getText());
            pst.setString(3, txtEmail.getText());
            pst.setString(4, txtId.getText()); //atenção esse e o ultimo parametro
            if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtFone.getText().isEmpty())){
                 JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatorios", "ATENÇAO", JOptionPane.WARNING_MESSAGE);
             }  else {
                 int confirma = pst.executeUpdate();
            if (confirma == 1) {
                JOptionPane.showMessageDialog(null,"Usuario atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                
               limpar();
                
           }
         }
            
            
      } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e); 
     }
   }
    
    //Metodo deletar
    private void deletar(){
        String apagar = "delete from tb_contatos where id=?";
        
        try {
           pst = conexao.prepareStatement(apagar);
           pst.setString(1, txtId.getText());
           int excluir = JOptionPane.showConfirmDialog(null, "tem certeza que deseja apagar este contato ?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                
                int confirma = pst.executeUpdate();
            if (confirma == 1) {
                JOptionPane.showMessageDialog(null,"Usuario adeletado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                
               limpar();
                
           }
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); 
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda de contatos");
        setResizable(false);

        jLabel1.setText("* ID");

        jLabel2.setText("* Nome");

        jLabel3.setText("* Telefone");

        jLabel4.setText("Email");

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/if_145_Action_183269.png"))); // NOI18N
        btnCreate.setToolTipText("Gera uma açao de inserção");
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/if_Search_858732.png"))); // NOI18N
        btnRead.setToolTipText("Gera uma ação de pesquisa ");
        btnRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/if_Update_984748.png"))); // NOI18N
        btnUpdate.setToolTipText("Gera uma ação de alteraçao de informaçoes");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/if_delete_678153.png"))); // NOI18N
        btnDelete.setToolTipText("Gera uma ação de exclusão");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/icones/Instavel.png"))); // NOI18N

        jLabel5.setText("* campos obrigatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        setSize(new java.awt.Dimension(626, 414));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // Chamando o metodo inserir
        adicionar();
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(TelaContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaContato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
