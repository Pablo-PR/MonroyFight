package caracteristicasJuego;

public class Jugador extends ElementosTablero{
	//extends ElementosTablero
	/*
	 * Cómo usar cantidad en clase Jugador
	 * generar jugador while cantidad<=6
	 * elegir el nombre del jugador entre los simbolos posibles (se puede enum)
	 * 
	 * */

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

	public void setNumGemas(int numGemas) {
		this.numGemas = numGemas;
	}

	public int getNumPociones() {
		return numPociones;
	}

	public void setNumPociones(int numPociones) {
		this.numPociones = numPociones;
	}

	public int getCantidadDinero() {
		return cantidadDinero;
	}

	public void setCantidadDinero(int cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}
	

	
	
	
}
