// import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private final JLabel lblLogin = new JLabel("Login");
    private final JLabel lblSenha = new JLabel("Senha");
    public static final JLabel lblNotificacoes = new JLabel(setHtmlFormat("Bem vindo! Faça login ou cadastre-se."), SwingConstants.CENTER);

    private final JTextField txtLogin = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();

    private final JButton btnLogin = new JButton("Login");
    private final JButton btnCadastrar = new JButton("Cadastrar");

    private final JCheckBox cbxAceite = new JCheckBox("Aceito os termos");

    private final String dbPadrao = "db_teste";
    private final String tblPadrao = "tbl_teste";

    public LoginFrame() {
        super("Tela de Login");
        setLayout(new GridLayout(7,1,5,5));

        JPanel pnlLinha1 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha2 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha3 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha4 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha5 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha6 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha7 = new JPanel(new GridLayout(1,2,5,5));
        JPanel pnlLinha8 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha9 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha10 = new JPanel(new GridLayout(1,1,5,5));

        pnlLinha1.add(new JLabel());
        // add(pnlLinha1);

        pnlLinha2.add(lblLogin);
        add(pnlLinha2);

        pnlLinha3.add(txtLogin);
        add(pnlLinha3);

        pnlLinha4.add(lblSenha);
        add(pnlLinha4);

        pnlLinha5.add(txtSenha);
        add(pnlLinha5);

        pnlLinha6.add(new JLabel());
        // add(pnlLinha6);

        pnlLinha7.add(btnLogin);
        pnlLinha7.add(btnCadastrar);
        add(pnlLinha7);

        pnlLinha8.add(new JLabel());
        // add(pnlLinha8);

        cbxAceite.setSelected(true);
        cbxAceite.setHorizontalAlignment(SwingConstants.CENTER);
        pnlLinha9.add(cbxAceite);
        add(pnlLinha9);

        pnlLinha10.add(lblNotificacoes);
        add(pnlLinha10);

        btnLogin.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        if (txtLogin.getText().trim().length() == 0) {
                            lblNotificacoes.setText(setHtmlFormat("É necessário digitar um login para acessar! Por favor, verifique e tente novamente."));
                            txtLogin.requestFocus();
                        } else if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                            lblNotificacoes.setText(setHtmlFormat("É necessário digitar uma senha para acessar! Por favor, verifique e tente novamente."));
                            txtSenha.requestFocus();
                        } else {
                            Connection conexao = MySQLConnector.conectar();
                            String strSqlLogin = "select * from `" + dbPadrao + "`.`" + tblPadrao + "`" + " where `email` = '" + txtLogin.getText() + "' and `senha` = '" + String.valueOf(txtSenha.getPassword()) + "';";
                            Statement stmSqlLogin = conexao.createStatement();
                            ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);
                            if (rstSqlLogin.next()) {
                                MenuFrame appMenuFrame = new MenuFrame();
                                appMenuFrame.setDefaultCloseOperation(ABORT);
                            } else {
                                lblNotificacoes.setText(setHtmlFormat("Não foi encontrado o login e/ou senha digitados! Por favor, verifique e tente novamente."));
                            }
                        }
                    } catch (Exception e) {
                        lblNotificacoes.setText(setHtmlFormat("Ops! Deu ruim no banco de dados, veja o erro: " + e));
                    }
                }
            }
        );

        btnCadastrar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    setVisible(false);
                    NovoCadastro appNovoCadastro = new NovoCadastro();
                    appNovoCadastro.setDefaultCloseOperation(ABORT);
                }
            }
        );

        setSize(400, 300);
        setVisible(true);
    }

    public static String setHtmlFormat(String strText) {
        return "<html><body>" + strText + "</body></html>";
    }

    public static LoginFrame appLoginFrame;

    public static void main(String[] args) {
        appLoginFrame = new LoginFrame();
        appLoginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
