import java.util.*;

class Dijkstra {

    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    static void run(Cell[][] grid, Cell start, Cell end, int delay) {
        Map<Cell, Integer> dist = new HashMap<>();
        Map<Cell, Cell> parent = new HashMap<>();
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                dist.put(cell, Integer.MAX_VALUE);
            }
        }
        dist.put(start, 0);
        pq.offer(start);

        while (!pq.isEmpty()) {
            Cell current = pq.poll();

            if (current == end) {
                BFS.reconstructPath(parent, end, start);
                return;
            }

            for (Cell neighbor : BFS.getNeighbors(grid, current)) {
                if (!neighbor.wall) {
                    int newDist = dist.get(current) + 1;
                    if (newDist < dist.get(neighbor)) {
                        dist.put(neighbor, newDist);
                        parent.put(neighbor, current);
                        pq.offer(neighbor);
                        neighbor.setVisited();
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ignored) {}
                    }
                }
            }
        }
    }
}
```
