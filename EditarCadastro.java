import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EditarCadastro extends JFrame {
    private final JLabel idJLabel = new JLabel("id:");
    private final JTextField idJTextField = new JTextField();

    private final JLabel nomeJLabel = new JLabel("Digite um nome:");
    private final JTextField nomeJTextField = new JTextField();
    private String strNome;

    private final JLabel emailJLabel = new JLabel("Digite um email:");
    private final JTextField emailJTextField = new JTextField();
    private String strEmail;

    private final JLabel senhaJLabel = new JLabel("Digite uma senha:");
    private final JPasswordField senhaJTextField = new JPasswordField();

    private final int columnsJTextField = 30;

    private final JButton editarJButton = new JButton("✓");

    private final JButton primeiroJButton = new JButton("<<");
    private final JButton anteriorJButton = new JButton("<");
    private final JButton proximoJButton = new JButton(">");
    private final JButton ultimoJButton = new JButton(">>");

    private final JLabel notificacaoJLabel = new JLabel("Notificações...");

    public EditarCadastro() {
        super("Editar Cadastro");
        // setLayout(new GridLayout(6, 4));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel linha1 = new JPanel();
        linha1.setLayout(new GridLayout(1, 3));

        JPanel linha2 = new JPanel();
        linha1.setLayout(new GridLayout(1, 2));

        JPanel linha3 = new JPanel();
        linha1.setLayout(new GridLayout(1, 2));

        JPanel linha4 = new JPanel();
        linha1.setLayout(new GridLayout(1, 2));

        JPanel linha5 = new JPanel();
        linha1.setLayout(new GridLayout(1, 4));

        JPanel linha6 = new JPanel();
        linha1.setLayout(new GridLayout(1, 1));

        editarJButton.setToolTipText("Atualizar");
        editarJButton.setEnabled(false);
        idJTextField.setEnabled(false);
        primeiroJButton.setEnabled(false);
        anteriorJButton.setEnabled(false);

        editarJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    boolean atualizou = false;
                    try {
                        atualizou = NavegadorDeRegistro.atualizarRegistro("db_teste", "tbl_teste", idJTextField.getText(), nomeJTextField.getText(), emailJTextField.getText(), senhaJTextField.getPassword());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        System.out.println();
                    }
                    if (atualizou) {
                        notificacaoJLabel.setText("Cadastro atualizado com sucesso!");
                    } else {
                        notificacaoJLabel.setText("Não foi possível atualizar o cadastro.");
                    }
                }
            }
        );

        primeiroJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String[] resultado;
                    try {
                        resultado = NavegadorDeRegistro.primeiroRegistro("db_teste", "tbl_teste");
                        idJTextField.setText(resultado[0]);
                        nomeJTextField.setText(resultado[1]);
                        strNome = nomeJTextField.getText();
                        emailJTextField.setText(resultado[2]);
                        strEmail = emailJTextField.getText();
                        editarJButton.setEnabled(false);
                        primeiroJButton.setEnabled(false);
                        anteriorJButton.setEnabled(false);
                        proximoJButton.setEnabled(true);
                        ultimoJButton.setEnabled(true);
                        notificacaoJLabel.setText("Primeiro registro posicionado com sucesso.");
                    } catch(Exception e) {
                        System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o primeiro. Veja o erro: " + e);
                        return;
                    }
                }
            }
        );

        anteriorJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (idJTextField.getText().length() > 0) {
                        String[] resultado;
                        try {
                            resultado = NavegadorDeRegistro.registroAnterior("db_teste", "tbl_teste", idJTextField.getText());
                            if (resultado != null) {
                                idJTextField.setText(resultado[0]);
                                nomeJTextField.setText(resultado[1]);
                                strNome = nomeJTextField.getText();
                                emailJTextField.setText(resultado[2]);
                                strEmail = emailJTextField.getText();
                                editarJButton.setEnabled(false);
                                proximoJButton.setEnabled(true);
                                ultimoJButton.setEnabled(true);
                                notificacaoJLabel.setText("Registro retrocedido com sucesso.");
                            } else {
                                primeiroJButton.setEnabled(false);
                                anteriorJButton.setEnabled(false);
                                notificacaoJLabel.setText("Já está no primeiro registro, por isso não é possível retroceder ao registro anterior.");
                            }
                        } catch(Exception e) {
                            System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o primeiro. Veja o erro: " + e);
                            return;
                        }
                    } else {
                        notificacaoJLabel.setText("Não foi possível retroceder o registro, pois não há um id válido atual.");
                    }
                }
            }
        );

        proximoJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (idJTextField.getText().length() > 0) {
                        String[] resultado;
                        try {
                            resultado = NavegadorDeRegistro.proximoRegistro("db_teste", "tbl_teste", idJTextField.getText());
                            if (resultado != null) {
                                idJTextField.setText(resultado[0]);
                                nomeJTextField.setText(resultado[1]);
                                strNome = nomeJTextField.getText();
                                emailJTextField.setText(resultado[2]);
                                strEmail = emailJTextField.getText();
                                editarJButton.setEnabled(false);
                                primeiroJButton.setEnabled(true);
                                anteriorJButton.setEnabled(true);
                                notificacaoJLabel.setText("Registro avançado com sucesso.");
                            } else {
                                notificacaoJLabel.setText("Já está no último registro, por isso não é possível avançar o registro.");
                                proximoJButton.setEnabled(false);
                                ultimoJButton.setEnabled(false);
                            }
                        } catch(Exception e) {
                            System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o próximo. Veja o erro: " + e);
                            return;
                        }
                    } else {
                        notificacaoJLabel.setText("Não foi possível avançar o registro, pois não há um id válido atual.");
                    }
                }
            }
        );

        ultimoJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String[] resultado = NavegadorDeRegistro.ultimoRegistro("db_teste", "tbl_teste");
                        idJTextField.setText(resultado[0]);
                        nomeJTextField.setText(resultado[1]);
                        strNome = nomeJTextField.getText();
                        emailJTextField.setText(resultado[2]);
                        strEmail = emailJTextField.getText();
                        editarJButton.setEnabled(false);
                        proximoJButton.setEnabled(false);
                        ultimoJButton.setEnabled(false);
                        primeiroJButton.setEnabled(true);
                        anteriorJButton.setEnabled(true);
                        notificacaoJLabel.setText("Último registro posicionado com sucesso.");
                    } catch(Exception e) {
                        System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o último. Veja o erro: " + e);
                        return;
                    }
                }
            }
        );

        nomeJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCampos();
            }
        });

        emailJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCampos();
            }
        });

        senhaJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCampos();
            }
        });

        linha1.add(idJLabel);
        linha1.add(idJTextField);
        linha1.add(editarJButton);
        container.add(linha1);

        linha2.add(nomeJLabel);
        nomeJTextField.setColumns(columnsJTextField);
        linha2.add(nomeJTextField);
        container.add(linha2);

        linha3.add(emailJLabel);
        emailJTextField.setColumns(columnsJTextField);
        linha3.add(emailJTextField);
        container.add(linha3);

        linha4.add(senhaJLabel);
        senhaJTextField.setColumns(columnsJTextField);
        linha4.add(senhaJTextField);
        container.add(linha4);

        linha5.add(primeiroJButton);
        linha5.add(anteriorJButton);
        linha5.add(proximoJButton);
        linha5.add(ultimoJButton);
        container.add(linha5);

        linha6.add(notificacaoJLabel);
        container.add(linha6);

        add(container);

        setSize(500, 300);
        setVisible(true);
        iniciarCampos();
    }

    public void verificarCampos() {
        if (!strNome.equals(nomeJTextField.getText())) {
            editarJButton.setEnabled(true);
        } else if (!strEmail.equals(emailJTextField.getText())) {
            editarJButton.setEnabled(true);
        } else if (senhaJTextField.getText().length() > 0) {
            editarJButton.setEnabled(true);
        } else {
            editarJButton.setEnabled(false);
        }
    }

    public void iniciarCampos() {
        try {
            String[] resultado = NavegadorDeRegistro.primeiroRegistro("db_teste", "tbl_teste");
            notificacaoJLabel.setText("Primeio registro posicionado com sucesso");
            if (resultado != null) {
                idJTextField.setText(resultado[0]);
                nomeJTextField.setText(resultado[1]);
                strNome = nomeJTextField.getText();
                emailJTextField.setText(resultado[2]);
                strEmail = emailJTextField.getText();
                editarJButton.setEnabled(false);
            } else {
                notificacaoJLabel.setText("Já está no primeiro registro, por isso não é possível retroceder ao registro anterior.");
            }
        } catch(Exception e) {
            System.out.println("Ops! Ocorreu algum erro ao posicionar o registro para o primeiro. Veja o erro: " + e);
        }
    }

    public static void main(String[] args) {
        EditarCadastro application = new EditarCadastro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
