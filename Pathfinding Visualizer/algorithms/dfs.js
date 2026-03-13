function runDFS() {
  let stack = [start];
  let visited = new Set();
  let parent = {};
  visited.add(start.toString());

  while (stack.length > 0) {
    let [r,c] = stack.pop();
    if (r===end[0] && c===end[1]) {
      reconstructPath(parent);
      return;
    }
    for (let [dr,dc] of [[1,0],[-1,0],[0,1],[0,-1]]) {
      let nr=r+dr, nc=c+dc;
      if (nr>=0 && nr<rows && nc>=0 && nc<cols &&
          !grid[nr][nc].classList.contains("wall") &&
          !visited.has([nr,nc].toString())) {
        stack.push([nr,nc]);
        visited.add([nr,nc].toString());
        parent[[nr,nc]] = [r,c];
        grid[nr][nc].classList.add("visited");
      }
    }
  }
}