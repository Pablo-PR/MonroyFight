package caracteristicasJuego;

public class Juego {

	private ElementosTablero tablero[][];
	private Jugador jugadores[];
	private int alto, ancho, numJugadores, contadorJugador, jugadorJuega, movimientosJugador;
	boolean finished, primerJugador;
	char ganadorJuego;

	
	public Juego(int alto, int ancho, int numJugadores) {
		this.alto = alto;
		this.ancho = ancho;
		this.numJugadores = numJugadores;
		contadorJugador = 0;
		jugadorJuega = 0;
		movimientosJugador = 0;
		finished = false;
		tablero = new ElementosTablero[alto][ancho];
		jugadores = new Jugador[numJugadores];
		primerJugador=false;
		ganadorJuego=' ';
	}

	
	public Jugador[] getJugadores() {
		return jugadores;
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

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public int getJugadorJuega() {
		return jugadorJuega;
	}

	public void setJugadorJuega(int jugadorJuega) {
		this.jugadorJuega = jugadorJuega;
	}

	public int getMovimientosJugador() {
		return movimientosJugador;
	}
	
	public void setMovimientosJugador(int movimientosJugador) {
		this.movimientosJugador = movimientosJugador;
	}
	
	public char getGanador() {
		return ganadorJuego;
	}

	/**
	 * Método al que se le pasa el tipo de jugador (enumerado) y crea el objeto jugador dentro del array jugadores.
	 * @param tipo
	 */
	public void crearJugador(TipoPersonaje tipo) {
		jugadores[contadorJugador] = new Jugador(tipo, obtenerCoordenada(), obtenerCoordenada(), Constantes.ID_JUGADORES[contadorJugador]);

		contadorJugador++;
	}

	/**
	 * Método que controla los movimientos de los jugadores en el tablero.
	 * @param direccion
	 */
	public void moverJugador(char direccion) {
		int posicionX = jugadores[jugadorJuega].getPosicionX();
		int posicionY = jugadores[jugadorJuega].getPosicionY();
		
		switch (direccion) {
		case 'N':{
			int posicionFuturaX = posicionX - 1;
			int posicionFuturaY = posicionY;
			
			tratarMovimientos(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
			
			break;
		}
		case 'S':{
			int posicionFuturaX = posicionX + 1;
			int posicionFuturaY = posicionY;
			
			tratarMovimientos(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
			break;
		}
		case 'E':{
			int posicionFuturaX = posicionX;
			int posicionFuturaY = posicionY + 1;
			
			tratarMovimientos(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
			break;
		}
		case 'O':{
			int posicionFuturaX = posicionX;
			int posicionFuturaY = posicionY - 1;
			
			tratarMovimientos(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
			break;
		}
		}
	}

	/**
	 * Método que ejecuta una acción u otra dependiendo de la casilla a la que vaya a avanzar el jugador.
	 * @param posicionX
	 * @param posicionY
	 * @param posicionFuturaX
	 * @param posicionFuturaY
	 */
	public void tratarMovimientos(int posicionX, int posicionY, int posicionFuturaX, int posicionFuturaY) {
		if (posicionFuturaX == Constantes.ANCHO_TABLERO) {
			posicionFuturaX=0;
		}
		
		if (posicionFuturaX == -1) {
			posicionFuturaX=9;
		}
		
		if (posicionFuturaY == Constantes.ALTO_TABLERO) {
			posicionFuturaY=0;
		}
		
		if (posicionFuturaY == -1) {
			posicionFuturaY=9;
		}
		
		if (comprobarHueco(posicionFuturaX, posicionFuturaY)) {
			avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
		}
		else {
			if (tablero[posicionFuturaX][posicionFuturaY] instanceof Jugador) {
				Jugador jugadorAtacar;
				jugadorAtacar=(Jugador) tablero[posicionFuturaX][posicionFuturaY];
				
				if(conflictoJugador(jugadorAtacar)) {
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
				}
				else {
					movimientosJugador=0;
				}
			}
			if (tablero[posicionFuturaX][posicionFuturaY] instanceof Gema) {
				avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
				jugadores[jugadorJuega].recogerGema();
			}
			if (tablero[posicionFuturaX][posicionFuturaY] instanceof Dinero) {
				avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
				jugadores[jugadorJuega].recogerDinero();
				if(jugadores[jugadorJuega].getCantidadDinero() == Constantes.NUM_DINERO) {
					finished=true;
					ganadorJuego=jugadores[jugadorJuega].getSimbolo();
				}
			}
			if (tablero[posicionFuturaX][posicionFuturaY] instanceof Pociones) {
				avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
				jugadores[jugadorJuega].recogerPocion();
			}
			if (tablero[posicionFuturaX][posicionFuturaY] instanceof Pozo) {
				if (conflictoPozo()) {
					avanzarCasilla(posicionX, posicionY, posicionFuturaX, posicionFuturaY);
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
		}
	}

	/**
	 * Método que actualiza la posición antigua en la que estaba el jugador, poniéndola a null, y asignándole la nueva posición en el tablero.
	 * @param posicionX
	 * @param posicionY
	 * @param posicionFuturaX
	 * @param posicionFuturaY
	 */
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

	/**
	 * Método que imprime por pantalla el tablero con todos los elementos y jugadores.
	 */
	public void imprimirTablero() {
		for (int x = 0; x < tablero.length; x++) {
            System.out.println(".........................................");
            System.out.print("| ");

            for (int y = 0; y < tablero[x].length; y++) {
            	if(comprobarHueco(x,y)) {
            		System.out.print(' ');
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

	/**
	 * Método que introduce todos los elementos del tablero en la matriz.
	 */
	public void introducirElementosTablero() {
		introducirElementos(Constantes.NUM_ROCAS, Constantes.SIMBOLO_ROCA);
		introducirElementos(Constantes.NUM_GEMAS, Constantes.SIMBOLO_GEMA);
		introducirElementos(Constantes.NUM_POZOS, Constantes.SIMBOLO_POZO);
		introducirElementos(Constantes.NUM_POCIONES, Constantes.SIMBOLO_POCION);
		introducirElementos(Constantes.NUM_DINERO, Constantes.SIMBOLO_DINERO);
		introducirJugadores();
	}

	/**
	 * Método que controla la correcta inserción de los jugadores en el tablero.
	 */
	public void introducirJugadores() {
		contadorJugador=0;
		int k=0;
		
		do {
			if(comprobarHueco(jugadores[contadorJugador].getPosicionX(), jugadores[contadorJugador].getPosicionY())) {
				tablero[jugadores[contadorJugador].getPosicionX()][jugadores[contadorJugador].getPosicionY()] = jugadores[contadorJugador];
				
				contadorJugador++;
				k++;
			}else {
				jugadores[contadorJugador].setPosicionX(obtenerCoordenada());
				jugadores[contadorJugador].setPosicionY(obtenerCoordenada());
			}
		} while (k < numJugadores);
	}

	/**
	 * Método que controla la correcta inserción de los objetos y obstáculos en el tablero.
	 * @param cantidad
	 * @param simboloElemento
	 */
	public void introducirElementos(int cantidad, char simboloElemento) {
		ElementosTablero elementoAIntroducir=null;
		boolean termina;
		
		for (int i = 0; i < cantidad; i++) {
			
			do {
				termina=false;
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

	/**
	 * Método que comprueba si una posición de la matriz está vacía (null).
	 * @param x
	 * @param y
	 */
	public boolean comprobarHueco(int x, int y) {
		boolean vacio=false;
        	
		if (tablero[x][y]==null) {
			vacio=true;
		}
		
		return vacio;
	}
	
	/**
	 * Método que simula la tirada de un dado, generando un número aleatorio entre 1 y la velocidad máxima del jugador.
	 */
	public int tirarDado() {
		movimientosJugador = (int) (Math.random() * jugadores[jugadorJuega].getRaza().getVelocidad() + Constantes.MINIMO_MOV_JUGADOR);
		
		return movimientosJugador;
	}
	
	/**
	 * Método que elige al primer jugador aleatoriamente y controla el orden de los turnos, devolviendo el símbolo del jugador al que le toca.
	 */
	public char getJugadorTurno() {
		int jugadorEmpieza=0;
		char simboloJugador;
		
		if(!primerJugador) {
			jugadorEmpieza=(int)(Math.random() * numJugadores);
			jugadorJuega=jugadorEmpieza;
			primerJugador=true;
		}
		
		simboloJugador=jugadores[jugadorJuega].getSimbolo();
		
		return simboloJugador;
	}

	/**
	 * Método que devuelve un número aleatorio entre 0 y 9, usado para obtener posiciones aleatorias en la matriz tablero.
	 */
	public int obtenerCoordenada() {
		int posicion = (int) (Math.random() * 10);

		return posicion;
	}
	
	/**
	 * Método que controla la batalla de un jugador con un pozo, generando dos números aleatorios comprendidos entre 1 y
	 * la magia máxima del jugador, la cual también es la magia máxima del pozo.
	 */
	public boolean conflictoPozo() {
		boolean jugadorPasa=false;
		int puntuacionPozo=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getMagia()) + 1;
		int puntuacionJugador=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getMagia()) + 1;
		
		System.out.println("La magia del jugador es " + puntuacionJugador + "\nLa magia del pozo es " + puntuacionPozo);
		
		if(puntuacionJugador > puntuacionPozo) {
			System.out.println("Ha ganado el jugador, puede pasar");
			jugadorPasa=true;
		}
		else {
			System.out.println("El pozo ha ganado, no puedes pasar y pierdes tus movimientos");
			movimientosJugador=0;
		}
		
		return jugadorPasa;
	}
	
	/**
	 * Método que controla la batalla entre jugadores, generando un número aleatorio para cada jugador comprendido entre 1 y
	 * su fuerza máxima. Se devuelve un boolean para controlar que un jugador solo avance si ha ganado la batalla.
	 * @param jugadorAtacar
	 */
	public boolean conflictoJugador(Jugador jugadorAtacar) {
		boolean jugadorPasa=false;
		int puntuacionJugador=0;
		int puntuacionJugadorAtacar=0;
		
		do {
			puntuacionJugador=(int) (Math. random() * jugadores[jugadorJuega].getRaza().getFuerza()) + 1;
			puntuacionJugadorAtacar=(int) (Math. random() * jugadorAtacar.getRaza().getFuerza()) + 1;
		}while(puntuacionJugador==puntuacionJugadorAtacar);
		
		System.out.println("El jugador " + jugadores[jugadorJuega].getSimbolo() + " ha sacado " + puntuacionJugador + "\nEl jugador atacado (" + jugadorAtacar.getSimbolo() + ") ha sacado " + puntuacionJugadorAtacar);
		
		if(puntuacionJugador > puntuacionJugadorAtacar) {
			Jugador ganador = jugadores[jugadorJuega];
			Jugador perdedor = jugadorAtacar;
			
			jugadorPasa = combateJugadores(perdedor, ganador);
		}
		else {
			Jugador ganador = jugadorAtacar;
			Jugador perdedor = jugadores[jugadorJuega];
			
			jugadorPasa = combateJugadores(perdedor, ganador);
		}
		
		return jugadorPasa;
	}

	/**
	 * Método que gestiona las consecuencias del combate entre jugadores dependiendo del número de pociones y de monedas.
	 * Si muere algún jugador, es eliminado del tablero.
	 * @param perdedor
	 * @param ganador
	 */
	public boolean combateJugadores(Jugador perdedor, Jugador ganador) {
		boolean jugadorPasa = false;
		boolean jugadorEliminado = false;

		if (perdedor.getNumPociones() > 0) {
			System.out.println("El jugador " + ganador.getSimbolo() + " gana, pero el jugador " + perdedor.getSimbolo()
					+ " tiene una poción, por lo que conserva su dinero y no muere.");
			perdedor.usarPocion();
		} else {
			if (perdedor.getCantidadDinero() > 0) {
				perdedor.perderDinero(ganador);
				System.out.println("El jugador " + ganador.getSimbolo() + " se queda con el dinero del jugador " + perdedor.getSimbolo());
				
				if (ganador.getCantidadDinero() == Constantes.NUM_DINERO) {
					finished = true;
					ganadorJuego = ganador.getSimbolo();
				}
			} else {
				tablero[perdedor.getPosicionX()][perdedor.getPosicionY()] = null;
				numJugadores--;
				movimientosJugador=0;
				
				System.out.println("El jugador " + perdedor.getSimbolo() + " ha muerto");

				for (int i = 0; i < jugadores.length && !jugadorEliminado; i++) {
					if (perdedor.getSimbolo() == jugadores[i].getSimbolo()) {
						jugadores[i] = null;
						jugadorEliminado = true;

						for (int j = i; j < numJugadores; j++) {
							jugadores[j] = jugadores[j + 1];
						}

					}
				}
				
				if (ganador.equals(jugadores[jugadorJuega])) {
					jugadorPasa = true;
				}
			}

			if (numJugadores == 1) {
				finished = true;
				ganadorJuego = ganador.getSimbolo();
			}
		}

		return jugadorPasa;
	}

	/**
	 * Método que imprime por pantalla los símbolos de los jugadores del tablero.
	 */
	public String nombresJugadores() {
		StringBuilder sbJugadores = new StringBuilder();
		
		for (int i = 0; i < numJugadores; i++) {
			sbJugadores.append("\nEl jugador numero " + (i+1) + " es el caracter: " + jugadores[i].getSimbolo());
		}
		
		return sbJugadores.toString();
	}

	/**
	 * Método que imprime por pantalla los objetos que posee cada jugador.
	 */
	public String valoresJugadores() {
		StringBuilder sbValoresJugadores = new StringBuilder();
		
		for (int i = 0; i < numJugadores; i++) {
			sbValoresJugadores.append("\nJugador " + jugadores[i].getSimbolo() + ":\n"
					+ "Gemas: " + jugadores[i].getNumGemas() + " Dinero: " + jugadores[i].getCantidadDinero() + " Pociones: " + jugadores[i].getNumPociones() + "\n");
			
		}
		
		return sbValoresJugadores.toString();
	}

	/**
	 * Método que pasa el turno de un jugador.
	 */
	public void proximoJugador() {
		jugadorJuega++;
		
		if(jugadorJuega == numJugadores) {
			jugadorJuega=0;
		}
	}

	/**
	 * Método que disminuye los movimientos totales de un jugador en su turno.
	 */
	public void restarMovimiento() {
		movimientosJugador--;		
	}
}