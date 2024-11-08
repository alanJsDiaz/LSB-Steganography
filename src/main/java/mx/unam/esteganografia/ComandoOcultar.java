package mx.unam.esteganografia;

/**
 * Comando que oculta un texto en una imagen.
 */
public class ComandoOcultar implements Comando {
    private String archivoTexto;
    private String archivoImagen;
    private String archivoImagenSalida;

    /**
     * Constructor.
     * @param archivoTexto nombre del archivo de texto que contiene el mensaje a ocultar.
     * @param archivoImagen nombre del archivo de imagen en el que se ocultará el mensaje.
     * @param archivoImagenSalida nombre del archivo de imagen con el mensaje oculto.
     */
    public ComandoOcultar(String archivoTexto, String archivoImagen, String archivoImagenSalida) {
        this.archivoTexto = archivoTexto;
        this.archivoImagen = archivoImagen;
        this.archivoImagenSalida = archivoImagenSalida;
    }

    /**
     * Ejecuta el comando.
     */
    @Override
    public void ejecutar() {
        System.out.println("Ocultando texto...");
        Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
        System.out.println("Texto ocultado exitosamente en " + archivoImagenSalida);
    }
}