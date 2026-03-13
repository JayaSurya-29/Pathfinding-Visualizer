import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {
    static final int SIZE = 20;
    Cell[][] grid = new Cell[SIZE][SIZE];
    JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));

    @SuppressWarnings("Convert2Lambda")
    public Main() {
        setTitle("Pathfinding Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Build grid
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Cell cell = new Cell(r, c);
                grid[r][c] = cell;
                gridPanel.add(cell.button);
            }
        }

        // Controls
        JComboBox<String> algoBox = new JComboBox<>(new String[]{"BFS", "DFS", "Dijkstra"});
        algoBox.setSelectedItem("BFS");

        JButton runBtn = new JButton("Run");
        runBtn.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("ConvertToStringSwitch")
            public void actionPerformed(ActionEvent e) {
                String algo = (String) algoBox.getSelectedItem();
                if (Grid.startCell == null || Grid.endCell == null) return;
                new Thread(() -> {
                    if (algo.equals("BFS")) BFS.run(grid, Grid.startCell, Grid.endCell);
                    else if (algo.equals("DFS")) DFS.run(grid, Grid.startCell, Grid.endCell);
                    else if (algo.equals("Dijkstra")) Dijkstra.run(grid, Grid.startCell, Grid.endCell);
                }).start();
            }
        });

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrid();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(algoBox);
        controlPanel.add(runBtn);
        controlPanel.add(resetBtn);

        add(controlPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    void resetGrid() {
        Grid.startSet = false;
        Grid.endSet = false;
        Grid.startCell = null;
        Grid.endCell = null;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c].reset();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}