
public class Obstacle implements GameObject {
	int x;
	int y;
	
	
	public Obstacle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.x  == x) && (this.y  == y);
	}

	@Override
	public void collide(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

}
