import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;

public class Wolfram {
	// Retorna array de numeros a partir de uma string
	private static int[] stringToIntArray(String string) {
		char[] chars = string.toCharArray();
		int[] intArray = new int[chars.length];
		
		for (int i = 0; i < chars.length; i++)
			intArray[i] = (int) chars[i] - 48;

		return intArray;
	}
	
	// Atualiza o array mundo e retorna um array do mundo Atualizado
	private static int[] atualizaMundo(int[] mundo, int[] rule) {
		int length = mundo.length;
		int[] novoMundo = new int[length];

		for (int i = 0; i < length; i++)
			novoMundo[i] = rule[7 - 4 * mundo[(i-1+length)%length] - 2 * mundo[i] - mundo[(i+1)%length]];

		return novoMundo;
	}
	
	// atualiza o array da GUI, que será mostrado
	private static ArrayList<int[]> atualizaGUI(ArrayList<int[]> display, int[] mundo, int tamArray) {
		display.add(mundo);

		if (display.size() == tamArray)
			display.remove(0);
			
		return display;
	}

	public static GUI setupGUI (int larguraCelula, int tamanhoMundo, int tamArray) {
		int x = larguraCelula*tamanhoMundo;
		int y = larguraCelula*(tamArray+1);

		return new GUI(x, y, larguraCelula);
	}

	public static int[] setupRegra (String regra) {
		String numeroDaRegra = Integer.toBinaryString(Integer.parseInt(regra));
		while(numeroDaRegra.length() < 8)
			numeroDaRegra = 0 + numeroDaRegra;
		
		return stringToIntArray(numeroDaRegra);
	}
		
	public static void main (String[] args) throws IOException, InterruptedException {
		String inputRegra = "", inputMundo = "";
		boolean inputValido = args.length == 1 || args.length == 2;

		// Validação da entrada do usuário
		if (args.length < 1 || args.length > 2) {
			System.out.println("USO: java Wolfram <NumeroDaRegra> [MundoInicial]");
			System.out.println("MundoInicial deve ser uma string de 0 e 1, onde 0 representa uma célula morta, e 1 uma viva.");
			System.out.println("Pode ser também o caracter 'R', para um mundo aleatório (pre-definido). O parâmetro é opcional.");
			System.out.println("Para resultados melhores, use strings longas para MundoInicial");
		} else if (args.length == 1) {
			inputRegra = args[0];
			inputMundo = MundoInicial.getMundoPadrao();
		} else if (args.length == 2) {
			inputRegra = args[0];

			if (args[1].charAt(0) == 'R' || args[1].charAt(0) == 'r') {
				inputMundo = MundoInicial.getMundoAleatorio();
			} else {
				inputMundo = args[1];
			}
		}

		if (inputValido) {
			// Extrai a regra a partir do primeiro parametro passado
			int[] regra = setupRegra(inputRegra);
			
			// Extrai o mundo a partir do segundo parametro passado
			int[] mundo = stringToIntArray(inputMundo);

			// Cria as dimensões do automato
			int largura = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
			int altura = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
			int larguraCelula = largura / (mundo.length+1);
			int tamArray = altura / larguraCelula - 1;
			
			ArrayList<int[]> display = new ArrayList<int[]>(0);
			
			GUI gui = setupGUI(larguraCelula, mundo.length, tamArray);
			
			// Atualiza o automato, com um intervalo de 50 ms entre cada iteração.
			while (true) {
				display = atualizaGUI(display, mundo, tamArray); // Atualiza a interface
				gui.draw(display); // Desenha
				mundo = atualizaMundo(mundo, regra); // Atualiza o estado
				Thread.sleep(50); // Intervalo entre frames (em ms)
			}
		}
	}
}
