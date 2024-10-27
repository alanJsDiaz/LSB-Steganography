package mx.unam.esteganografia;

public class ProcesadorEsteganografia {

    public void procesarEntrada(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Parámetros insuficientes.");
            mostrarUso();
            return;
        }

        String bandera = args[0];
        Comando comando;

        switch (bandera) {
            case "-h":
                if (args.length != 4) {
                    System.out.println("Error: Parámetros insuficientes para la bandera -h.");
                    mostrarUso();
                    return;
                }
                comando = new ComandoOcultar(args[1], args[2], args[3]);
                break;

            case "-u":
                if (args.length != 3) {
                    System.out.println("Error: Parámetros insuficientes para la bandera -u.");
                    mostrarUso();
                    return;
                }
                comando = new ComandoRevelar(args[1], args[2]);
                break;

            default:
                System.out.println("Error: Bandera desconocida.");
                mostrarUso();
                return;
        }

        // Ejecutar el comando seleccionado.
        comando.ejecutar();
    }

    private void mostrarUso() {
        System.out.println("Uso:");
        System.out.println("Para ocultar texto: -h <archivo_texto> <archivo_imagen> <archivo_salida>");
        System.out.println("Para revelar texto: -u <archivo_imagen> <archivo_salida>");
    }
}