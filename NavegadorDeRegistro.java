import java.sql.*;

public class NavegadorDeRegistro {
    public static String[] primeiroRegistro(String db, String tbl, String pesquisa) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPesquisa = "";
        if (pesquisa != null) {
            strSqlPesquisa = " where `nome` like '%" + pesquisa + "%' or `email` like '%" + pesquisa + "%'";
        }
        String strSqlPrimeiroRegistro = "select * from `" + db + "`.`" + tbl + "`" + strSqlPesquisa + " order by `id` asc limit 1;";
        Statement stmSqlPrimeiroRegistro = conexao.createStatement();
        ResultSet rstSqlPrimeiroRegistro = stmSqlPrimeiroRegistro.executeQuery(strSqlPrimeiroRegistro);
        rstSqlPrimeiroRegistro.next();
        String[] resultado = {
            rstSqlPrimeiroRegistro.getString("id"),
            rstSqlPrimeiroRegistro.getString("nome"),
            rstSqlPrimeiroRegistro.getString("email")
        };
        stmSqlPrimeiroRegistro.close();
        return resultado;
    }

    public static String[] registroAnterior(String db, String tbl, String id, String pesquisa) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPesquisa = "";
        if (pesquisa != null) {
            strSqlPesquisa = " and (`nome` like '%" + pesquisa + "%' or `email` like '%" + pesquisa + "%')";
        }
        int idPessoa = Integer.parseInt(id);
        int proximoId = idPessoa - 1;
        if (proximoId >= 1) {
            String strSqlRegistroAnterior = "select * from `" + db + "`.`" + tbl + "` where `id` < "+ id + strSqlPesquisa + " order by `id` desc limit 1;";
            Statement stmSqlRegistroAnterior = conexao.createStatement();
            String[] resultado = {"","",""};
            try {
                ResultSet rstSqlRegistroAnterior = stmSqlRegistroAnterior.executeQuery(strSqlRegistroAnterior);
                rstSqlRegistroAnterior.next();
                resultado[0] = rstSqlRegistroAnterior.getString("id");
                resultado[1] = rstSqlRegistroAnterior.getString("nome");
                resultado[2] = rstSqlRegistroAnterior.getString("email");
            } catch (Exception e) {
                System.out.println("Ops! Parece que já está no primeiro registro. Veja o erro: " + e);
                return null;
            }
            stmSqlRegistroAnterior.close();
            return resultado;
        } else {
            return null;
        }
    }

    public static String[] proximoRegistro(String db, String tbl, String id, String pesquisa) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPesquisa = "";
        if (pesquisa != null) {
            strSqlPesquisa = " and (`nome` like '%" + pesquisa + "%' or `email` like '%" + pesquisa + "%')";
        }
        try {
            String strSqlProximoRegistro = "select * from `" + db + "`.`" + tbl + "` where `id` > " + id + strSqlPesquisa + " order by `id` asc limit 1;";
            Statement stmSqlProximoRegistro = conexao.createStatement();
            String[] resultado = {"","",""};
            try {
                ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
                rstSqlProximoRegistro.next();
                resultado[0] = rstSqlProximoRegistro.getString("id");
                resultado[1] = rstSqlProximoRegistro.getString("nome");
                resultado[2] = rstSqlProximoRegistro.getString("email");
                stmSqlProximoRegistro.close();
            } catch (Exception e) {
                System.out.println("Ops! Parece que já está no último registro...");
                return null;
            }
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] ultimoRegistro(String db, String tbl, String pesquisa) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPesquisa = "";
        if (pesquisa != null) {
            strSqlPesquisa = " where `nome` like '%" + pesquisa + "%' or `email` like '%" + pesquisa + "%'";
        }
        try {
            String strSqlUltimoRegistro = "select * from `" + db + "`.`" + tbl + "`" + strSqlPesquisa + " order by `id` desc limit 1;";
            Statement stmSqlUltimoRegistro = conexao.createStatement();
            ResultSet rstSqlUltimoRegistro = stmSqlUltimoRegistro.executeQuery(strSqlUltimoRegistro);
            rstSqlUltimoRegistro.next();
            String[] resultado = {
                rstSqlUltimoRegistro.getString("id"),
                rstSqlUltimoRegistro.getString("nome"),
                rstSqlUltimoRegistro.getString("email")
            };
            stmSqlUltimoRegistro.close();
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] irParaId(String db, String tbl, String id) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        try {
            String strSqlRegistro = "select * from `" + db + "`.`" + tbl + "` where `id` = " + id + ";";
            Statement stmSqlRegistro = conexao.createStatement();
            ResultSet rstSqlRegistro = stmSqlRegistro.executeQuery(strSqlRegistro);
            rstSqlRegistro.next();
            String[] resultado = {
                rstSqlRegistro.getString("id"),
                rstSqlRegistro.getString("nome"),
                rstSqlRegistro.getString("email")
            };
            stmSqlRegistro.close();
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] pesquisarNome(String db, String tbl, String nome, String email) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        try {
            String strSqlPesquisarNome = "select * from `" + db + "`.`" + tbl + "` where `nome` like '%" + nome + "%' or `email` like '%" + email + "%' order by `id` asc limit 1;";
            Statement stmSqlPesquisarNome = conexao.createStatement();
            ResultSet rstSqlPesquisarNome = stmSqlPesquisarNome.executeQuery(strSqlPesquisarNome);
            rstSqlPesquisarNome.next();
            String[] resultado = {
                rstSqlPesquisarNome.getString("id"),
                rstSqlPesquisarNome.getString("nome"),
                rstSqlPesquisarNome.getString("email")
            };
            stmSqlPesquisarNome.close();
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean atualizarRegistro(String db, String tbl, String id, String nome, String email, char[] senha) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlUltimoRegistro = "update `" + db + "`.`" + tbl + "` set `nome` = '" + nome + "', `email` = '" + email + "', `senha` = '" + String.valueOf(senha) + "' where `id` = " + id + ";";
            Statement stmSqlUltimoRegistro = conexao.createStatement();
            stmSqlUltimoRegistro.addBatch(strSqlUltimoRegistro);
            stmSqlUltimoRegistro.executeBatch();
            stmSqlUltimoRegistro.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean cadastrarRegistro(String db, String tbl, String nome, String email, char[] senha) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlCadastrarRegistro = "insert into `" + db + "`.`" + tbl + "` (`nome`,`email`,`senha`) values ('" + nome + "','" + email + "','" + String.valueOf(senha) +"');";
            Statement stmSqlCadastrarRegistro = conexao.createStatement();
            stmSqlCadastrarRegistro.addBatch(strSqlCadastrarRegistro);
            stmSqlCadastrarRegistro.executeBatch();
            stmSqlCadastrarRegistro.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static String[] deletarRegistro(String db, String tbl, String id) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlDeletarRegistro = "delete from `" + db + "`.`" + tbl + "`  where `id` = " + id + ";";
            Statement stmSqlDeletarRegistro = conexao.createStatement();
            stmSqlDeletarRegistro.addBatch(strSqlDeletarRegistro);
            stmSqlDeletarRegistro.executeBatch();
            stmSqlDeletarRegistro.close();

            String strSqlProximoRegistro = "select * from `" + db + "`.`" + tbl + "`  where `id` >= " + id + ";";
            Statement stmSqlProximoRegistro = conexao.createStatement();
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            rstSqlProximoRegistro.next();
            String[] resultado = {
                rstSqlProximoRegistro.getString("id"),
                rstSqlProximoRegistro.getString("nome"),
                rstSqlProximoRegistro.getString("email")
            };
            stmSqlProximoRegistro.close();
            return resultado;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
