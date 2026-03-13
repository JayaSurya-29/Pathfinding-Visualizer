import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

class Cell {
    int row, col;
    boolean wall = false, start = false, end = false;
    JButton button = new JButton();

    Cell(int r, int c) {
        row = r;
        col = c;
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new java.awt.Dimension(25, 25));
        button.addActionListener(e -> handleClick());
    }

    void handleClick() {
        if (!Grid.startSet) {
            setStart();
            Grid.startSet = true;
            Grid.startCell = this;
        } else if (!Grid.endSet) {
            setEnd();
            Grid.endSet = true;
            Grid.endCell = this;
        } else {
            setWall();
        }
    }

    void setWall() {
        wall = true;
        SwingUtilities.invokeLater(() -> button.setBackground(Color.BLACK));
    }

    void setStart() {
        start = true;
        SwingUtilities.invokeLater(() -> button.setBackground(Color.GREEN));
    }

    void setEnd() {
        end = true;
        SwingUtilities.invokeLater(() -> button.setBackground(Color.RED));
    }

    void setVisited() {
        if (!start && !end) SwingUtilities.invokeLater(() -> button.setBackground(Color.CYAN));
    }

    void setPath() {
        if (!start && !end) SwingUtilities.invokeLater(() -> button.setBackground(Color.YELLOW));
    }

    void reset() {
        wall = false;
        start = false;
        end = false;
        SwingUtilities.invokeLater(() -> button.setBackground(Color.WHITE));
    }
}