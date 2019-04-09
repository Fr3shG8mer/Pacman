
public interface GameObject {
	boolean isOnPosition(int x, int y);
	void collide(Board board);
	boolean isSolid(); 
}
