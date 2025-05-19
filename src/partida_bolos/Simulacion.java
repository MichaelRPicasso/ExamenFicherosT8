package partida_bolos;

import java.io.Serializable;
import java.util.Random;

public class Simulacion implements Serializable {
	private static final long SerialVersionUID = 1L;

	// simualar lanzaminento de bolos rancom 0-100

	private int puntuacion;
	private Random rn;// DECLARAMOS EL ALEATORIZADOR

	public Simulacion() {
		this.rn = new Random();
	}

	public int devolverPuntuacion() {
		puntuacion = rn.nextInt(101);// GENERA NUMERO ALEATORIO ENTRE 0Y100
		return puntuacion;

	}

}
