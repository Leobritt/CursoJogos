package br.com.mariojp.game.entities.inimigo;

import br.com.mariojp.game.MovimentoBoss;

public class BossInimigo extends InimigoAbstract {

	public BossInimigo(int x, int y) {
		super(x, y);
		this.setInitX(x); 
		this.setMovimento( new MovimentoBoss());
		initBoss();
	}

	private void initBoss() {
		carregarImagem("/imagens/bossUp.png");
		getImageDimensions();
	}
}
