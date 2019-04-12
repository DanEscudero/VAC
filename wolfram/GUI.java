import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

class World extends JComponent {
	int larguraCelula;
	ArrayList<int[]> display;
	
	public World(int larguraCelula, ArrayList<int[]> display) {
		this.larguraCelula = larguraCelula;
		this.display = display;
	}
	
	// Desenha todo o display com quadrados pretos onde tiver 1
	public void paint(Graphics graphics) {
		for (int y = 0; y < display.size(); y++) {
			int[] linha = display.get(y);
			for (int x = 0; x < linha.length; x++)
				if (linha[x] == 1) graphics.fillRect(x * larguraCelula, y * larguraCelula, larguraCelula, larguraCelula);
		}
	}
	
}


public class GUI extends JFrame {
	private int larguraCelula;
	private JFrame frame;
	
	public GUI(int x, int y, int larguraCelula) {
		this.larguraCelula = larguraCelula;

		// Setup inicial do frame
        frame = new JFrame("Automato De Wolfram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(x, y);
        frame.setVisible(true);
	}
	
	public void draw(ArrayList<int[]> display) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new World(larguraCelula, display));
		frame.setVisible(true);
	}

}
