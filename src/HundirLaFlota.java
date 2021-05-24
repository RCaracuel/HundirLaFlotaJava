import java.util.Scanner;

public class HundirLaFlota {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Tablero usuario = new Tablero();
		Tablero pc = new Tablero();
		Tablero aux = new Tablero();

		int puntosJugador = 24;
		int puntosPc = 24;
		String opcion;

		
		usuario.llenarTablero();
		usuario.pintarTablero();

		System.out.println();
		pc.llenarTablero();
		pc.pintarTablero();

		System.out.println();
		aux.pintarTablero();

		while (puntosJugador > 0 && puntosPc > 0) {
			
			System.out.print("Introduzca unas coordenadas EJ(1-3):");
			opcion = teclado.nextLine();
			puntosJugador=aux.tirada(opcion, aux, pc, puntosJugador);
			System.out.println();
			aux.pintarTablero();
			System.out.println("Te quedan "+puntosJugador+" puntos");
			
			//Tengo que hacer la tirada random del pc

		}

//		aux.tirada("2-6", aux, pc);
//
//		System.out.println();
//		aux.pintarTablero(aux);
//
//		aux.tirada("5-3", aux, pc);
//
//		System.out.println();
//		aux.pintarTablero(aux);
//
//		aux.tirada("8-2", aux, pc);
//
//		System.out.println();
//		aux.pintarTablero(aux);

		
	}
}
