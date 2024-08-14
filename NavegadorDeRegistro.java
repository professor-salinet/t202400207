import java.sql.*;

public class NavegadorDeRegistro {
    public static String[] primeiroRegistro(String db, String tbl) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPrimeiroRegistro = "select * from `" + db + "`.`" + tbl + "` order by `id` asc limit 1;";
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

    public static String[] registroAnterior(String db, String tbl, String id) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        int idPessoa = Integer.parseInt(id);
        int proximoId = idPessoa - 1;
        if (proximoId >= 1) {
            String strSqlRegistroAnterior = "select * from `" + db + "`.`" + tbl + "` order by `id` desc;";
            Statement stmSqlRegistroAnterior = conexao.createStatement();
            ResultSet rstSqlRegistroAnterior = stmSqlRegistroAnterior.executeQuery(strSqlRegistroAnterior);
            String[] resultado = {"","",""};
            while (rstSqlRegistroAnterior.next()) {
                if (id.equals(rstSqlRegistroAnterior.getString("id"))) {
                    try {
                        rstSqlRegistroAnterior.next();
                        resultado[0] = rstSqlRegistroAnterior.getString("id");
                        resultado[1] = rstSqlRegistroAnterior.getString("nome");
                        resultado[2] = rstSqlRegistroAnterior.getString("email");
                    } catch (Exception e) {
                        System.out.println("Ops! Parece que já está no primeiro registro. Veja o erro: " + e);
                    }
                    break;
                }
            }
            stmSqlRegistroAnterior.close();
            if (resultado[0] == "") {
                return null;
            } else {
                return resultado;
            }
        } else {
            return null;
        }
    }

    public static String[] proximoRegistro(String db, String tbl, String id) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        try {
            String strSqlProximoRegistro = "select * from `" + db + "`.`" + tbl + "` order by `id` asc;";
            Statement stmSqlProximoRegistro = conexao.createStatement();
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            String[] resultado = {"","",""};
            while (rstSqlProximoRegistro.next()) {
                if (id.equals(rstSqlProximoRegistro.getString("id"))) {
                    rstSqlProximoRegistro.next();
                    resultado[0] = rstSqlProximoRegistro.getString("id");
                    resultado[1] = rstSqlProximoRegistro.getString("nome");
                    resultado[2] = rstSqlProximoRegistro.getString("email");
                    break;
                }
            }
            stmSqlProximoRegistro.close();
            if (resultado[0] == "") {
                return null;
            } else {
                return resultado;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] ultimoRegistro(String db, String tbl) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        try {
            String strSqlUltimoRegistro = "select * from `" + db + "`.`" + tbl + "` order by `id` desc limit 1;";
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
