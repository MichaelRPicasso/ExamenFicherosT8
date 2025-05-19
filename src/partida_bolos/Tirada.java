package partida_bolos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Tirada implements Serializable {

	private static final long SerialVersionUID = 1L;

	private String nombre;
	private int[] lanzamientos;
	private LocalDate fecha;

	public Tirada(String nombre) {

		this.nombre = nombre;
		this.lanzamientos = new int[3];
		this.fecha = LocalDate.now();
	}

	public int[] getLanzamientos() {
		return lanzamientos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void realizarTirada() {
		Simulacion simula = new Simulacion();// INSTANCIAMOS EL SIMULADOR DE TIRADA PARA EMPLEARLO
		for (int i = 0; i < this.lanzamientos.length; i++) {
			this.lanzamientos[i] = simula.devolverPuntuacion();// LLAMAMOS AL METODO QUE NOS DEVUELVE EL NUMERO GENERADO
																// Y LO ALMACENAMOS EN EL ARRAY DE 3 LANZAMIENTOS
			System.out.println("Tirada " + i + " = " + this.lanzamientos[i]);// MOSTRAMOS EL RESULTADO
		}
		System.out.println("-------------");

	}

	public int mejorLanzamiento() {
		int mejor = 0;

		for (int i : lanzamientos) {
			if (i > mejor)
				mejor = i;// SI ALGUN LANZAMIENTO SUPERA A "MEJOR" MEJOR PASA A TENER ESE VALOR
		}

		return mejor;// DEVUELVE EL MEJOR LANZAMIENTO
	}

	public int totalLanzamiento() {
		int total = 0;

		for (int i : lanzamientos) {
			total += i;// SUMA A CADA VUELTA EL CONTENIDO DEL ARRAY
		}

		return total;// DEVUELVE EL MEJOR LANZAMIENTO
	}

	// al realizar la tirada, el resultado hay que almacenarlo en el array si o si

}
