package mx.unam.esteganografia;

import java.util.Arrays;

/**
 * Clase que ayuda en la correcta impresión del resumen de las pruebas
 * unitarias.
 *
 * Implementa dos métodos que devuelven objetos que pueden ser concatenados a un
 * StringBuilder.
 *
 */
public class Impresora {

	/**
	 * Sirve para crear cadenas de un solo símbolo de longitud dada.
	 */
	private char arreglo[];

	/**
	 * Constructor.
	 *
	 * @param size int Representa la longitud de las líneas a devolver.
	 */
	public Impresora(int tamaño) {
		this.arreglo = new char[tamaño];
	}

	/**
	 * Devuelve un arreglo con la longitud impuesta por el constructor que solo
	 * contiene el símbolo pasado.
	 *
	 * @param symbol char Carácter con el que se va a rellenar el arreglo.
	 *
	 * @return char[] Arreglo que solo contiene al char symbol.
	 */
	public char[] divisor(char simbolo) {
		Arrays.fill(arreglo, simbolo);

		return arreglo;
	}

	/**
	 * Devuelve una cadena de longitud impuesta por el constructor la cual del
	 * lado izquierdo contiene a la primera cadena pasada y del lado derecho
	 * contiene a la segunda cadena pasada. En medio de ambas cadena existe una
	 * cadena de espacios en blanco de longitud exacta para alcanzar la longitud
	 * impuesta por el constructor.
	 *
	 * @param str1 String Cadena que va al inicio de la cadena respuesta.
	 * @param str2 String Cadena que va al final de la cadena respuesta.
	 *
	 * @return String Cadena resultante.
	 */
	public String linea(String str1, String str2) {
		StringBuilder sb;

		sb = new StringBuilder();
		Arrays.fill(arreglo, ' ');
		sb.append(" ");
		sb.append(str1);
		sb.append(arreglo, 0, arreglo.length - str1.length() - str2.length() - 2);
		sb.append(str2);
		sb.append(" ");

		return sb.toString();
	}
}
