package vista;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class CreatorInterface {
    JPanel bodyPanel;
    private JMenuBar menuBar1;
    private JMenu editMenu, seeMenu, fontMenu, formatoMenu, fileMenu, searchMenu, insertarMenu, tableMenu, formularioMenu, helpMenu;
    private JMenuItem acercaDeMenuItem;
    private JPanel menusPanel, creatorPanel;
    private JToolBar toolBar1, toolBar2, toolBar3;
    private JTextPane creatorTextPanel;
    private JComboBox comboBox1;
    private JComboBox fontComboBox;
    private JButton newFileButton;
    private JButton openFileButton;
    private JMenu configurationMenu;
    private JMenuItem toolbarVisibilityMenuItem;
    private JButton saveButton;
    private JButton printButton;
    private JMenuItem newFileMenuItem;
    private JMenuItem saveFileMenuItem;
    private JMenuItem openFileMenuItem;
    private JMenuItem printMenuItem;
    private JMenuItem createTableMenuItem;
    private JMenuItem editTableMenuItem;
    private JButton blackLetterButton;
    private JButton cursiveButton;
    private Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts( );
    private StyleSheet styleSheet = new StyleSheet();
    private HTMLEditorKit htmlEditorKit = new HTMLEditorKit();

    public CreatorInterface() {

        creatorTextPanel.setContentType("text/html");
        creatorTextPanel.setEditorKit(htmlEditorKit);

        Font font = creatorTextPanel.getFont();
        fontMenu.add(new JMenuItem("Fuente(default)"));
        for (Font item : fonts) {
            fontComboBox.addItem(item.getFontName());
            fontMenu.add(new JMenuItem(item.getFontName()));
        }
        acercaDeMenuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Proyecto hecho por Fbernabe.", "Acerca de...", JOptionPane.PLAIN_MESSAGE);
        });

        createTableMenuItem.addActionListener(e -> {
            TablesCreation.start();
        });
//        fontComboBox.addActionListener(e -> {
//            for (Font value : fonts) {
//                if (Objects.equals(fontComboBox.getSelectedItem(), value.getFontName())) {
//                    creatorTextPanel.setFont(value.deriveFont(12f));
//                } else if (fontComboBox.getSelectedIndex() == 0) {
//                    creatorTextPanel.getText();
//                }
//            }
//        });

        toolbarVisibilityMenuItem.addActionListener(e -> {

            if (toolBar3.isVisible()){
                toolBar1.setVisible(false);
                toolBar2.setVisible(false);
                toolBar3.setVisible(false);
                toolbarVisibilityMenuItem.setText("Mostrar barras de herramientas.");
            } else {
                toolBar1.setVisible(true);
                toolBar2.setVisible(true);
                toolBar3.setVisible(true);
                toolbarVisibilityMenuItem.setText("Ocultar barras de herramientas.");
            }
        });
        ActionListener saveListener = e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("fichero.html"));
            fileChooser.setDialogTitle("Selecciona un fichero:");
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                try {

                    Files.writeString(Path.of(path), creatorTextPanel.getText(), StandardCharsets.UTF_8);
                } catch (IOException ex) {

                    ex.printStackTrace();
                }
            }
        };
        ActionListener printListener = e -> {
            try {
                boolean done = creatorTextPanel.print();
                if (done) {
                    JOptionPane.showMessageDialog(null, "Se ha imprimido el archivo.", "Todo correcto", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido imprimir.", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        };
        ActionListener newFileListener = e -> {
            int value = JOptionPane.showConfirmDialog(null, "Perderá todo lo que no haya guardado, ¿quiere continuar?", "Archivo nuevo", JOptionPane.OK_OPTION);
            if (value == 0) {
                creatorTextPanel.setDocument(new HTMLDocument());
            }
        };
        ActionListener openFileListener = e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("fichero.txt"));
            fileChooser.setDialogTitle("Selecciona un fichero:");
//            FileFilter filter = new FileNameExtensionFilter("Ficheros de texto", "txt");
//            fileChooser.addChoosableFileFilter(filter);
            FileFilter filter = new FileNameExtensionFilter("Ficheros html", "html");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                try {

                    creatorTextPanel.setText(Files.readString(Path.of(path)));
                } catch (IOException ex) {

                    ex.printStackTrace();
                }
            }
        };
        newFileButton.addActionListener(newFileListener);
        newFileMenuItem.addActionListener(newFileListener);
        openFileButton.addActionListener(openFileListener);
        openFileMenuItem.addActionListener(openFileListener);
        printButton.addActionListener(printListener);
        printMenuItem.addActionListener(printListener);
        saveButton.addActionListener(saveListener);
        saveFileMenuItem.addActionListener(saveListener);

        blackLetterButton.addActionListener(e -> {
            String selectedText = creatorTextPanel.getSelectedText();
            String text2 = "<b>" + selectedText + "</b>";
            creatorTextPanel.setText(text2);
        });
        cursiveButton.addActionListener(e -> {
            String selectedText = creatorTextPanel.getSelectedText();
            String text2 = "<em>" + selectedText + "</em>";
            creatorTextPanel.setText(text2);
        });
        fontComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int familyInt = fontComboBox.getSelectedIndex();
                String family = fonts[familyInt].getFontName();
                String selectedText = creatorTextPanel.getSelectedText();
                String text2 = "<text style='font-family:"+family+">" + selectedText + "</text>";
                creatorTextPanel.setText(text2);
            }
        });
    }
}
