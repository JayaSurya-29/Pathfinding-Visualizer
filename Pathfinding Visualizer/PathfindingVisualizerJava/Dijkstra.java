import java.util.*;

class Dijkstra {

    static class Node {
        Cell cell;
        int dist;

        Node(Cell c, int d) {
            cell = c;
            dist = d;
        }
    }

    static void run(Cell[][] grid, Cell start, Cell end) {
        run(grid, start, end, 50);
    }

    static void run(Cell[][] grid, Cell start, Cell end, int delay) {

        if (start == null || end == null) {
            System.out.println("Start or End not set!");
            return;
        }

        Map<Cell, Integer> dist = new HashMap<>();
        Map<Cell, Cell> parent = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

        for (Cell[] row : grid) {
            for (Cell c : row) {
                dist.put(c, Integer.MAX_VALUE);
            }
        }

        dist.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            Cell current = node.cell;

            if (visited.contains(current))
                continue;

            visited.add(current);

            // do not recolor start or end
            if (!current.start && !current.end)
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

                    pq.add(new Node(neighbor, newDist));
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
