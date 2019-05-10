import java.awt.Graphics;
import java.awt.Graphics2D;

// Código original: https://github.com/R-e-t-u-r-n-N-u-l-l/Fractal-Shapes
// Alterações feitas por: Bryan Cruz e Daniel Escudero

// Classe responsável pelo fractal 
//
// Esse fractal será uma arvore, na qual começamos com uma 
// linha e partir dela saem outras 2, com uma rotação de 45º
// para cada. Repetimos então a criação dessas outras 2 
// linhas para cada linha que adicionarmos na árvore.

public class FractalTree extends Shape {
    // Inicialização das variáveis principais da classe
    
	private Graphics2D g;
	private int length	  = 300;

    // O angulo de rotação para  a criação da arvore
	private double angle1 = Math.PI / 4, 
				   angle2 = Math.PI / 4;

	public FractalTree(Main main) {
		super(main, "Fractal Tree");
	}

	public FractalTree(Main main, double angle1, double angle2) {
		super(main, "Fractal Tree");
        this.angle1 = angle1;
        this.angle2 = angle2;
	}

    // Inicialização da criação da árvore, faz a primeira
    // chamada para a função recursiva "fractal"
	public void begin(Graphics g, int count) {
        if (count < 1) {
            count = 1;
        }

		this.g 	   = (Graphics2D) g;
		int width  = main.getFrame().getWidth();
		int height = main.getFrame().getHeight();
		
		g.translate(width / 2, height);
		fractal(length, count);
	}
	
    // Função recursiva que desenha a árvore fractal. 
    //
    // A quantidade de subchamadas dessa função é limitada 
    // pelo parâmetro "count".
    //
    // Desenha o ramo principal (ou tronco) da árvore (g.drawLine), 
    // depois movimenta e rotaciona a imagem para desenhar 
    // os 2 ramos secundários (ou galhos), recursivamente fazendo uma 
    // nova chamada a si mesma. Cada galho terá 2/3 do tamanho
    // do tronco da árvore.
	private void fractal(int length, int count) {
		g.drawLine(0, 0, 0, -length);
		g.translate(0, -length);
		
		if(count < 1)
			return;
		
		g.rotate(angle1);
		fractal(length / 3 * 2, count - 1);
		
		g.translate(0, length / 3 * 2);
		g.rotate(-angle1);
		
		g.rotate(-angle2);
		fractal(length / 3 * 2, count - 1);
		
		g.translate(0, length / 3 * 2);
		g.rotate(angle2);
	}
}
