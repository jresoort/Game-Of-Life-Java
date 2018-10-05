/*
Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
Any live cell with more than three live neighbours dies, as if by overcrowding.
Any live cell with two or three live neighbours lives, unchanged, to the next generation.
Any dead cell with exactly three live neighbours cells will come to life.
*/
class LifeStarts {
	public static void main(String[] args)
			throws java.lang.InterruptedException{
		Life life = new Life(30);
		life.drawWorld();

		loop(life);
		return;
	}

	public static void loop(Life life) {
		while(true){
			int i = 0;
			long time = System.currentTimeMillis();
			while (System.currentTimeMillis() < time + 1000) {
				i++;
				life.nextGeneration();
//				life.drawWorld();
			}

			System.out.println("Generations per second =  " + i);
		}
	}
}