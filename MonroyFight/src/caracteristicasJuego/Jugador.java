package caracteristicasJuego;

public class Jugador extends ElementosTablero{

	private TipoPersonaje raza;
	private int numGemas, numPociones, cantidadDinero, posicionX, posicionY;
	

	public Jugador(TipoPersonaje raza, int posicionX, int posicionY, char simbolo) {
		super(simbolo);
		this.raza=raza;
		this.numGemas=0;
		this.numPociones=0;
		this.cantidadDinero=0;
		this.posicionX=posicionX;
		this.posicionY=posicionY;
	}

	
	public int getPosicionX() {
		return posicionX;
	}


	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}


	public int getPosicionY() {
		return posicionY;
	}


	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}


	public TipoPersonaje getRaza() {
		return raza;
	}
	
	public int getNumGemas() {
		return numGemas;
	}

	public int getNumPociones() {
		return numPociones;
	}

	public int getCantidadDinero() {
		return cantidadDinero;
	}

	public void setCantidadDinero(int cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}
	
	/**
	 * Método que aumenta el número de gemas del jugador al recoger una en el tablero.
	 */
	public void recogerGema() {
		numGemas++;
	}

	/**
	 * Método que disminuye el número de gemas del jugador al usar una.
	 */
	public void usarGema() {
		numGemas--;
	}
	
	/**
	 * Método que aumenta la cantidad de dinero del jugador al recoger una moneda en el tablero.
	 */
	public void recogerDinero() {
		cantidadDinero++;
	}
	
	/**
	 * Método al que se le pasa por parámetro otro jugador, el cual va a recibir el dinero del jugador actual si pierde y no tiene poción.
	 */
	public void perderDinero(Jugador otro) {
		otro.setCantidadDinero(otro.cantidadDinero + this.cantidadDinero);
		this.cantidadDinero=0;
	}
	
	/**
	 * Método que aumenta la cantidad de pociones del jugador al recoger una en el tablero.
	 */
	public void recogerPocion() {
		numPociones++;
	}

	/**
	 * Método que disminuye el número de pociones del jugador al usar una.
	 */
	public void usarPocion() {
		numPociones--;
	}
}