package br.com.mariojp.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.function.Supplier;

import javax.swing.JPanel;
import javax.swing.Timer;

import br.com.mariojp.game.entities.Missil;
import br.com.mariojp.game.entities.Nave;
import br.com.mariojp.game.entities.inimigo.BossInimigo;
import br.com.mariojp.game.entities.inimigo.Inimigo;
import br.com.mariojp.game.entities.inimigo.InimigoAbstract;

public class Jogo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Nave nave;

	private int score = 0;

	private ArrayList<InimigoAbstract> inimigos = new ArrayList<>();

	private ArrayList<InimigoAbstract> bossInimigos = new ArrayList<>();

	private Random random = new Random();

	private boolean endgame = false;

	private final int DELAY = 10;

	private final int B_WIDTH = 800;
	private final int B_HEIGHT = 600;

	public Jogo() {
		initJogo();
	}

	private void initJogo() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setDoubleBuffered(true);
		nave = new Nave(40, 60, B_WIDTH);

		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!endgame) {
			desenhar(g);
		} else {
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void desenhar(Graphics g) {
		g.drawImage(nave.getImage(), nave.getX(), nave.getY(), this);

		for (Missil m : nave.getMissiles()) {
			if (m.isVisible()) {
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}
		for (InimigoAbstract i : inimigos) {
			if (i.isVisible()) {
				g.drawImage(i.getImage(), i.getX(), i.getY(), this);
			}
		}

		for (InimigoAbstract i : bossInimigos) {
			if (i.isVisible()) {
				g.drawImage(i.getImage(), i.getX(), i.getY(), this);
			}
		}
		g.setColor(Color.WHITE);
		g.drawString("PONTOS : " + score, 5, 15);

	}

	private void updateMissiles() {
		ArrayList<?> ms = nave.getMissiles();
		for (int i = 0; i < ms.size(); i++) {
			Missil m = (Missil) ms.get(i);
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		stopGame();
		updateNave();

		if (score == 3) {
			// endgame = true;
			updateInimigo(bossInimigos, () -> new BossInimigo(B_WIDTH, random.nextInt(B_HEIGHT - 20) + 10), 1);
		}

		checkCollisions(bossInimigos, true);
		updateMissiles();
		updateInimigo(inimigos, () -> new Inimigo(B_WIDTH, random.nextInt(B_HEIGHT - 20) + 10), 5);
		checkCollisions(inimigos, false);
		repaint();
	}

	public <T extends InimigoAbstract> void checkCollisions(List<T> inimigosList, boolean isBoss) {
		Rectangle r3 = nave.getBounds();
		for (T inimigo : inimigosList) {
			Rectangle r2 = inimigo.getBounds();
			if (r3.intersects(r2)) {
				inimigo.setVisible(false);
				if (isBoss) {
					endgame = true;
				} else {
					nave.setVisible(false);
					endgame = true;
				}
			}
		}

		ArrayList<Missil> ms = nave.getMissiles();
		for (Missil m : ms) {
			Rectangle r1 = m.getBounds();
			for (T inimigo : inimigosList) {
				Rectangle r2 = inimigo.getBounds();
				if (r1.intersects(r2)) {
					m.setVisible(false);
					inimigo.setVisible(false);
					score++;
				}
			}
		}
	}

	private <T extends InimigoAbstract> void movimentoInimigo(List<T> naves) {
		ListIterator<T> iterator = naves.listIterator();
		while (iterator.hasNext()) {
			T inimigo = iterator.next();
			if (inimigo.isVisible()) {
				inimigo.move();
			} else {
				iterator.remove();
			}
		}
	}

	private <T extends InimigoAbstract> void updateInimigo(List<T> inimigosList, Supplier<T> inimigoSupplier, int qtd) {
		while (inimigosList.size() < qtd) {
			inimigosList.add(inimigoSupplier.get());
		}
		movimentoInimigo(inimigosList);
	}

	private void stopGame() {
		if (endgame) {
			timer.stop();
		}
	}

	private void updateNave() {
		nave.move();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}
	}
}