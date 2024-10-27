package mx.unam.esteganografia;

public class ComandoOcultar implements Comando {
    private String archivoTexto;
    private String archivoImagen;
    private String archivoImagenSalida;

    public ComandoOcultar(String archivoTexto, String archivoImagen, String archivoImagenSalida) {
        this.archivoTexto = archivoTexto;
        this.archivoImagen = archivoImagen;
        this.archivoImagenSalida = archivoImagenSalida;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ocultando texto...");
        // Aquí va la logica para ocultar el texto en la imagen.
        // Ejemplo: UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
        System.out.println("Texto ocultado exitosamente en " + archivoImagenSalida);
    }
}
