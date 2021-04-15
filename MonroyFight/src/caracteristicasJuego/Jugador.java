package caracteristicasJuego;

public class Jugador{

	private TipoPersonaje raza;
	private int numGemas, numPociones, cantidadDinero, posicionX, posicionY;
	

	public Jugador(TipoPersonaje raza, int posicionX, int posicionY) {
		this.raza=raza;
		this.numGemas=0;
		this.numPociones=0;
		this.cantidadDinero=0;
		this.posicionX=posicionX;
		this.posicionY=posicionY;
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
	
	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	private int tirarDado() {
		int movimientos=(int)(Math.random() * raza.getVelocidad() + Constantes.MINIMO_MOV_JUGADOR);
		
		return movimientos;
	}
	
	
}
