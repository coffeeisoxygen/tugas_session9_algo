package samplellist;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LinkedListGUI extends JFrame {
    private LinkedList linkedList;
    private JTextField nimField;
    private JTextField namaField;
    private JTextField nilaiField;
    private JTextField searchField;
    private JTextArea detailArea;
    private JPanel visualizationPanel;

    public LinkedListGUI() {
        linkedList = new LinkedList();
        setTitle("Linked List GUI");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel: Split into Left (Data Display) and Right (Input Data)
        JSplitPane topSplitPane = createTopSplitPane();
        add(topSplitPane, BorderLayout.CENTER);

        // Bottom Panel: Visualization
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JSplitPane createTopSplitPane() {
        // Left Panel: Display Data
        JPanel leftPanel = createLeftPanel();

        // Right Panel: Input Data
        JPanel rightPanel = createRightPanel();

        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400); // Initial divider location
        splitPane.setResizeWeight(0.5); // Equal resizing
        return splitPane;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());

        // Detailed Data Area
        detailArea = new JTextArea();
        detailArea.setEditable(false);
        JScrollPane detailScrollPane = new JScrollPane(detailArea);
        leftPanel.add(detailScrollPane, BorderLayout.CENTER);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("NIM:"));
        nimField = new JTextField();
        inputPanel.add(nimField);
        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);
        inputPanel.add(new JLabel("Nilai:"));
        nilaiField = new JTextField();
        inputPanel.add(nilaiField);

        // Control Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = new JButton("Add Data");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);

        JButton displayButton = new JButton("Display Data");
        displayButton.addActionListener(new DisplayButtonListener());
        buttonPanel.add(displayButton);

        JPanel searchPanel = createSearchPanel();

        // Adding panels to the right panel
        rightPanel.add(inputPanel);
        rightPanel.add(buttonPanel);
        rightPanel.add(searchPanel);

        return rightPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new GridLayout(2, 2));
        searchPanel.add(new JLabel("Search NIM:"));
        searchField = new JTextField();
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        searchPanel.add(searchButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        searchPanel.add(deleteButton);

        return searchPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Visualization Panel
        visualizationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLinkedList(g);
            }
        };
        visualizationPanel.setPreferredSize(new Dimension(800, 300));
        bottomPanel.add(visualizationPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private void updateDetailArea() {
        StringBuilder sb = new StringBuilder();
        Simpul penunjuk = linkedList.getPertama();
        while (penunjuk != null) {
            sb.append(penunjuk.toString()).append("\n");
            penunjuk = penunjuk.berikutnya;
        }
        detailArea.setText(sb.toString());
    }

    private void drawLinkedList(Graphics g) {
        Simpul penunjuk = linkedList.getPertama();
        int x = 20;
        int y = 20;
        while (penunjuk != null) {
            g.drawRect(x, y, 100, 50);
            g.drawString("NIM: " + penunjuk.getNim(), x + 10, y + 20);
            g.drawString("Nama: " + penunjuk.getNama(), x + 10, y + 35);
            if (penunjuk.berikutnya != null) {
                g.drawLine(x + 100, y + 25, x + 150, y + 25);
                g.drawLine(x + 145, y + 20, x + 150, y + 25);
                g.drawLine(x + 145, y + 30, x + 150, y + 25);
            }
            penunjuk = penunjuk.berikutnya;
            x += 150;
        }

        // Draw head and tail pointers
        if (linkedList.getPertama() != null) {
            g.drawString("Head", 10, 15);
            g.drawLine(10, 20, 20, 20);
            g.drawLine(15, 15, 20, 20);
            g.drawLine(15, 25, 20, 20);
        }
        if (penunjuk == null && x > 20) {
            g.drawString("Tail", x - 150 + 110, y + 25);
            g.drawLine(x - 150 + 100, y + 25, x - 150 + 110, y + 25);
            g.drawLine(x - 150 + 105, y + 20, x - 150 + 110, y + 25);
            g.drawLine(x - 150 + 105, y + 30, x - 150 + 110, y + 25);
        }
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nim = nimField.getText();
            String nama = namaField.getText();
            String nilaiStr = nilaiField.getText();

            if (nim.isEmpty() || nama.isEmpty() || nilaiStr.isEmpty()) {
                JOptionPane.showMessageDialog(LinkedListGUI.this, "All fields must be filled", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nilai;
            try {
                nilai = Integer.parseInt(nilaiStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(LinkedListGUI.this, "Nilai must be a number", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            linkedList.isiData(nim, nama, nilai);
            updateDetailArea(); // Update detail area after adding data
            visualizationPanel.repaint(); // Repaint visualization panel
            JOptionPane.showMessageDialog(LinkedListGUI.this, "Data added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class DisplayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateDetailArea(); // Update detail area to show all data
            visualizationPanel.repaint(); // Repaint visualization panel
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nim = searchField.getText();

            Simpul found = linkedList.findDataByNim(nim);
            if (found != null) {
                JOptionPane.showMessageDialog(LinkedListGUI.this, "Data found: " + found.toString(), "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(LinkedListGUI.this, "Data not found for NIM: " + nim, "Search Result",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nim = searchField.getText();

            boolean deleted = linkedList.hapusDataByNim(nim);
            if (deleted) {
                updateDetailArea(); // Update detail area after deletion
                visualizationPanel.repaint(); // Repaint visualization panel
                JOptionPane.showMessageDialog(LinkedListGUI.this, "Data deleted successfully", "Delete Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(LinkedListGUI.this, "Data not found for NIM: " + nim, "Delete Result",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LinkedListGUI().setVisible(true));
    }
}