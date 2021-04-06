package caracteristicasJuego;

public enum TipoPersonaje {

	ELFO(Elfo.VELOCIDAD_ELFO, Elfo.MAGIA_ELFO, Elfo.FUERZA_ELFO),
	OGRO(Ogro.VELOCIDAD_OGRO, Ogro.MAGIA_OGRO, Ogro.FUERZA_OGRO),
	GUERRERO(Guerrero.VELOCIDAD_GUERRERO, Guerrero.MAGIA_GUERRERO, Guerrero.FUERZA_GUERRERO),
	MAGO(Mago.VELOCIDAD_MAGO, Mago.MAGIA_MAGO, Mago.FUERZA_MAGO);
	
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
