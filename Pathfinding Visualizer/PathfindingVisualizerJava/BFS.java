import java.util.*;

class BFS {
    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    @SuppressWarnings("SleepWhileInLoop")
    static void run(Cell[][] grid, Cell start, Cell end, int delay) {
        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> parent = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current == end) {
                reconstructPath(parent, end, start);
                return;
            }
            for (Cell neighbor : getNeighbors(grid, current)) {
                if (!visited.contains(neighbor) && !neighbor.wall) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    neighbor.setVisited();
                    try { Thread.sleep(delay); } catch (InterruptedException e) {}
                }
            }
        }
    }

    static List<Cell> getNeighbors(Cell[][] grid, Cell cell) {
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};
        List<Cell> neighbors = new ArrayList<>();
        for (int i=0; i<4; i++) {
            int nr = cell.row+dr[i], nc = cell.col+dc[i];
            if (nr>=0 && nr<grid.length && nc>=0 && nc<grid[0].length) {
                neighbors.add(grid[nr][nc]);
            }
        }
        return neighbors;
    }

    static void reconstructPath(Map<Cell,Cell> parent, Cell end, Cell start) {

    Cell cur = parent.get(end); // start from the cell before end

    while (cur != null && cur != start) {
        cur.setPath();
        cur = parent.get(cur);

        try { Thread.sleep(30); } catch (InterruptedException e) {}
    }
}
    }
}
