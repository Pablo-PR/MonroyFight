package caracteristicasJuego;

public class Pozo extends ElementosTablero{

	public Pozo (char simbolo) {
		super(simbolo);
	}
	
	
	public boolean conflictoPozo(int magiaMaximaJugador) {
		boolean jugadorPasa=false;
		
		int puntuacionPozo=(int) (Math. random() * magiaMaximaJugador) + 1;
		int puntuacionJugador=(int) (Math. random() * magiaMaximaJugador) + 1;
		
		if(puntuacionJugador > puntuacionPozo) {
			jugadorPasa=true;
		}
		return jugadorPasa;
	}
}
