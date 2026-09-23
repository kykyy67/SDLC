package by.aleksandr.length;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame implements ModelObserver {
    private final LengthModel model;
    private final LengthController controller;
    private final JLabel resultLabel;

    public MainFrame(LengthModel model, LengthController controller) {
        this.model = model;
        this.controller = controller;
        this.model.addObserver(this);

        setTitle("Утилита: Высота и длина");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Конвертер величин", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(33, 37, 41));
        titleLabel.setBorder(new EmptyBorder(25, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        resultLabel = new JLabel("Ожидание ввода...", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultLabel.setForeground(new Color(108, 117, 125));
        add(resultLabel, BorderLayout.CENTER);

        // Панель для кнопок с отступом между ними (15px)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 25, 10));

        RoundedButton inputButton = new RoundedButton("Ввести данные", 15);
        inputButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        inputButton.setBackground(new Color(13, 110, 253));
        inputButton.setForeground(Color.WHITE);
        inputButton.setPreferredSize(new Dimension(170, 45));

        // Новая кнопка очистки (нейтральный серый цвет)
        RoundedButton clearButton = new RoundedButton("Очистить", 15);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setBackground(new Color(108, 117, 125));
        clearButton.setForeground(Color.WHITE);
        clearButton.setPreferredSize(new Dimension(120, 45));

        inputButton.addActionListener(e -> {
            InputDialog dialog = new InputDialog(this, model, controller);
            dialog.setVisible(true);
        });

        clearButton.addActionListener(e -> controller.clear());

        buttonPanel.add(inputButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onModelChanged() {
        if (model.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, model.getErrorMessage(), "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            resultLabel.setText("<html><font color='#DC3545'>Ошибка ввода данных</font></html>");
        } else if (model.getResult() != null) {
            String resultText = String.format(
                    "<html><center><font color='#6C757D' size='4'>Результат:</font><br><font color='#198754' size='+3'><b>%.4f</b></font> <font color='#212529' size='5'>%s</font></center></html>",
                    model.getResult(), model.getLastToUnit().toString()
            );
            resultLabel.setText(resultText);
        } else {
            // Состояние после нажатия кнопки "Очистить"
            resultLabel.setText("<html><font color='#6C757D'>Ожидание ввода...</font></html>");
        }
    }
}