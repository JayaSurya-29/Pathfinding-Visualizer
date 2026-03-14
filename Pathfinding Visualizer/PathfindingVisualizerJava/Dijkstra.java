import java.util.*;

class Dijkstra {

    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    static void run(Cell[][] grid, Cell start, Cell end, int delay) {

        Map<Cell, Integer> dist = new HashMap<>();
        Map<Cell, Cell> parent = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        PriorityQueue<Cell> pq = new PriorityQueue<>(
                Comparator.comparingInt(dist::get)
        );

        // Initialize distances
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                dist.put(cell, Integer.MAX_VALUE);
            }
        }

        dist.put(start, 0);
        pq.offer(start);

        while (!pq.isEmpty()) {

            Cell current = pq.poll();

            // Skip if already processed
            if (visited.contains(current))
                continue;

            visited.add(current);

            // Mark visited visually
            current.setVisited();

            if (current == end) {
                BFS.reconstructPath(parent, end, start);
                return;
            }

            for (Cell neighbor : BFS.getNeighbors(grid, current)) {

                if (neighbor.wall || visited.contains(neighbor))
                    continue;

                int newDist = dist.get(current) + 1;

                if (newDist < dist.get(neighbor)) {

                    dist.put(neighbor, newDist);
                    parent.put(neighbor, current);

                    pq.offer(neighbor);
                }
            }

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
