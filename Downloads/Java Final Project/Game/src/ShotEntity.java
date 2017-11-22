
public class ShotEntity extends Entity {

	private double moveSpeed = -300;
	private Game game;
	private boolean used = false;
	
	public ShotEntity(Game game,String sprite,int x,int y) {
		super(sprite,x,y);
		
		this.game = game;
		
		dy = moveSpeed;
	}

	
	public void move(long delta) {
		// proceed with normal move
		super.move(delta);
		
		// if we shot off the screen, remove ourselves
		if (y < -100) {
			game.removeEntity(this);
		}
	}
	
	
	public void collidedWith(Entity other) {
		// prevents double kills, if we've already hit something,
		// don't collide
		if (used) {
			return;
		}
		
		// if we've hit an alien, kill it!
		if (other instanceof BossEntity) {
			game.removeEntity(this);
			((BossEntity) other).loseHealth();
			
			if(((BossEntity) other).isDead()) {
				game.removeEntity(other);
				game.notifyAlienKilled();
				used = true;
			}
			
		}
		else if (other instanceof AlienEntity) {
			// remove the affected entities
			game.removeEntity(this);
			game.removeEntity(other);
			
			// notify the game that the alien has been killed
			game.notifyAlienKilled();
			used = true;
		}
	}
}