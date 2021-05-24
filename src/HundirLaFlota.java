import java.util.Scanner;

public class HundirLaFlota {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Tablero usuario = new Tablero();
		Tablero pc = new Tablero();
		Tablero aux = new Tablero();
		Tablero auxPc = new Tablero();

		int puntosJugador = 24;
		int puntosPc = 24;
		String opcion;

		usuario.llenarTablero();
//		usuario.pintarTablero();

		System.out.println();
		pc.llenarTablero();
//		pc.pintarTablero();

		System.out.println();
		aux.pintarTablero();
		auxPc.pintarTablero();

		while (puntosJugador > 0 && puntosPc > 0) {

			System.out.print("Introduzca unas coordenadas EJ(1-3):");
			opcion = teclado.nextLine();
			puntosJugador = aux.tirada(opcion, aux, pc, puntosJugador);
			System.out.println();
			System.out.println("---Tablero DEL USUARIO ---");
			aux.pintarTablero();
			System.out.println("Te quedan " + puntosJugador + " puntos");

			// Tengo que hacer la tirada random del pc
			System.out.println("Turno del pc");
			int tiradaPc1 = pc.random(0, 9);
			int tiradaPc2 = pc.random(0, 9);

			String tirada = String.valueOf(tiradaPc1);
			tirada += "-";
			tirada += String.valueOf(tiradaPc2);

			System.out.println("Titada del pc:" + tirada);
			puntosPc = auxPc.tirada(tirada, auxPc, usuario, puntosPc);
			System.out.println();
			System.out.println("---Tablero DEL PC ---");
			auxPc.pintarTablero();
			System.out.println("Al PC le quedan " + puntosPc + " puntos");
		}

		System.out.println("El juego ha terminado!!");
		if (puntosJugador == 0)
			System.out.println("Gana el PC");
		else
			System.out.println("Gana el Jugador");

	}
}
