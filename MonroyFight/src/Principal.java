import java.util.Arrays;
import java.util.Scanner;
import caracteristicasJuego.Constantes;
import caracteristicasJuego.Juego;
import caracteristicasJuego.JuegoException;
import caracteristicasJuego.TipoPersonaje;

public class Principal {

	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
	
		char direccion;
		try {
			Juego juego = crearJuego();
			System.out.println(juego);
			System.out.println(juego.nombresJugadores());
			System.out.println(juego.valoresJugadores());
			
			while (!juego.isTerminado()) {
				int dado = juego.getNumeroMovimientosJugador();
				
				System.out.println(
						"Le toca al jugador " + juego.getJugadorTurno() + ". El dado saca " + dado + " movimientos");
				
				for (int i = 1; i <= dado && !juego.isTerminado(); i++) {

					direccion = solicitarMovimiento();

					System.out.println(juego.moverJugador(direccion));

					System.out.println(juego);
					System.out.println(juego.valoresJugadores());

				}
				juego.proximoJugador();
			}

			System.out.println("Juego terminado. El ganador es:" + juego.getGanador());

		} catch (JuegoException e) {
			System.out.println(e.getMessage());
		}

	}

	private static char solicitarMovimiento() {
		char direccion;
		do {
			System.out.println("Indique a donde desea moverse: N: Norte, S: Sur, E: Este u O: Oeste");
			direccion = teclado.nextLine().charAt(0);
		} while (direccion != 'N' && direccion != 'S' && direccion != 'E' && direccion != 'O');
		return direccion;
	}

	private static Juego crearJuego() throws JuegoException {
		int numJugadores;

		do {
			System.out.println("Introduzca número de jugadores entre "+ Constantes.MINIMO_JUGADORES +  "  y " + Constantes.MAXIMO_JUGADORES);
			numJugadores = Integer.parseInt(teclado.nextLine());
		} while (numJugadores < Constantes.MINIMO_JUGADORES || numJugadores > Constantes.MAXIMO_JUGADORES);

		Juego juego = new Juego(Constantes.ALTO_TABLERO, Constantes.ANCHO_TABLERO, numJugadores);

		for (int i = 1; i <= numJugadores; i++) {

			TipoPersonaje tipo = //solicitarTipoPersonaje();
			//juego.crearJugador(tipo);

		}
		return juego;
	}

	private static TipoPersonaje solicitarTipoPersonaje() {
		// TODO Auto-generated method stub
		return null;
	}

	private static TipoPersonaje solicitarTipoJugador() {
		
		TipoPersonaje tipo=null;
		boolean hayError;

		do {
			try {
				System.out.println("Elija el tipo de jugador:" + Arrays.toString(TipoPersonaje.values()));
				tipo = TipoPersonaje.valueOf(teclado.nextLine().toUpperCase());
				hayError = false;
			} catch (IllegalArgumentException e) {
				System.out.println("Tipo incorrecto");
				hayError = true;
			}

		} while (hayError);

		return tipo;
	}

}