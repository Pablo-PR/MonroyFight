package caracteristicasJuego;

public class Jugador {

	private TipoPersonaje raza;
	private int numMovimientos, posicionX, posicionY;
	private static int contadorPosiciones=0;
	
	public Jugador(TipoPersonaje raza) {
		this.raza=raza;
		this.posicionX=obtenerPosicionX();
		this.posicionY=obtenerPosicionY();;
	}

	

	public TipoPersonaje getRaza() {
		return raza;
	}
	

	public void setRaza(TipoPersonaje raza) {
		this.raza = raza;
	}

	public int getNumMovimientos() {
		return numMovimientos;
	}

	public void setNumMovimientos(int numMovimientos) {
		this.numMovimientos = numMovimientos;
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
	
	private int obtenerPosicionX() {
		int posicionX=Constantes.POSICIONES_SALIDA_EJE_X;
		contadorPosiciones++;
		return posicionX;
	}
	
	private int obtenerPosicionY() {
		
		return 0;
	}

	
	private int tirarDado() {
		int movimientos=(int)(Math.random() * raza.getVelocidad()) + Constantes.MINIMO_MOV_JUGADOR;
		return movimientos;
	}

	@Override
	public String toString() {
		return "Raza del jugador: " + raza + ", posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
	}
	
	
	
}
