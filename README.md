# ExamenFicherosT8
ExamenMiguelRoblesPicassoProgramacionT8

Hoy va a tocar jugar a los bolos, aunque no será necesario conocer las reglas del juego original vamos a implementar nuestra propia competición. Crearemos una aplicación en Java que registre las puntuaciones de los jugadores en una partida de bolos donde se podrán realizar tres lanzamientos.

Implementaremos la clase Simulación cuya función será simular un lanzamiento de bolos generando un número aleatorio entre 0 y 100, esta clase además tendrá un método devolverPuntuacion() que retorne la puntuación. (1 puntos)

Implementaremos la clase Tirada (2 puntos) que almacenará los tres lanzamientos que realizará cada jugador gracias a la clase Simulación, el nombre de cada tirador y la fecha. Además esta clase contendrá los métodos:

realizarTirada() que mostrará el valor de los tres lanzamientos.
mejorLanzamiento() que devolverá el lanzamiento con mayor puntuación de la tirada.
totalLanzamientos() que devolverá la suma de los tres lanzamientos de la tirada.
Crearemos una clase principal donde implementaremos un array de hasta 100 partidas en la que se deberá realizar las siguientes tareas:

Tirar(): Realizar una tirada. (1 punto)
maximaPuntuacion(): Mostrar la tirada que contenga el lanzamiento más alto (si hay más de una se imprimirán todas ellas). (0.5 puntos)
minimaPuntuacion(): Mostrar la tirada que contenga el lanzamiento más bajo (si hay más de una se imprimirán todas ellas). (0.5 puntos)
mayorTotal(): La tirada con una mayor totalización en el cómputo de los tres lanzamientos; si existiera más de una se imprimirá solo la que tenga la fecha de realización más antigua. (0.5 puntos)
Salir de la aplicación
Al entrar en la aplicación se debe de comprobar si existe un fichero datos.dat y si es así recuperaremos los datos de las partidas previas. (1.5 puntos)

Antes de salir se debe seleccionar la partida con la mayor puntuación y se almacenará el nombre y la puntuación de la partida en un fichero de texto llamado ganador.txt. (1.5 puntos)

Al salir se deben de guardar los datos del array en el estado en el que se encuentra. (1.5 puntos)

Nota: En este ejercicio se deberá utilizar exclusivamente las variables y métodos indicados explícitamente en el enunciado. Limita tu implementación a los atributos y métodos especificados.
