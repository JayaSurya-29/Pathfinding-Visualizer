import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    static final int SIZE = 20;

    Cell[][] grid = new Cell[SIZE][SIZE];
    JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));

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

        // Algorithm dropdown
        JComboBox<String> algoBox =
                new JComboBox<>(new String[]{"BFS", "DFS", "Dijkstra"});

        algoBox.setSelectedItem("BFS");

        // Run button
        JButton runBtn = new JButton("Run");

        runBtn.addActionListener(e -> {

            if (Grid.startCell == null || Grid.endCell == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select Start and End nodes first.");
                return;
            }

            String algo = (String) algoBox.getSelectedItem();

            new Thread(() -> {

                if ("BFS".equals(algo))
                    BFS.run(grid, Grid.startCell, Grid.endCell);

                else if ("DFS".equals(algo))
                    DFS.run(grid, Grid.startCell, Grid.endCell);

                else if ("Dijkstra".equals(algo))
                    Dijkstra.run(grid, Grid.startCell, Grid.endCell);

            }).start();

        });

        // Reset button
        JButton resetBtn = new JButton("Reset");

        resetBtn.addActionListener(e -> resetGrid());

        JPanel controlPanel = new JPanel();
        controlPanel.add(algoBox);
        controlPanel.add(runBtn);
        controlPanel.add(resetBtn);

        add(controlPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);   // center window
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
        SwingUtilities.invokeLater(Main::new);
    }
}
