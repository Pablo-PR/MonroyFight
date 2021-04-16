package caracteristicasJuego;

public class Juego {

	private ElementosTablero tablero[][];
	private Jugador jugadores[];
	private int alto, ancho, numJugadores, jugadorJuega, jugadorTira;
	boolean finished;

	public Juego(int alto, int ancho, int numJugadores) {
		this.alto = alto;
		this.ancho = ancho;
		this.numJugadores = numJugadores;
		finished = false;
		tablero = new ElementosTablero[alto][ancho];
		jugadores = new Jugador[numJugadores];
		jugadorJuega = 0;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getJugadorJuega() {
		return jugadorJuega;
	}

	public void setJugadorJuega(int jugadorJuega) {
		this.jugadorJuega = jugadorJuega;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}

	public void crearJugador(TipoPersonaje tipo) {
		jugadores[jugadorJuega] = new Jugador(tipo, obtenerCoordenada(), obtenerCoordenada(), Constantes.ID_JUGADORES[jugadorJuega]);

		jugadorJuega++;
	}

	public String realizarMovimiento(String direccion) {
		switch (direccion) {
		case "Norte":
			break;
		case "Sur":
			break;
		case "Este":
			break;
		case "Oeste":
			break;
		}
		
		d;
	}

	public void imprimirTablero() {
		
		for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                
            	introducirElementos(Constantes.NUM_ROCAS, Constantes.SIMBOLO_ROCA);
            	introducirElementos(Constantes.NUM_GEMAS, Constantes.SIMBOLO_GEMA);
            	introducirElementos(Constantes.NUM_POZOS, Constantes.SIMBOLO_POZO);
            	introducirElementos(Constantes.NUM_POCIONES, Constantes.SIMBOLO_POCION);
            	introducirElementos(Constantes.NUM_DINERO, Constantes.SIMBOLO_DINERO);
            	introducirJugadores();
            }
        }
		
		for (int x = 0; x < tablero.length; x++) {
            System.out.println(".........................................");
            System.out.print("| ");

            for (int y = 0; y < tablero[x].length; y++) {
            	if(comprobarHueco(x,y)) {
            		System.out.println(' ');
            	}else {
            		System.out.print(tablero[x][y].getSimbolo());
            	}
            	
                if (y != tablero[x].length - 1) {
                    System.out.print(" | ");
                }
            }

            System.out.println(" |");

            if (x == tablero.length -1) {
                System.out.println(".........................................");
            }
		}
        
	}

	public void introducirJugadores() {
		boolean posicionado=false;
		
		for (int k = 0; k < jugadorJuega; k++) {
			do {
				if(tablero[jugadores[jugadorJuega].getPosicionX()][jugadores[jugadorJuega].getPosicionY()] == null) {
					
					tablero[jugadores[jugadorJuega].getPosicionX()][jugadores[jugadorJuega].getPosicionY()] = jugadores[jugadorJuega];
					posicionado=true;
				}else {
					jugadores[jugadorJuega].setPosicionX(obtenerCoordenada());
					jugadores[jugadorJuega].setPosicionY(obtenerCoordenada());
				}
			}while(!posicionado);
		}
	}

	public void introducirElementos(int cantidad, char simboloElemento) {
		ElementosTablero elementoAIntroducir=null;
		boolean termina=false;
		
		for (int i = 0; i < cantidad; i++) {
			
			do {
				int x=obtenerCoordenada();
				int y=obtenerCoordenada();
				
				if(comprobarHueco(x, y)) {
					switch(simboloElemento) {
					case Constantes.SIMBOLO_ROCA:
						elementoAIntroducir = new Rocas(simboloElemento);
						break;
						
					case Constantes.SIMBOLO_DINERO:
						elementoAIntroducir = new Dinero(simboloElemento);
						break;
						
					case Constantes.SIMBOLO_GEMA:
						elementoAIntroducir = new Gema(simboloElemento);
						break;
						
					case Constantes.SIMBOLO_POCION:
						elementoAIntroducir = new Pociones(simboloElemento);
						break;
						
					case Constantes.SIMBOLO_POZO:
						elementoAIntroducir = new Pozo(simboloElemento);
						break;
						
					}
					
					termina=true;
					tablero[x][y] = elementoAIntroducir;
				}
			}while(!termina);
			
			}
	}

	public boolean comprobarHueco(int x, int y) {
		boolean vacio=false;
        	
		if (tablero[x][y]==null) {
			vacio=true;
		}
		return vacio;
	}
	
	private int tirarDado() {
		int movimientos=(int)(Math.random() * jugadores[jugadorJuega]..getVelocidad() + Constantes.MINIMO_MOV_JUGADOR);
		
		return movimientos;
	}
	
	
	public String getJugadorTurno() {
		boolean primeroCreado=false;
		int primerJugador, ultimoEnTirar=0;
		String mensajeTurno;
		
		if(!primeroCreado) {
			primerJugador=(int)(Math.random() * numJugadores + 1);
			ultimoEnTirar=primerJugador;
			primeroCreado=true;
			mensajeTurno="El primer jugador es: " + jugadores[primerJugador].getSimbolo();
		}else {
			ultimoEnTirar++;
			if(ultimoEnTirar > numJugadores) {
				ultimoEnTirar=0;
			}
			mensajeTurno="El siguiente jugador es: " + jugadores[ultimoEnTirar].getSimbolo();
		}
		
		return mensajeTurno;
	}
	

	private boolean generarPosicionTablero() {
		boolean estaLibre = true;
		int x = obtenerCoordenada();
		int y = obtenerCoordenada();
		return false;
	}

	public int obtenerCoordenada() {
		int posicion = (int) (Math.random() * 10);

		return posicion;
	}

	public String toString() {
		String c = null;
		StringBuilder sb = new StringBuilder(c);
		for (int i = 0; i < numJugadores; i++) {
			sb.append("\nEl jugador numero " + (i+1) + " es el caracter: " + jugadores[i].getSimbolo() + ".\n");
			sb.append("Gemas: " + jugadores[i].getNumGemas() + " Dinero: " + jugadores[i].getCantidadDinero() + " Pociones: " + jugadores[i].getNumPociones());
		}
		c=sb.toString();
		return c;
	}

	

}
