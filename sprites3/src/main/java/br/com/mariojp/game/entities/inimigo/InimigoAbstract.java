package br.com.mariojp.game.entities.inimigo;

import br.com.mariojp.game.IMovimento;
import br.com.mariojp.game.entities.Sprite;

public abstract class InimigoAbstract extends Sprite{

    private int initX;
	private IMovimento movimento;

    public InimigoAbstract(int x, int y) {
        super(x, y);
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
    public IMovimento getMovimento() {
        return movimento;
    }
    public void setMovimento(IMovimento movimento) {
        this.movimento = movimento;
    }
}