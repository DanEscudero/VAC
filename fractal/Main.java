import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

// Código original: https://github.com/R-e-t-u-r-n-N-u-l-l/Fractal-Shapes
// Alterações feitas por: Bryan Cruz e Daniel Escudero

public class Main {
	private Frame frame;
	private Shape currentShape;
	
	public Main(double angle1, double angle2, int numberOfIterations, int updateRate) {
        // Cria o objeto que desenhará o Fractal, o Frame no qual ele será desenhado e a BufferStrategy
		currentShape = new FractalTree(this, angle1, angle2);
		frame 		 = new Frame(currentShape.getName());
		
		BufferStrategy bs = frame.getBufferStrategy();
			
		if(bs == null) {
			frame.createBufferStrategy(2);
			bs = frame.getBufferStrategy();
		}
		

        // Loop de renderização: irá desenhar o frame na tela.
        // A cada iteração, o frame é redesenhado com as atualizações da imagem.
        //
        // Desenha a árvore fractal com 1 iteração até `numberOfIterations` iterações.
        //
        // A cada iteração `n`, desenha a árvore fractal com `n` passos, i.e,
        // `n` passos de bifurcação (ou profundidade)
        
        for (int count = 1; count <= numberOfIterations; count++) {
            Graphics g = bs.getDrawGraphics();
			
			g.setColor(Color.BLACK);
			g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			
			g.setColor(Color.WHITE); 

            // O objeto da classe herdada de Shape desenhará o Fractal
			currentShape.begin(g, count);
			
			g.dispose();	
			bs.show();

            // Pausa o Frame por alguns tempo para conseguirmos ver a construção da árvore a cada iteração
            try {
                TimeUnit.MILLISECONDS.sleep(updateRate);
            } catch (Exception e) {
                System.out.println("error");
            }
		}
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {

        double angle1, angle2;
        int numberOfIterations, updateRate;

		// Validação da entrada do usuário
		if (args.length != 4) {
			System.out.println("USO: java Main <angulo1> <angulo2> <numDeIteracoes> <tempoDeExibicao>");
			System.out.println("`angulo1` e `angulo2` são os ângulos dos ramos da árvore, em graus");
			System.out.println("numDeIteracoes é a quantidade de iterações da árvore (profundidade)");
			System.out.println("tempoDeExibicao é o tempo em milissegundos de exibição de cada iteração da árvore");

            return;
		} else {
			angle1 = Integer.parseInt(args[0])*(Math.PI/180);
			angle2 = Integer.parseInt(args[1])*(Math.PI/180);
			numberOfIterations = Integer.parseInt(args[2]);
			updateRate = Integer.parseInt(args[3]);
        }

		new Main(angle1, angle2, numberOfIterations, updateRate);
	}
}
