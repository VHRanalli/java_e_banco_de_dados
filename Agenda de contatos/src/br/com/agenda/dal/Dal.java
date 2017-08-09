package br.com.agenda.dal;

/**
 * Modolu de Conexao - Dal
 * @author victor.hrbarbosa
 */

//a linha abaixo importa bibliotecas e recursos para trabalha com sql
import java.sql.*;



public class Dal {
   //criando um metodo com retorno
   //conection -> e um recurso da blibioteca java.sql.* usado para conexao ao banco de dados
    //conector() -> nome do metodo(qualquer nome)
    
    public static Connection conector(){
        // criando uma variavel especial para armazenar e consultar no banco de dados
        //"String de conexao"
        //java.sql.Connection -> classe usada para conexao com o banco
        // conexao e uma variavel (que pode ter qualquer nome)
        //atribuimos o valor nulo por questões de segurança
        
        java.sql.Connection conexao = null;
        //carregando o driver do banco de dados
        //obs: nao esqueça de importa o driver para o netbeans
        //STRING -> tipo de variavel
        //driver -> nome de variavel que recebe o driver
        
        String driver = "com.mysql.jdbc.Driver";
        //caminho do banco de dados (local, servidor local ou clound)
        //STRING -> tipo de variavel
        //url > nome da variavel que rece a informação do "caminho", porta padrao do mysql e nome do banco de dados
        String url = "jdbc:mysql://localhost:3306/dbagenda";
        //autenticação do banco de dados (usuario e senha com permissoes de acesso
        //obs:altera o usuario e a senha de acordo com o servidor do banco de dados
        //usar o root nao e umaboa pratica em servidores de empresas mas quando esta num ambiente local e necessario usalos para acessar o banco de dados
        
        String user = "root";
        String password = "senac@tat";
        //iremos ultilizar uma estrutura do tipo try catch  para tratamento de exeçoes
        //na tentativa de conectar ao banco de dados
        
        try {
        //se o banco de dados estiver disponivel estabeleça a conexao quando solicitado
        //usaremos mais recursos para estabelecer uma conexao
        Class.forName(driver);
        //atribua a variavel conexao as informaçoes necessarias para estabelecer uma conexao com o banco
        //DriverManager -> Gerenciamento do driver (MYSQL)
        //getConnection -> pegar a conexao das variavels url, user e password e transmitir a variavel conexao 
        conexao = DriverManager.getConnection(url,user,password);
        return conexao;
        
               
        } catch (Exception e) {
            //se o banco de dados estiver indisponivel (queda de conexao, problemas de infraestrutura, servidor desligado, etc
            //a linha abaixo ajuda a indentificar o problema de conexão
            //System.out.println(e);
            return null;
            
        }
        
        
    }
    
}
