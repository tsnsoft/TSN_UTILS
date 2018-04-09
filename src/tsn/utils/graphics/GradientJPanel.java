package tsn.utils.graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Градиентная панель
 *
 * @author Талипов С.Н.
 */
public class GradientJPanel extends JPanel {

    private Color color1 = Color.white, color2 = Color.white;

    /**
     * Градиентная панель, например: "jPanel1 = new
     * tsn.utils.graphics.GradientJPanel(java.awt.Color.BLUE,
     * java.awt.Color.CYAN);"
     *
     * @param color1 начальный цвет
     * @param color2 конечный цвет
     */
    public GradientJPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, w, h, color2, true);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, w, h);
    }
}
