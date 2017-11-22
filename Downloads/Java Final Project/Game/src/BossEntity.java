
public class BossEntity extends AlienEntity{
	
	private Game game;
	
	private double moveSpeed = 75;
	private int health = 4;
	
	public BossEntity(Game game,String ref,int x,int y) {
		super(game,ref,x,y);
		
		this.game = game;
		dx = -moveSpeed;
	}
	
	public void move(long delta) {
		// if we have reached the left hand side of the screen and
		// are moving left then request a logic update 
		if ((dx < 0) && (x < 10)) {
			game.updateLogic();
		}
		// and vice vesa, if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > 750)) {
			game.updateLogic();
		}
		
		// proceed with normal move
		super.move(delta);
	}
	
	public void doLogic() {
		// swap over horizontal movement and move down the
		// screen a bit
		dx = -dx;
		y += 10;
		
		// if we've reached the bottom of the screen then the player
		// dies
		if (y > 570) {
			game.notifyDeath();
		}
	}
	
	public void collidedWith(Entity other) {
		// collisions with aliens are handled elsewhere
	}
	
	public void loseHealth() {
		health--;
	}
	
	public boolean isDead() {
		return (health <= 0);
	}
}
