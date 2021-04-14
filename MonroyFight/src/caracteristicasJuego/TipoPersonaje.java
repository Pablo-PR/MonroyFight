package caracteristicasJuego;

public enum TipoPersonaje {

	ELFO(Constantes.VELOCIDAD_ELFO, Constantes.MAGIA_ELFO, Constantes.FUERZA_ELFO),
	OGRO(Constantes.VELOCIDAD_OGRO, Constantes.MAGIA_OGRO, Constantes.FUERZA_OGRO),
	GUERRERO(Constantes.VELOCIDAD_GUERRERO, Constantes.MAGIA_GUERRERO, Constantes.FUERZA_GUERRERO),
	MAGO(Constantes.VELOCIDAD_MAGO, Constantes.MAGIA_MAGO, Constantes.FUERZA_MAGO);
	
	public int velocidad, magia, fuerza;
	
	private TipoPersonaje(int velocidad, int magia, int fuerza) {
		this.velocidad = velocidad;
		this.magia = magia;
		this.fuerza = fuerza;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public int getMagia() {
		return magia;
	}
	
	public int getFuerza() {
		return fuerza;
	}
}
