package vista;

import javax.swing.*;
import java.awt.event.*;

public class TablesCreation extends JDialog {
    private JPanel contentPane;
    private JButton acceptButton;
    private JButton cancelButton;
    private JPanel creatorPanel;
    private JPanel acceptCancelPanel;
    private JPanel buttonsPanel;
    private JTextField rowsTextField;
    private JTextField columnTextField;
    private JTextField widthTextField;
    private JLabel columnsLabel;
    private JLabel widthLabel;
    private JLabel rowsLabel;

    public TablesCreation() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(acceptButton);

        acceptButton.addActionListener(e -> onOK());

        cancelButton.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int row = Integer.parseInt(rowsTextField.getText());
        int column = Integer.parseInt(columnTextField.getText());
        int width = Integer.parseInt(widthTextField.getText());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        TablesCreation dialog = new TablesCreation();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        System.exit(0);
    }
}
