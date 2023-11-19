package br.com.mariojp.game;

public class BossInimigo extends Sprite {

	private int initX;
	private IMovimento movimento;

	public BossInimigo(int x, int y) {
		super(x, y);
		this.initX = x;
		movimento = new MovimentoBoss();
		initBoss();
	}

	private void initBoss() {
		carregarImagem("/imagens/bossUp.png");
		getImageDimensions();
	}

	public void move() {
		movimento.move(this);
	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

}
