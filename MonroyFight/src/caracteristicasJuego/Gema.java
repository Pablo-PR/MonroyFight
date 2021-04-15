package caracteristicasJuego;

public class Gema extends ElementosTablero{
	
	public Gema(int cantidad, int posicionX, int posicionY) {
		super(cantidad, posicionX, posicionY);
	}

	

	@Override
	public String toString() {
		return "Gema [posicionX=" + super.getPosicionX() + ", posicionY=" + super.getPosicionY() + "]";
	}
	
}
