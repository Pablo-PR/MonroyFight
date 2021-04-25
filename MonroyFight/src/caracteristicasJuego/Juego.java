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

	public int getMovimientosJugador() {
		return movimientosJugador;
	}
	
	public void setMovimientosJugador(int movimientosJugador) {
		this.movimientosJugador = movimientosJugador;
	}

	public void crearJugador(TipoPersonaje tipo) {
		jugadores[contadorJugador] = new Jugador(tipo, obtenerCoordenada(), obtenerCoordenada(), Constantes.ID_JUGADORES[contadorJugador]);

		contadorJugador++;
	}

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

	public void introducirElementosTablero() {
	
		introducirElementos(Constantes.NUM_ROCAS, Constantes.SIMBOLO_ROCA);
		introducirElementos(Constantes.NUM_GEMAS, Constantes.SIMBOLO_GEMA);
		introducirElementos(Constantes.NUM_POZOS, Constantes.SIMBOLO_POZO);
		introducirElementos(Constantes.NUM_POCIONES, Constantes.SIMBOLO_POCION);
		introducirElementos(Constantes.NUM_DINERO, Constantes.SIMBOLO_DINERO);
		introducirJugadores();
	}

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

	public boolean comprobarHueco(int x, int y) {
		boolean vacio=false;
        	
		if (tablero[x][y]==null) {
			vacio=true;
		}
		
		return vacio;
	}
	
	public int tirarDado() {
		movimientosJugador = (int) (Math.random() * jugadores[jugadorJuega].getRaza().getVelocidad() + Constantes.MINIMO_MOV_JUGADOR);
		
		return movimientosJugador;
	}
	
	
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

	public int obtenerCoordenada() {
		int posicion = (int) (Math.random() * 10);

		return posicion;
	}
	
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

	public boolean combateJugadores(Jugador perdedor, Jugador ganador) {
		boolean jugadorPasa=false;
		if (perdedor.getNumPociones() > 0) {
			System.out.println("El jugador " + ganador.getSimbolo() + " gana, pero el jugador " + perdedor.getSimbolo() + " tiene una poci�n, por lo que conserva su dinero y no muere.");
			perdedor.usarPocion();
		}
		else {
			if (perdedor.getCantidadDinero() > 0) {
				perdedor.perderDinero(ganador);
				System.out.println("El jugador " + ganador.getSimbolo() + " se queda con el dinero del jugador " + perdedor.getSimbolo());
				if(ganador.getCantidadDinero() == Constantes.NUM_DINERO) {
					finished=true;
					ganadorJuego=ganador.getSimbolo();
				}
			}
			else {
				tablero[perdedor.getPosicionX()][perdedor.getPosicionY()] = null;
				System.out.println("El jugador " + perdedor.getSimbolo() + " ha muerto");
				numJugadores--;
				if(numJugadores==1) {
					finished=true;
					ganadorJuego=ganador.getSimbolo();
				}
				jugadorPasa=true;
			}
		}
		return jugadorPasa;
	}

	public String nombresJugadores() {
		StringBuilder sbJugadores = new StringBuilder();
		
		for (int i = 0; i < numJugadores; i++) {
			sbJugadores.append("\nEl jugador numero " + (i+1) + " es el caracter: " + jugadores[i].getSimbolo());
		}
		
		return sbJugadores.toString();
	}

	public String valoresJugadores() {
		StringBuilder sbValoresJugadores = new StringBuilder();
		
		for (int i = 0; i < numJugadores; i++) {
			sbValoresJugadores.append("\nJugador " + jugadores[i].getSimbolo() + ":\n"
					+ "Gemas: " + jugadores[i].getNumGemas() + " Dinero: " + jugadores[i].getCantidadDinero() + " Pociones: " + jugadores[i].getNumPociones() + "\n");
		}
		
		return sbValoresJugadores.toString();
	}

	public void proximoJugador() {
		jugadorJuega++;
		
		if(jugadorJuega == numJugadores) {
			jugadorJuega=0;
		}
	}

	public char getGanador() {
		return ganadorJuego;
	}

	public void restarMovimiento() {
		movimientosJugador--;		
	}
}