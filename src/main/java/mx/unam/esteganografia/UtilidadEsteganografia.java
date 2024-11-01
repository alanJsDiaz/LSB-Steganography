package mx.unam.esteganografia;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;

public class UtilidadEsteganografia {

    /**
     * Método para ocultar texto en una imagen.
     * @param archivoTexto El nombre del archivo de texto que se desea ocultar.
     * @param archivoImagen El nombre del archivo de la imagen en la que se desea ocultar el texto.
     * @param archivoImagenSalida El nombre del archivo de la imagen de salida con el texto oculto.
     */
    public static void ocultarTextoEnImagen(String archivoTexto, String archivoImagen, String archivoImagenSalida) {
        try {
            byte[] bytesTexto = leerArchivoTexto(archivoTexto);
            BufferedImage imagen = cargarImagen(archivoImagen);
            ocultarTextoEnPixels(imagen, bytesTexto);
            guardarImagen(imagen, archivoImagenSalida);
        } catch (IOException e) {
            System.err.println("Error al ocultar texto en la imagen.");
            e.printStackTrace();
        }
    }


    /**
     * Método para leer un archivo de texto y regresar su contenido en bytes.
     * @param archivoTexto El nombre del archivo de texto que se desea leer.
     * @return Un arreglo de bytes con el contenido del archivo de texto.
     * @throws IOException Si ocurre un error al leer el archivo de texto.
     */
    private static byte[] leerArchivoTexto(String archivoTexto) throws IOException {
        return Files.readAllBytes(new File(archivoTexto).toPath());
    }


    /**
     * Método para cargar una imagen en un objeto BufferedImage.
     * @param archivoImagen El nombre del archivo de la imagen que se desea cargar.
     * @return Un objeto BufferedImage con la imagen cargada.
     * @throws IOException Si ocurre un error al cargar la imagen.
     */
    private static BufferedImage cargarImagen(String archivoImagen) throws IOException {
        return ImageIO.read(new File(archivoImagen));
    }


    /**
     * Método para ocultar un texto en los pixeles de una imagen.
     * @param imagen La imagen en la que se desea ocultar el texto.
     * @param bytesTexto El texto que se desea ocultar en la imagen.
     */
    private static void ocultarTextoEnPixels(BufferedImage imagen, byte[] bytesTexto) {
        int index = 0;
    
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                if (index < bytesTexto.length * 8) {
                    int rgb = imagen.getRGB(i, j);
                    int alfa = (rgb >> 24);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
    
                    alfa = modificarCanal(alfa, bytesTexto, index++);
                    if (index < bytesTexto.length * 8) red = modificarCanal(red, bytesTexto, index++);
                    if (index < bytesTexto.length * 8) green = modificarCanal(green, bytesTexto, index++);
                    if (index < bytesTexto.length * 8) blue = modificarCanal(blue, bytesTexto, index++);
    
                    int rgbNuevo = (alfa << 24) | (red << 16) | (green << 8) | blue;
                    imagen.setRGB(i, j, rgbNuevo);
                }
            }
        }
    }


    /**
     * Método para modificar un canal de color de un pixel de una imagen.
     * @param canal El valor del canal de color que se desea modificar.
     * @param bytesTexto El texto que se desea ocultar en la imagen.
     * @param index El índice del texto que se desea ocultar en la imagen.
     * @return El valor del canal de color modificado.
     */
    private static int modificarCanal(int canal, byte[] bytesTexto, int index) {
        return (canal & 0xFE) | ((bytesTexto[index / 8] >> (7 - (index % 8))) & 1);
    }


    /**
     * Método para guardar una imagen en un archivo.
     * @param imagen La imagen que se desea guardar.
     * @param archivoImagenSalida El nombre del archivo de la imagen de salida.
     * @throws IOException Si ocurre un error al guardar la imagen.
     */
    private static void guardarImagen(BufferedImage imagen, String archivoImagenSalida) throws IOException {
        File archivoSalida = new File(archivoImagenSalida);
        ImageIO.write(imagen, "png", archivoSalida);
    }


    /**
     * Método para revelar el texto oculto en una imagen.
     * @param archivoImagen El nombre del archivo de la imagen de la que se desea revelar el texto oculto.
     * @param archivoTextoSalida El nombre del archivo de texto de salida con el texto revelado.
     */
    public static void revelarTextoDeImagen(String archivoImagen, String archivoTextoSalida) {
        // Implementación de la lógica para revelar el texto oculto de la imagen.
    }
}