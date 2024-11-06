package mx.unam.esteganografia;

/**
 * Comando que revela un texto oculto en una imagen.
 */
public class ComandoRevelar implements Comando {
    private String archivoImagen;
    private String archivoTextoSalida;

    /**
     * Constructor.
     * @param archivoImagen Imagen que contiene el texto oculto.
     * @param archivoTextoSalida Archivo de texto donde se guardará el texto revelado.
     */
    public ComandoRevelar(String archivoImagen, String archivoTextoSalida) {
        this.archivoImagen = archivoImagen;
        this.archivoTextoSalida = archivoTextoSalida;
    }

    /**
     * Ejecuta el comando.
     */
    @Override
    public void ejecutar() {
        System.out.println("Revelando texto...");
        Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
        System.out.println("Texto revelado y guardado en " + archivoTextoSalida);
    }
}
