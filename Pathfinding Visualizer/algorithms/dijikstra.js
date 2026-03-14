function runDijkstra(){

  let dist = {};
  let parent = {};
  let pq = [];
  let visited = new Set();

  function key(r,c){
    return r + "-" + c;
  }

  // initialize distances
  for(let r=0;r<rows;r++){
    for(let c=0;c<cols;c++){
      dist[key(r,c)] = Infinity;
    }
  }

  dist[key(start[0],start[1])] = 0;

  pq.push({
    r:start[0],
    c:start[1],
    cost:0
  });

  function step(){

    if(pq.length === 0) return;

    pq.sort((a,b)=>a.cost-b.cost);

    let current = pq.shift();
    let r = current.r;
    let c = current.c;

    if(visited.has(key(r,c))){
      setTimeout(step,10);
      return;
    }

    visited.add(key(r,c));

    // mark visited
    if(!(r===start[0] && c===start[1]) &&
       !(r===end[0] && c===end[1])){
      grid[r][c].classList.add("visited");
    }

    if(r===end[0] && c===end[1]){
      reconstructPath(parent);
      return;
    }

    let dirs = [[1,0],[-1,0],[0,1],[0,-1]];

    for(let [dr,dc] of dirs){

      let nr = r + dr;
      let nc = c + dc;

      if(nr>=0 && nr<rows && nc>=0 && nc<cols &&
         !grid[nr][nc].classList.contains("wall")){

        let newCost = dist[key(r,c)] + 1;

        if(newCost < dist[key(nr,nc)]){

          dist[key(nr,nc)] = newCost;

          parent[key(nr,nc)] = [r,c];

          pq.push({
            r:nr,
            c:nc,
            cost:newCost
          });

        }
      }
    }

    setTimeout(step,20);
  }

  step();
}
