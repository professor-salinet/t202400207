import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

    private DrawingPanel panel1, panel2, panel3;

    public MainPanel() {
        setTitle("Exemplo de JPanel com PaintComponent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria os painéis de desenho
        panel1 = new DrawingPanel(1);
        panel2 = new DrawingPanel(2);
        panel3 = new DrawingPanel(3);

        // Adiciona os painéis ao JFrame
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.EAST);

        // Cria o botão para alterar as cores
        JButton button = new JButton("Alterar Cores");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gera uma cor aleatória
                Color newColor1 = getRandomColor();
                Color newColor2 = getRandomColor();
                Color newColor3 = getRandomColor();

                // Define as novas cores nos painéis
                panel1.setColor(newColor1);
                panel2.setColor(newColor2);
                panel3.setColor(newColor3);
            }
        });

        // Adiciona o botão ao JFrame
        add(button, BorderLayout.SOUTH);

        // Configura o tamanho do JFrame e exibe-o
        setSize(600, 400);
        setVisible(true);
    }

    // Método para gerar uma cor aleatória
    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPanel();
            }
        });
    }
}
