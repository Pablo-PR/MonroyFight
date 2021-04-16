package caracteristicasJuego;

public class Juego {

	private ElementosTablero tablero[][];
	private Jugador jugadores[];
	private int alto, ancho, numJugadores, contadorJugador, jugadorJuega;
	boolean finished;

	public Juego(int alto, int ancho, int numJugadores) {
		this.alto = alto;
		this.ancho = ancho;
		this.numJugadores = numJugadores;
		contadorJugador = 0;
		jugadorJuega=0;
		finished = false;
		tablero = new ElementosTablero[alto][ancho];
		jugadores = new Jugador[numJugadores];
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

	public int getContadorJugador() {
		return contadorJugador;
	}

	public void setContadorJugador(int jugadorJuega) {
		this.contadorJugador = jugadorJuega;
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

	public int getJugadorJuega() {
		return jugadorJuega;
	}

	public void setJugadorJuega(int jugadorJuega) {
		this.jugadorJuega = jugadorJuega;
	}

	public void crearJugador(TipoPersonaje tipo) {
		jugadores[contadorJugador] = new Jugador(tipo, obtenerCoordenada(), obtenerCoordenada(), Constantes.ID_JUGADORES[contadorJugador]);

		contadorJugador++;
	}

	public void moverJugador(char direccion) {
		int posicionX = jugadores[jugadorJuega].getPosicionX();
		int posicionY = jugadores[jugadorJuega].getPosicionX();
		
		switch (direccion) {
		case 'N':
			int posicionFuturaX = posicionX--;
			int posicionFuturaY = posicionY;
			
			if (comprobarHueco(posicionFuturaX, posicionFuturaY)) {
				avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
			}
			else {
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Gema) {
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					jugadores[jugadorJuega].recogerGema();
				}
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Dinero) {
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					jugadores[jugadorJuega].recogerDinero();
				}
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Pociones) {
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					jugadores[jugadorJuega].recogerPocion();
				}
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Pozo) {
					if (conflictoPozo()) {
						System.out.println("El jugador ha ganado");
						avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					}
					else {
						System.out.println("El pozo ha ganado");
					}
				}
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Rocas) {
					if (jugadores[jugadorJuega].getNumGemas() > 0) {
						jugadores[jugadorJuega].usarGema();
						avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					}
					else {
						System.out.println("Hay una roca, seguro que se podria romper con algo.");
					}
				}
				if (tablero[posicionFuturaX][posicionFuturaY] instanceof Jugador) {
					Jugador jugadorAtacar;
					jugadorAtacar=(Jugador) tablero[posicionFuturaX][posicionFuturaY];
					conflictoJugador(jugadorAtacar);
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
					jugadores[jugadorJuega].recogerPocion();
				}
				 
				
			}
			
			
			break;
		case 'S':
			jugadores[jugadorJuega].setPosicionY(posicionX++);
			break;
		case 'E':
			jugadores[jugadorJuega].setPosicionX(posicionY++);
			break;
		case 'O':
			jugadores[jugadorJuega].setPosicionX(posicionY--);
			break;
		}
		
	}

	private void avanzarCasilla(int posicionX, int posicionY, int posicionFuturaX, int posicionFuturaY) {
		tablero[posicionX][posicionY] = null;
		tablero[posicionFuturaX][posicionFuturaY] = jugadores[jugadorJuega];
		
		if (posicionX != posicionFuturaX) {
			jugadores[jugadorJuega].setPosicionX(posicionFuturaX);
		}
		else {
			jugadores[jugadorJuega].setPosicionY(posicionFuturaY);
		}
		
	}

	public void imprimirTablero() {
		
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

	public void introducirElementosTablero() {
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
	}

	public void introducirJugadores() {
		boolean posicionado=false;
		
		for (int k = 0; k < contadorJugador; k++) {
			do {
				if(tablero[jugadores[contadorJugador].getPosicionX()][jugadores[contadorJugador].getPosicionY()] == null) {
					
					tablero[jugadores[contadorJugador].getPosicionX()][jugadores[contadorJugador].getPosicionY()] = jugadores[contadorJugador];
					posicionado=true;
				}else {
					jugadores[contadorJugador].setPosicionX(obtenerCoordenada());
					jugadores[contadorJugador].setPosicionY(obtenerCoordenada());
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
	
	public int tirarDado() {
		int movimientos=(int)(Math.random() * jugadores[jugadorJuega].getRaza().getVelocidad() + Constantes.MINIMO_MOV_JUGADOR);
		
		return movimientos;
	}
	
	
	public char getJugadorTurno() {
		boolean primeroCreado=false;
		int primerJugador=0;
		char simboloJugador;
		
		if(!primeroCreado) {
			primerJugador=(int)(Math.random() * numJugadores + 1);
			jugadorJuega=primerJugador;
			primeroCreado=true;
			simboloJugador=jugadores[primerJugador].getSimbolo();
		}else {
			jugadorJuega++;
			if(jugadorJuega > numJugadores) {
				jugadorJuega=0;
			}
			simboloJugador=jugadores[jugadorJuega].getSimbolo();
		}
		
		return simboloJugador;
	}

	public int obtenerCoordenada() {
		int posicion = (int) (Math.random() * 10);

		return posicion;
	}
	
	public boolean conflictoPozo() {
		boolean jugadorPasa=false;
		
		int puntuacionPozo=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getMagia()) + 1;
		int puntuacionJugador=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getMagia()) + 1;
		
		if(puntuacionJugador > puntuacionPozo) {
			jugadorPasa=true;
		}
		return jugadorPasa;
	}
	
	public boolean conflictoJugador(Jugador jugadorAtacar) {
		boolean jugadorGana=false;
		
		int puntuacionJugador=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getFuerza()) + 1;
		int puntuacionJugadorAtacar=(int) (Math. random() * jugadorAtacar.getRaza().getMagia()) + 1;
		
		if(puntuacionJugador > puntuacionJugadorAtacar) {
			if (jugadorAtacar.getNumPociones() > 0) {
				System.out.println("El jugador " + jugadores[jugadorJuega].getSimbolo() + " gana, pero el jugador " + jugadorAtacar.getSimbolo() + " tiene una poción, por lo que conserva su dinero.");
				jugadorAtacar.usarPocion();
			}
			else {
				if (jugadorAtacar.getCantidadDinero() > 0) {
					jugadorAtacar.perderDinero(jugadores[jugadorJuega]);
				}
				else {
					tablero[jugadorAtacar.getPosicionX()][jugadorAtacar.getPosicionY()] = null;
				}
			}
			jugadorGana=true;
		}
		else {
			
		}
		return jugadorGana;
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
