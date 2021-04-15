package caracteristicasJuego;

public class Gema extends ElementosTablero{

	private int posicionX, posicionY, cantidad;
	
	public Gema(int cantidad, int posicionX, int posicionY) {
		super(cantidad, posicionX, posicionY);
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
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void eliminarRoca() throws JuegoException{
		if (this.cantidad==0) {
			throw new JuegoException("No dispones de gemas suficientes para romper una roca");
		}
		this.cantidad--;
		//Borrar Roca
	}

	

	@Override
	public String toString() {
		return "Gema [posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
	}
	
	
}
