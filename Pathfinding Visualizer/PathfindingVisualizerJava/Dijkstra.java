import java.util.*;

class Dijkstra {
    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    @SuppressWarnings("SleepWhileInLoop")
    static void run(Cell[][] grid, Cell start, Cell end, int delay) {
        Map<Cell, Integer> dist = new HashMap<>();
        Map<Cell, Cell> parent = new HashMap<>();
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> Integer.compare(dist.get(a), dist.get(b)));

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                dist.put(cell, Integer.MAX_VALUE);
            }
        }
        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            if (current == end) {
                BFS.reconstructPath(parent, end, start);
                return;
            }
            for (Cell neighbor : BFS.getNeighbors(grid, current)) {
                if (!neighbor.wall) {
                    int newDist = dist.get(current) + 1; // uniform weight
                    if (newDist < dist.get(neighbor)) {
                        dist.put(neighbor, newDist);
                        parent.put(neighbor, current);
                        pq.add(neighbor);
                        neighbor.setVisited();
                        try { Thread.sleep(delay); } catch (InterruptedException e) {}
                    }
                }
            }
        }
    }
}