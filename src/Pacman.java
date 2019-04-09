
public class Pacman {
	int x;
	int y;
	Direction direction;
	int life = 3;
	public boolean isOnPosition(int x, int y) {
		return (this.x  == x) && (this.y  == y);
	}
	public void move(Direction inputDirection) {
		x= inputDirection.getNextX(x);
		y= inputDirection.getNextY(y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	
}
