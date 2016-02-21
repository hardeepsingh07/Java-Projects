public class SortTimer {
	long comparision;
	long moves;
	long time;

	public void reset() {
		comparision = 0;
		moves = 0;
		time = System.nanoTime();
	}

	public void addComparision() {
		comparision++;
	}

	public void addComparison(int n) {
		comparision += n;
	}

	public void addMove() {
		moves++;
	}

	public void addMoves(int n) {
		moves += n;
	}

	public long getComparison() {
		return comparision;
	}

	public long getMoves() {
		return moves;
	}

	public long getElapsedTime() {
		return (System.nanoTime() - time);
	}
}
