import java.awt.Graphics;
import java.awt.Graphics2D;

// Código original: https://github.com/R-e-t-u-r-n-N-u-l-l/Fractal-Shapes
// Alterações feitas por: Bryan Cruz e Daniel Escudero

// Classe abstrata que define as funções e variáveis que uma classe fractal
// herdeira deve ter para poder ser renderizada pela classe Main.

public abstract class Shape {
	protected Main main;
	protected Graphics2D g;
	private String name;
	
	public Shape(Main main, String name) {
		this.main = main;
		this.name = name;
	}
	
	public abstract void begin(Graphics g, int count);
	
	public String getName() {
		return name;
	}
}
