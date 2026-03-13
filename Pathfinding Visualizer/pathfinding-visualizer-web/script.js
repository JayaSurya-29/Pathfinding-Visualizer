const rows = 20, cols = 20;
let grid = [];
let start = null, end = null;

function initializeGrid() {
  const gridDiv = document.getElementById("grid");
  gridDiv.innerHTML = "";
  grid = [];
  for (let r=0; r<rows; r++) {
    let row = [];
    for (let c=0; c<cols; c++) {
      const cell = document.createElement("div");
      cell.className = "cell";
      cell.dataset.row = r;
      cell.dataset.col = c;
      cell.onclick = () => handleCellClick(r,c);
      gridDiv.appendChild(cell);
      row.push(cell);
    }
    grid.push(row);
  }
}

function handleCellClick(r,c) {
  const cell = grid[r][c];
  if (!start) {
    start = [r,c];
    cell.classList.add("start");
  } else if (!end) {
    end = [r,c];
    cell.classList.add("end");
  } else {
    cell.classList.toggle("wall");
  }
}

function runSelected() {
  const algo = document.getElementById("algorithm").value;
  if (algo === "BFS") runBFS();
  else if (algo === "DFS") runDFS();
  else if (algo === "Dijkstra") runDijkstra();
}

function resetGrid() {
  start = null;
  end = null;
  initializeGrid();
}

function reconstructPath(parent) {
  let cur = end;
  while (cur && cur.toString() !== start.toString()) {
    grid[cur[0]][cur[1]].classList.add("path");
    cur = parent[cur];
  }
}

initializeGrid();