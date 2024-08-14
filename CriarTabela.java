import java.sql.*;

public class CriarTabela {
    public static void main(String[] args) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlCriarTabela = "create table `db_teste`.`tbl_teste` (`id` int not null auto_increment, `nome` varchar(255) not null, `email` varchar(255) not null, `senha` varchar(255) not null, primary key (`id`));";
            Statement stmSqlCriarTabela = conexao.createStatement();
            stmSqlCriarTabela.addBatch(strSqlCriarTabela);
            stmSqlCriarTabela.executeBatch();
            stmSqlCriarTabela.close();
            System.out.println("Tabela criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Ops! Ocorreu o erro: " + e);
        }
    }
}