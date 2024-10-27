package mx.unam.esteganografia;

public class ComandoRevelar implements Comando {
    private String archivoImagen;
    private String archivoTextoSalida;

    public ComandoRevelar(String archivoImagen, String archivoTextoSalida) {
        this.archivoImagen = archivoImagen;
        this.archivoTextoSalida = archivoTextoSalida;
    }

    @Override
    public void ejecutar() {
        System.out.println("Revelando texto...");
        // Aquí iría la lógica para revelar el texto oculto en la imagen.
        // Ejemplo: UtilidadEsteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
        System.out.println("Texto revelado y guardado en " + archivoTextoSalida);
    }
}
