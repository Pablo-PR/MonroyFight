package caracteristicasJuego;

public interface Posicionar {

	default int obtenerPosicion() {
		int posicion = (int) (Math.random() * 10);
		
		return posicion;
	}
}
