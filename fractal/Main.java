import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

// Código original: https://github.com/R-e-t-u-r-n-N-u-l-l/Fractal-Shapes
// Alterações feitas por: Bryan Cruz e Daniel Escudero

public class Main {
	private Frame frame;
	private Shape currentShape;
	
	public Main() {
        // Cria o objeto que desenhará o Fractal, o Frame no qual ele será desenhado e a BufferStrategy
		currentShape = new FractalTree(this);
		frame 		 = new Frame(currentShape.getName());
		
		BufferStrategy bs = frame.getBufferStrategy();
			
		if(bs == null) {
			frame.createBufferStrategy(2);
			bs = frame.getBufferStrategy();
		}
		

        // Loop de renderização: irá desenhar o frame na tela.
        // A cada iteração, o frame é redesenhado com as atualizações da imagem.
        //
        // Desenha a árvore fractal com 1 iteração até 20 iterações.
        // A cada iteração `n`, desenha a árvore fractal com `n` passos, i.e,
        // `n` passos de bifurcação (ou profundidade)
        //
        // Perto da 15ª iteração já não conseguimos ver mudanças na árvore,
        // apenas se tivermos algum zoom
        for (int count = 1; count <= 20; count++) {
            Graphics g = bs.getDrawGraphics();
			
			g.setColor(Color.BLACK);
			g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			
			g.setColor(Color.WHITE); 

            // O objeto da classe herdada de Shape desenhará o Fractal
			currentShape.begin(g, count);
			
			g.dispose();	
			bs.show();

            // Pausa 2 segundos o frame para conseguirmos ver a construção da árvore com calma
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (Exception e) {
                System.out.println("error");
            }
		}
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
