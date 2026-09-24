package by.aleksandr.length;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LengthModel model = new LengthModel();
        LengthController controller = new LengthController(model);
        MainFrame mainFrame = new MainFrame(model, controller);

        mainFrame.setVisible(true);
    }
}