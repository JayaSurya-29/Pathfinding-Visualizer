function runDijkstra() {
  let dist = {};
  let parent = {};
  let pq = [];

  for (let r=0; r<rows; r++) {
    for (let c=0; c<cols; c++) {
      dist[[r,c]] = Infinity;
    }
  }
  dist[start] = 0;
  pq.push({pos:start, cost:0});

  while (pq.length > 0) {
    pq.sort((a,b)=>a.cost-b.cost);
    let {pos:[r,c], cost} = pq.shift();
    if (r===end[0] && c===end[1]) {
      reconstructPath(parent);
      return;
    }
    for (let [dr,dc] of [[1,0],[-1,0],[0,1],[0,-1]]) {
      let nr=r+dr, nc=c+dc;
      if (nr>=0 && nr<rows && nc>=0 && nc<cols &&
          !grid[nr][nc].classList.contains("wall")) {
        let newCost = cost+1;
        if (newCost < dist[[nr,nc]]) {
          dist[[nr,nc]] = newCost;
          parent[[nr,nc]] = [r,c];
          pq.push({pos:[nr,nc], cost:newCost});
          grid[nr][nc].classList.add("visited");
        }
      }
    }
  }
}