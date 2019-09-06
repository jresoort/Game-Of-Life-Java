public class Life {
	private int dimension;
	private boolean[][] world;
	private boolean[][] newWorld;
	private long generation;

	Life(int dimension){
		this.dimension = dimension;
		this.generation = 0;
		createNewWorld();
	}

	Life(){
		this.dimension = 10;
		this.generation = 0;
		world = new boolean[dimension][dimension];
		newWorld = new boolean[dimension][dimension];


		world[3][3] = true;
		world[4][4] = true;
		world[5][4] = true;
		world[3][5] = true;
		world[4][5] = true;
	}

	// Contains the logic for the starting scenario.
	// Which cells are alive or dead in generation 0.
	private void createNewWorld(){
		world = new boolean[dimension][dimension];
		newWorld = new boolean[dimension][dimension];
		for(int row = 0; row < world.length; row++ ){
			for(int col = 0; col < world[row].length; col++ ){
				world[row][col] = (Math.random() < 0.3);
			}
		}
	}

	// Draws the world in the terminal.
	public void drawWorld(){
		System.out.print("\033[H\033[2J"); // Simulates a clear screen on linux machines
		for(int row = 0; row < world.length; row++ ){
			for(int col = 0; col < world[row].length; col++ ){
				 System.out.print(world[row][col] ? '@' : '.');
				 System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println("Generation: " + generation);
	}

	// Create the next generation
	public void nextGeneration(){
		for(int row = 0; row < newWorld.length; row++ ){
			for(int col = 0; col < newWorld[row].length; col++ ){
				newWorld[row][col] = isAlive(row, col);
			}
		}
		//recycle world arrays to reduce gc
		boolean[][] oldWorld = world;
		world = newWorld;
		newWorld = oldWorld;
		generation++;
	}

	// Calculate if an individual cell should be alive in the next generation.
	// Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
	private boolean isAlive(int row, int col){
		int liveCount = 0;
		boolean cellCurrentlyAlive = world[row][col];

		for(int r = -1; r <= 1; r++){
			int currentRow = row + r;
			currentRow = (currentRow < 0)? dimension - 1: currentRow;
			currentRow = (currentRow >= dimension)? 0 : currentRow;
			for(int c = -1; c <= 1; c++){
				int currentCol = col + c;
				currentCol = (currentCol < 0)? dimension - 1: currentCol;
				currentCol = (currentCol >= dimension)? 0 : currentCol;
				if(world[currentRow][currentCol]){
					liveCount++;
				}
			}
		}

		// Since all cells are counted including the cell we are calculating.
		// We must subtract 1 from the liveCount if the cell we are calculating for is alive.
		if(cellCurrentlyAlive){
			liveCount--;
		}
	
		// The game of life rules in code form.
		if(liveCount == 2 && cellCurrentlyAlive){
			return true;
		} else if(liveCount == 3){
			return true;
		} else {
			return false;
		}
	}

	public boolean currentlyAlive(int row, int col) {
		return world[row][col];
	}
}