package uf2examen;

import java.util.Scanner;

public class TresEnLinia {
	
	final static int MAX = 3;
	final static int MAXJUG = 2;
	final static int TAULES = 9;

	static Scanner reader = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char resp;
		do {
			System.out.println("Benvinguts al tren en linia!");
			jugar();
			System.out.println("Voleu continuar jugant? ");
			resp = obtenirResp();
		} while (resp == 's' || resp == 'S');
	}

	public static void jugar() {
		char[][]tauler = new char [MAX][MAX];
		int torn;
		int empat=0;
		boolean correcte = false;
		boolean guanyador = false;
		
		inicialitzar(tauler);
		
		torn=1;
		
		do {
			mostrar(tauler);
			correcte = tiradaJugador(tauler, torn);
			torn = obtenirJugador(torn, correcte);
			guanyador = comprovar(tauler);
			
			if (!guanyador && correcte)
				empat++;
		} while (empat < TAULES && !guanyador);
		mostrar(tauler);
		finalitzarPartida(guanyador, empat, torn);
	}

	
	public static void finalitzarPartida(boolean guanyador, int empat, int torn) {
		if (guanyador && torn==1)
			System.out.println("Ha guanyat el jugador 2!");
		else if (guanyador && torn==2)
			System.out.println("Ha guanyat el jugador 1!");
		else if (empat==TAULES)
			System.out.println("Empat! ningú dels 2 jugadors a pogut fer el 3 en línia...");
	}

	public static boolean comprovar(char[][] tauler) {
		boolean guanyador = false;
		//files
		if ((tauler[0][0]=='X' && tauler[0][1]=='X' && tauler[0][2]=='X') || (tauler[0][0]=='O' && tauler[0][1]=='O' && tauler[0][2]=='O')) 
			guanyador=true;
		else if ((tauler[1][0]=='X' && tauler[1][1]=='X' && tauler[1][2]=='X') || (tauler[1][0]=='O' && tauler[1][1]=='O' && tauler[1][2]=='O')) 
			guanyador=true;
		else if ((tauler[2][0]=='X' && tauler[2][1]=='X' && tauler[2][2]=='X') || (tauler[2][0]=='O' && tauler[2][1]=='O' && tauler[2][2]=='O')) 
			guanyador=true;
		//columnes
		else if ((tauler[0][0]=='X' && tauler[1][0]=='X' && tauler[2][0]=='X') || (tauler[0][0]=='O' && tauler[1][0]=='O' && tauler[2][0]=='O')) 
			guanyador=true;
		else if  ((tauler[0][1]=='X' && tauler[1][1]=='X' && tauler[2][1]=='X') || (tauler[0][1]=='O' && tauler[1][1]=='O' && tauler[2][1]=='O')) 
			guanyador=true;
		else if  ((tauler[0][2]=='X' && tauler[1][2]=='X' && tauler[2][2]=='X') || (tauler[0][2]=='O' && tauler[1][2]=='O' && tauler[2][2]=='O')) 
			guanyador=true;
		//diagonals
		else if  ((tauler[0][0]=='X' && tauler[1][1]=='X' && tauler[2][2]=='X') || (tauler[0][0]=='O' && tauler[1][1]=='O' && tauler[2][2]=='O')) 
			guanyador=true;
		else if ((tauler[0][2]=='X' && tauler[1][1]=='X' && tauler[2][0]=='X') || (tauler[0][2]=='O' && tauler[1][1]=='O' && tauler[2][0]=='O')) 
			guanyador=true;
		
		return guanyador;
	}

	public static boolean tiradaJugador(char[][] tauler, int torn) {
		int [] coordenades = new int [2];
		boolean correcte = false;
		
		obtenirNum(coordenades);
		
		if (tauler[coordenades[0]][coordenades[1]]=='X' || tauler[coordenades[0]][coordenades[1]]=='O') {
			System.out.println("Casella ja ocupada!");
			correcte = false;
		}
		else if (torn==1) {
			tauler[coordenades[0]][coordenades[1]]='X';
			correcte = true;
		}
		else {
			tauler[coordenades[0]][coordenades[1]]='O';
			correcte = true;
		}
		return correcte;
	}

	public static void obtenirNum(int[] coordenades) {
		boolean dins = false;
		boolean num = false;
		do {
			while (!num) {
				try {
					System.out.println("Posa una fila: ");
					coordenades[0]=reader.nextInt();
					System.out.println("Posa una columna: ");
					coordenades[1]=reader.nextInt();
					num = true;
				}
				catch (Exception e) {
					System.out.println("ERROR! Posa un número enter.");
					reader.nextLine();
					num = false;
				}
			}
		if (coordenades[0]>=0 && coordenades[0]<=2 && coordenades[1]>=0 && coordenades[1]<=2)
			dins=true;
		else
			System.out.println("Has posat una/dues coordenades fora del rang (0-2).");
		} while (!dins);	
	}

	public static void mostrar(char[][] tauler) {
		int i,j;
		System.out.println("  012");
		for (i=0;i<MAX;i++) {
			System.out.print(i+" ");
			for (j=0;j<MAX;j++) {
				System.out.print(tauler[i][j]);	
			}
			System.out.println();
		}
	}

	public static int obtenirJugador(int torn, boolean correcte) {
		if (correcte)
			torn = (torn % MAXJUG) + 1;
		return torn;
	}

	public static void inicialitzar(char[][] tauler) {
		int i,j;
		for (i=0;i<MAX;i++) {
			for (j=0;j<MAX;j++) {
				tauler[i][j]=' ';
			}
		}
	}

	public static char obtenirResp() {
		char resp;
		resp = reader.next().charAt(0);
		return resp;
	}
}
