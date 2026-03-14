function runDijkstra() {

  let dist = {};
  let parent = {};
  let pq = [];
  let visited = new Set();

  // convert coordinates to unique key
  function key(r,c){
    return r + "," + c;
  }

  // initialize distances
  for(let r=0; r<rows; r++){
    for(let c=0; c<cols; c++){
      dist[key(r,c)] = Infinity;
    }
  }

  dist[key(start[0],start[1])] = 0;

  pq.push({
    pos:start,
    cost:0
  });

  function step(){

    if(pq.length === 0) return;

    // priority queue simulation
    pq.sort((a,b)=>a.cost-b.cost);

    let {pos:[r,c], cost} = pq.shift();

    if(visited.has(key(r,c))){
      setTimeout(step,10);
      return;
    }

    visited.add(key(r,c));

    // mark visited (skip start/end)
    if(!(r===start[0] && c===start[1]) &&
       !(r===end[0] && c===end[1])){
      grid[r][c].classList.add("visited");
    }

    // reached destination
    if(r===end[0] && c===end[1]){
      reconstructPath(parent);
      return;
    }

    // explore neighbors
    for(let [dr,dc] of [[1,0],[-1,0],[0,1],[0,-1]]){

      let nr = r + dr;
      let nc = c + dc;

      if(nr>=0 && nr<rows && nc>=0 && nc<cols &&
         !grid[nr][nc].classList.contains("wall")){

        let newCost = cost + 1;

        if(newCost < dist[key(nr,nc)]){

          dist[key(nr,nc)] = newCost;

          parent[key(nr,nc)] = [r,c];

          pq.push({
            pos:[nr,nc],
            cost:newCost
          });
        }
      }
    }

    setTimeout(step,20);
  }

  step();
}
