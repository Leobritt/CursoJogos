package br.com.mariojp.game;

public class Inimigo extends Sprite {

	private int initX;
	private IMovimento movimento;

	public Inimigo(int x, int y) {
		super(x, y);
		this.initX = x;
		movimento = new MovimentoInimigo();
		initInimigo();
	}

	private void initInimigo() {
		carregarImagem("/imagens/alien.png");
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
