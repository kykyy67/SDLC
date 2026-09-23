package by.aleksandr.length;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private final int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false); // Отключаем стандартную заливку
        setFocusPainted(false);      // Убираем рамку фокуса
        setBorderPainted(false);     // Убираем стандартную рамку
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Включаем сглаживание для красивых краев
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Изменяем цвет при наведении и нажатии
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        // Рисуем закругленный прямоугольник
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();

        super.paintComponent(g); // Рисуем текст поверх
    }
}