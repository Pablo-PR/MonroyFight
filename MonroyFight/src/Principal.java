import java.util.Arrays;
import java.util.Scanner;
import caracteristicasJuego.Constantes;
import caracteristicasJuego.Juego;
import caracteristicasJuego.JuegoException;
import caracteristicasJuego.TipoPersonaje;

/**
 * 
 * @author Pablo Parra Rodríguez  &&  Iván Morales Mellado
 *
 */

public class Principal {

	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
	
		char direccion;
		try {
			Juego juego = crearJuego();
			juego.introducirElementosTablero();
			juego.imprimirTablero();
			System.out.println(juego.nombresJugadores());
			System.out.println(juego.valoresJugadores());
			
			while (!juego.isFinished()) {
				int dado = juego.tirarDado();
				juego.setMovimientosJugador(dado);
				
				System.out.println("Le toca al jugador " + juego.getJugadorTurno() + ". El dado saca " + dado + " movimientos");
				
				for (int i = 1; i <= dado && !juego.isFinished() && juego.getMovimientosJugador() > 0; i++) {
					
					if (dado != juego.getMovimientosJugador()) {
						System.out.println("Le quedan " + juego.getMovimientosJugador() + " movimientos");
					}
					
					direccion = solicitarMovimiento();

					juego.moverJugador(direccion);

					juego.imprimirTablero();
					System.out.println(juego.valoresJugadores());
					
					juego.restarMovimiento();
				}
				juego.proximoJugador();
			}

			System.out.println("Juego terminado. El ganador es el jugador " + juego.getGanador());

		} catch (JuegoException e) {
			System.out.println(e.getMessage());
		}

	}

	private static char solicitarMovimiento() {
		char direccion;
		
		do {
			System.out.println("Indique a donde desea moverse: N: Norte, S: Sur, E: Este u O: Oeste");
			direccion = teclado.nextLine().toUpperCase().charAt(0);
			
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

			TipoPersonaje tipo = solicitarTipoJugador();
			juego.crearJugador(tipo);

		}
		return juego;
	}


	private static TipoPersonaje solicitarTipoJugador() {
		
		TipoPersonaje tipo=null;
		boolean hayError;

		do {
			try {
				System.out.println("Elija el tipo de jugador: " + Arrays.toString(TipoPersonaje.values()));
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