
public abstract class Personaje {
	
	private int velocidad, magia, fuerza, dinero;
	TipoPersonaje raza;
	
	public Personaje(TipoPersonaje raza) {
		this.raza = raza;
		velocidad = raza.getVelocidad();
		magia = raza.getMagia();
		fuerza = raza.getFuerza();
		this.dinero=0;
	}
}
