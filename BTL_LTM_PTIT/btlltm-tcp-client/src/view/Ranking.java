package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class Ranking extends JFrame {
    private JTable tblRanking;
    private DefaultTableModel tableModel;
    private JPanel Background;

    public Ranking() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bảng xếp hạng");
        setPreferredSize(new Dimension(600, 400));

        Background = new JPanel();
        Background.setBackground(new Color(66, 107, 85));
        Background.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bảng xếp hạng");
        titleLabel.setFont(new Font("Berlin Sans FB", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        tableModel = new DefaultTableModel(
            new Object [][] {},
            new String [] {"Rank", "ID", "Username", "Score"}
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        tblRanking = new JTable(tableModel);
        tblRanking.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        tblRanking.setRowHeight(30);
        tblRanking.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        tblRanking.getTableHeader().setBackground(new Color(245, 255, 250));
        tblRanking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRanking.setGridColor(new Color(66, 107, 85));
        tblRanking.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(tblRanking);
        scrollPane.getViewport().setBackground(new Color(245, 255, 250));

        Background.add(titleLabel, BorderLayout.NORTH);
        Background.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(Background);

        pack();
        setLocationRelativeTo(null);
    }

    public void setRankingData(Vector<Vector<Object>> data) {
        tableModel.setRowCount(0);
        int rank = 1;
        for (Vector<Object> row : data) {
            Vector<Object> newRow = new Vector<>(row);
            newRow.add(0, rank++);
            tableModel.addRow(newRow);
        }
    }
}
