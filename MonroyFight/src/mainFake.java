
public class mainFake {

	public static void main(String[] args) {
		
		int matriz[][] = new int[10][10];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }

        for (int x = 0; x < matriz.length; x++) {
            System.out.println(".........................................");
            System.out.print("| ");

            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(matriz[x][y]);

                if (y != matriz[x].length - 1) {
                    System.out.print(" | ");
                }
            }

            System.out.println(" |");

            if (x == matriz.length -1) {
                System.out.println(".........................................");
            }
        }

	}

}
