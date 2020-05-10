package engine;

public interface GameListener {
	public void onGameOver();
	
	public void onHandUpdated();
	
	public void onFieldUpdated();
	
	public void onHeroUpdated();
	
	public void onManaCrystalsUpdated();
	
	public void onDeckUpdated();
}
