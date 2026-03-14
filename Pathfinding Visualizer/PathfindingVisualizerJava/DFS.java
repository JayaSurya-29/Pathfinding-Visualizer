import java.util.*;

class DFS {

    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    static void run(Cell[][] grid, Cell start, Cell end, int delay) {

        Stack<Cell> stack = new Stack<>();
        Map<Cell, Cell> parent = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {

            Cell current = stack.pop();

            if (current == end) {
                BFS.reconstructPath(parent, end, start);
                return;
            }

            for (Cell neighbor : BFS.getNeighbors(grid, current)) {

                if (!visited.contains(neighbor) && !neighbor.wall) {

                    stack.push(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);

                    // do not recolor start/end
                    if (!neighbor.start && !neighbor.end)
                        neighbor.setVisited();

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
