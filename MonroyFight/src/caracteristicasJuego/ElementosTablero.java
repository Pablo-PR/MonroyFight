package caracteristicasJuego;

public abstract class ElementosTablero {

	private int cantidad, posicionX, posicionY;
	
	
	public ElementosTablero(int posicionX, int posicionY, int cantidad) {
		this.posicionX=posicionX;
		this.posicionY=posicionY;
		this.cantidad=cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

}
