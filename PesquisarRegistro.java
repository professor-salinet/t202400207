import java.sql.*;

public class PesquisarRegistro {
    public static void main(String[] args) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlPesquisaRegistro = "select * from `db_teste`.`tbl_teste` where `nome`='Teste';";
            Statement stmSqlPesquisaRegistro = conexao.createStatement();
            ResultSet resultado = stmSqlPesquisaRegistro.executeQuery(strSqlPesquisaRegistro);
            while (resultado.next()) {
                System.out.println(resultado.getString("senha"));
            }
            System.out.println("Pesquisa realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Ops! Ocorreu o erro: " + e);
        }
    }
}
