import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Tablero {

	final static char AGUA_NO_TOCADA = '.';
	final static char AGUA = 'A';
	final static char TOCADO = 'X';

	public char[][] tablero = new char[10][10];
	Random rand = new Random();

	public Tablero() {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = '~';
			}
		}

	}

	public static int random(int min, int max) {
		return Math.round(min + Math.round(Math.random() * max));
	}

	public void pintarTablero() {
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[0].length; j++) {
				System.out.print(this.tablero[i][j]);
			}
			System.out.println();
		}
	}

	public int tirada(String coordenadas, Tablero usuarioAux, Tablero pc, int puntosUsuario) {
		String[] coor = coordenadas.split("-");
		int fila = Integer.parseInt(coor[0]);
		int columna = Integer.parseInt(coor[1]);

		if (pc.tablero[fila][columna] != '~') {
			usuarioAux.tablero[fila][columna] = TOCADO;
			
		} else {
			usuarioAux.tablero[fila][columna] = AGUA;
			puntosUsuario--;
		}
		
		return puntosUsuario;

	}

	public void llenarTablero() {

		int[] cantidadBarcos = { 3, 3, 2, 1, 1 };
		char[] caracter = { '1', '2', '3', '4', '5' };
		int[] numCasillas = { 1, 2, 3, 4, 5 };
		int fin = 1;

		for (int i = 0; i < numCasillas.length; i++) {
			while (cantidadBarcos[i] != 0) {

				int[] pintando = pintarBarcos(cantidadBarcos[i], caracter[i], numCasillas[i]);

				fin = pintando[0];
				cantidadBarcos[i] = pintando[1];

			}
		}

	}

	// Método que recibe la cantidad de barcos que tiene que pintar, el dígito que
	// tiene que pintar
	// y el número de casillas que ocupa el barco
	// devuelvue un array con dos int, el primero indica true o false (0-1) y el
	// siguiente
	// el número de barcos que faltan por pintar
	public int[] pintarBarcos(int cantidad, char digito, int barco) {

		// declaramos un boolean para la orientación
		boolean orientacion;
		// este es el array de dos posiciones que vamos a devolver
		int[] fallo = new int[2];
		// el número de barcos que faltan por pintar
		int resto = cantidad;

		// creamos dos variables para la fila y la columna
		int fila;
		int columna;
		fallo[0] = 1;
		fallo[1] = resto;
		boolean pintado = false;

		// este for va de 0 a la cantidad de barcos que tenemos que pintar
		for (int i = 0; i < cantidad; i++) {

			// instanciamos la orientación de forma random, la fila y la columna también
			orientacion = random(0, 9) < 5 ? false : true;
			fila = random(0, 9);
			columna = random(0, 9);
//			System.out.println("fila" + fila);
//			System.out.println("columna" + fila);

			// si la posición está vacía
			if (this.tablero[fila][columna] == '~') {
//				System.out.println("entro a pintar");
				// y la orientación es horizontal comprobamos si cabe el barco, es decir, si la
				// columna
				// más el número de columnas que voy a usar no supera 10
				if (orientacion && columna + barco <= 10) {
//					System.out.println("opcion 1");
					// en ese caso el tope será la columna de inicio más el número de casillas que
					// ocupa el barco
					int tope = columna + barco;
					// recorremos un bucle hasta el tope
					for (int j = columna; j < tope; j++) {
//						System.out.println("opcion 2");
						// si esa casilla está llena devolvemos un error y el resto de barcos que
						// tenemos que pintar
						if (this.tablero[fila][j] != ' ') {
//							System.out.println("opcion 3");
							fallo[0] = 1;
							fallo[1] = resto;
							pintado = false;
//							System.out.println("El resto es " + resto);
							return fallo;
//					
						}
					}
					for (int j = columna; j < tope; j++) {
						// anteriormente comprobamos que esta casilla y las siguientes que necesitemos
						// están vacías así que comenzamos a pintarlo
//						System.out.println("lo he pintado en opcion 4");
//						System.out.println(cantidad);
						this.tablero[fila][j] = digito;
						fallo[0] = 0;
						pintado = true;
//						System.out.println("El resto es " + resto);

					}
					// hacemos lo mismo pero si esta vez la orientación es vertical
				} else if (!orientacion && fila + barco <= 10) {
//					System.out.println("opcion 5");
					// el tope será la fila más la cantidad de casillas que ocupa el barco
					int tope = fila + barco;
					for (int j = fila; j < tope; j++) {
//						System.out.println("opcion 6");
						// si no está vacio en algun momento devolvemos un error
						if (this.tablero[j][columna] != '~') {
//							System.out.println("opcion 7");
							fallo[0] = 1;
							fallo[1] = resto;
							pintado = false;
//							System.out.println("El resto es " + resto);
							return fallo;

						}
					}
					// volvemos a recorrer las filas sabiendo que están vacías y rellenamos
					for (int j = fila; j < tope; j++) {
//						System.out.println("opcion 8");
						this.tablero[j][columna] = digito;
						pintado = true;
						fallo[0] = 0;

//						System.out.println("El resto es " + resto);
					}
					// Si desde la primera vez encontramos una posición ocupada por algo devolvemos
					// error
				} else {
//					System.out.println("opcion 9");
					fallo[0] = 1;
					fallo[1] = resto;
//					System.out.println("El resto es " + resto);
					return fallo;
				}
			}

			if (pintado)
				resto--;
		}

		// Cuando acabe todo devolveremos 0 como true y el resto debería ser 0 también
//		System.out.println("final");
//		System.out.println("El resto es " + resto);
		fallo[1] = resto;
		return fallo;

	}

}
