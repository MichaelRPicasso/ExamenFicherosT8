package partida_bolos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Main {

	static Tirada[] partidas = new Tirada[100];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		cargarDatos();

		operarMenu();

		guardarDatos();// AL SALIR DE LA APLICACION , AL TERMINAR EL EMTODO"OPERAR MENU" PASARIA POR
						// ESTA PARTE Y GUARDARIA LSO DATOS

	}

	private static void menu() {
		System.out.println("""
				1-TIRAR
				2-MAXIMA PUNTUACION
				3-MINIMA PUNTUACION
				4-MAYOR TOTAL
				5-SALIR
				""");

	}

	public static void operarMenu() {

		boolean flag = true;
		int opcion = 0;
		while (flag) {
			menu();
			try {
				opcion = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			switch (opcion) {
			case 1:
				tirar();
				break;
			case 2:
				maximaPuntuacion();
				break;
			case 3:
				minimaPuntuacion();
				break;
			case 4:
				mayorTotal();
				break;
			case 5:
				guardarGanador();

				System.out.println("Adios.");
				flag = false;
				break;
			default:
				System.err.println("Opcion introducida no valida");

			}

		}

	}

	public static void tirar() {
		System.out.println("Introduce el nombre del Jugador.");
		Tirada tirada = null;
		try {
			tirada = new Tirada(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < partidas.length; i++) {
			if (partidas[i] == null) {
				tirada.realizarTirada();
				partidas[i] = tirada;
				i = partidas.length;
			}
		}
	}

	public static void maximaPuntuacion() {
		int mayor = 0;

		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() > mayor)
					mayor = i.mejorLanzamiento();
			}
		}
		for (Tirada i : partidas) {
			if (i != null) {
				if (i.mejorLanzamiento() == mayor)
					System.out.println(i.getNombre() + " : " + i.mejorLanzamiento() + " Puntos.");
			}
		}

		// si hay varias iguales imprimo todas
	}

	public static void minimaPuntuacion() {

		// YA QUE EN LA CLASE TIRADA NO DICE EL ENUNCIADOQ UE SE CREE METODO PARA
		// COMPROBAR EL MENOS, LO TENEMOS QUE HACER TODO DESDE EL MAIN,
		// Y POR ELLO LA METODOLOGIA QUE USO ES DIFERENTE A LA DE "maximaPuntuacion()"

		int menor = 100;// PONGO LA PUNTUACION MAXIMA ALCANZABLE, YA QUE LO QUE SE ALMACENE SERA POR
						// FUERZA INFERIOR O IGUAL A ESTO

		for (Tirada i : partidas) {
			if (i != null) {

				for (int j = 0; j < i.getLanzamientos().length; j++) {
					if (i.getLanzamientos()[j] < menor)
						menor = i.getLanzamientos()[j];// SI ALGUN LANZAMIENTO NO LLEGA A "MENOR" MENOR PASA A TENER ESE
														// VALOR
				}
			}
		}

		for (Tirada i : partidas) {
			if (i != null) {

				for (int j = 0; j < i.getLanzamientos().length; j++) {
					if (i.getLanzamientos()[j] == menor)
						System.out.println(i.getNombre() + " : " + i.getLanzamientos()[j] + " Puntos.");
				}
			}
		}
	}

	public static void mayorTotal() {

		int mayor = 0;
		LocalDate fechaAntigua = LocalDate.now();

		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() > mayor)
					mayor = i.totalLanzamiento();
			}
		}

		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() == mayor) {

					if (i.getFecha().isBefore(fechaAntigua)) {
						fechaAntigua = i.getFecha();
					}
				}

			}
		}

		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() == mayor && i.getFecha().equals(fechaAntigua))
					System.out.println(i.getNombre() + " : " + i.totalLanzamiento() + " Puntos.");
			}
		}

	}

	// METODO QUE ALMACENA EL NOMBRE Y PUNTUACION DEL GANADOR
	public static void guardarGanador() {
		File ganador = new File("ganador.txt");

		if (!ganador.exists()) {
			try {
				ganador.createNewFile();
				System.out.println("\"ganador.txt\" creado correctamente.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int mayor = 0;
		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() > mayor)
					mayor = i.totalLanzamiento();
			}
		}
		for (Tirada i : partidas) {
			if (i != null) {
				if (i.totalLanzamiento() == mayor)

					try (FileWriter escritor = new FileWriter("ganador.txt")) {
						escritor.write(i.getNombre() + " : " + i.totalLanzamiento() + " Puntos.");// ESCRIBIMOS EN EL
																									// ARCHIVO
																									// PUNTUACION MAS
																									// ALTA QUE HAY Y SU
																									// RESPECTIVO
																									// JUGADOR
					} catch (IOException e) {

						e.printStackTrace();
					}

			}
		}

	}

	public static void cargarDatos() {

		try (ObjectInputStream aux = new ObjectInputStream(new FileInputStream("datos.dat"))) {
			partidas = (Tirada[]) aux.readObject();

		} catch (FileNotFoundException e) {// EXCEPCION QUE COMPRUEBA SI EL ARCHIVO EXISTE Y EN CASO NEGATIVO, LO CREA
			System.out.println("Archivo no encontrado");
			File archivo = new File("datos.dat");
			try {
				archivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (InvalidClassException e2) {
			e2.printStackTrace();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void guardarDatos() {

		try (ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
			aux.writeObject(partidas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
