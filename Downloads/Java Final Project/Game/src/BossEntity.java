
public class BossEntity extends Entity{
	
	private Game game;
	
	private double moveSpeed = 75;
	private int health = 5;
	private boolean temp = false;
	private double lastFire = 0;
	
	public BossEntity(Game game,String ref,int x,int y) {
		super(ref, x, y);
		
		this.game = game;
		dx = -moveSpeed;
		health+= this.game.round / 2;
	}
	
	public void move(long delta) {
		// if we have reached the left hand side of the screen and
		// are moving left then request a logic update 
		if ((dx < 0) && (x < 10)) {
			game.updateLogic();
		}
		// and vice vesa, if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > 550)) {
			game.updateLogic();
		}
		
		shoot();
		
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
		if(other instanceof ShotEntity) {
			loseHealth();
		}
		return;
	}
	
	public void loseHealth() {
		if(!temp) {
			health -= 1;
		}
		temp = true;
	}
	
	public boolean isDead() {
		temp = false;
		if(health <= 0) {
			return true;
		}
		return false;
	}
	
	public boolean shoot() {
		if(System.currentTimeMillis() - lastFire > 1000) {
			if(Math.random() * 10 > 4) {
				game.bossShot();
				lastFire = System.currentTimeMillis();
				return true;
			}
		}
		return false;
	}
}
