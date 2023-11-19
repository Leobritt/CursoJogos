package br.com.mariojp.game.entities.inimigo;

import br.com.mariojp.game.MovimentoInimigo;

public class Inimigo extends InimigoAbstract {

	public Inimigo(int x, int y) {
		super(x, y);
		this.setInitX(x); 
		this.setMovimento( new MovimentoInimigo());
		initInimigo();
	}

	private void initInimigo() {
		carregarImagem("/imagens/alien.png");
		getImageDimensions();
	}
}
