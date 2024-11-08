package mx.unam.esteganografia;

/**
 * Clase principal que ejecuta el programa.
 */
public class Main {
    /**
     * Método principal que ejecuta el programa.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        ProcesadorEntrada procesador = new ProcesadorEntrada();
        procesador.procesarEntrada(args);
    }
}
