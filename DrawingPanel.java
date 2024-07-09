import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingPanel extends JPanel {

    private Color color; // Cor inicial
    private int panelNumber; // Número do painel (1, 2 ou 3)

    public DrawingPanel(int panelNumber) {
        this.panelNumber = panelNumber;
        this.color = Color.BLACK; // Cor inicial: preto
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Configura a cor do desenho
        g.setColor(color);

        // Desenha algo simples para ilustrar
        int width = getWidth();
        int height = getHeight();

        switch (panelNumber) {
            case 1:
                g.fillRect(10, 10, width - 20, height - 20);
                break;
            case 2:
                g.fillOval(10, 10, width - 20, height - 20);
                break;
            case 3:
                g.drawLine(10, 10, width - 10, height - 10);
                break;
        }
    }

    public void setColor(Color color) {
        this.color = color;
        repaint(); // Redesenha o painel com a nova cor
    }
}
