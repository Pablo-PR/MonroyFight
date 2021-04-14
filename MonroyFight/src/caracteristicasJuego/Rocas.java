package caracteristicasJuego;

public class Rocas extends ElementosTablero{

	private char simboloRoca;
	private int posicionX, posicionY, cantidad;
	public Rocas(int posicionX, int posicionY, int cantidad) {
		super(posicionX, posicionY, cantidad);
		this.simboloRoca=Constantes.SIMBOLO_ROCA;
	}

}
