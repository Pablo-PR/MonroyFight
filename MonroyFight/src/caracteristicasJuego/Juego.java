package caracteristicasJuego;

public class Juego implements Posicionar{

	private ElementosTablero tablero[][];
	private Jugador jugadores[];
	private int alto, ancho, numJugadores, jugadorJuega;
	boolean finished;
	
	
	public Juego(int alto, int ancho, int numJugadores) {
		this.alto=alto;
		this.ancho=ancho;
		this.numJugadores=numJugadores;
		tablero = new ElementosTablero[alto][ancho];
	}
	
	
	public Jugador[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getJugadorJuega() {
		return jugadorJuega;
	}

	public void setJugadorJuega(int jugadorJuega) {
		this.jugadorJuega = jugadorJuega;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}


	public void crearJugador(TipoPersonaje tipo) {
		Jugador jugadorA = new Jugador(tipo, obtenerPosicion(), obtenerPosicion());
		
	}
	
	
	

}
