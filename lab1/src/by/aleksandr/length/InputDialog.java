package by.aleksandr.length;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputDialog extends JDialog {
    private JTextField inputField;
    private JComboBox<Unit> fromBox;
    private JComboBox<Unit> toBox;
    private final LengthModel model;
    private final LengthController controller;

    public InputDialog(JFrame parent, LengthModel model, LengthController controller) {
        super(parent, "Ввод данных", true);
        this.model = model;
        this.controller = controller;
        setupUI();
    }

    private void setupUI() {
        // Увеличиваем ширину до 500, чтобы влез длинный текст
        setSize(500, 250);
        setLocationRelativeTo(getParent());
        getContentPane().setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 20));
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(Color.WHITE);

        Font uiFont = new Font("Segoe UI", Font.PLAIN, 15);

        JLabel lenLabel = new JLabel("Значение длины:");
        lenLabel.setFont(uiFont);
        mainPanel.add(lenLabel);

        inputField = new JTextField(model.getLastInput());
        inputField.setFont(uiFont);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        mainPanel.add(inputField);

        JLabel fromLabel = new JLabel("Исходная единица:");
        fromLabel.setFont(uiFont);
        mainPanel.add(fromLabel);

        fromBox = new JComboBox<>(Unit.values());
        fromBox.setSelectedItem(model.getLastFromUnit());
        fromBox.setFont(uiFont);
        fromBox.setBackground(Color.WHITE);
        mainPanel.add(fromBox);

        JLabel toLabel = new JLabel("В какую перевести:");
        toLabel.setFont(uiFont);
        mainPanel.add(toLabel);

        toBox = new JComboBox<>(Unit.values());
        toBox.setSelectedItem(model.getLastToUnit());
        toBox.setFont(uiFont);
        toBox.setBackground(Color.WHITE);
        mainPanel.add(toBox);

        // Используем новую кнопку со скруглением (радиус 15)
        RoundedButton calcButton = new RoundedButton("Рассчитать", 15);
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        calcButton.setBackground(new Color(25, 135, 84));
        calcButton.setForeground(Color.WHITE);

        calcButton.addActionListener(e -> {
            controller.processConversion(
                    inputField.getText(),
                    (Unit) fromBox.getSelectedItem(),
                    (Unit) toBox.getSelectedItem()
            );
            dispose();
        });

        mainPanel.add(new JLabel());
        mainPanel.add(calcButton);

        add(mainPanel);
    }
}