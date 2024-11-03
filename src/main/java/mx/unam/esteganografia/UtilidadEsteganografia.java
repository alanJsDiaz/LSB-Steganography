package mx.unam.esteganografia;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
            System.err.println("Error al ocultar texto en la imagen. !!!! " + e.getMessage());
            System.exit(1);
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
        BufferedImage imagen = ImageIO.read(new File(archivoImagen));
        if (imagen == null) {
            throw new IOException("La imagen no pudo ser cargada. Verifica que el archivo esté en el formato adecuado y sea legible.");
        }
        return imagen;
    }
    


    /**
     * Método para ocultar un texto en los pixeles de una imagen.
     * @param imagen La imagen en la que se desea ocultar el texto.
     * @param bytesTexto El texto que se desea ocultar en la imagen.
     */
    private static void ocultarTextoEnPixels(BufferedImage imagen, byte[] bytesTexto) {
        String textoConFin = new String(bytesTexto) + "\0EOF\0";
        byte[] bytesTextoConFin;
        
        try {
            bytesTextoConFin = textoConFin.getBytes("UTF-8"); 
        } catch (IOException e) {
            System.err.println("Error de codificación UTF-8: " + e.getMessage());
            return;
        }
        
        int index = 0;
        int capacidadMaxima = imagen.getWidth() * imagen.getHeight() * 3; 
        
        if (bytesTextoConFin.length * 8 > capacidadMaxima) {
            throw new IllegalArgumentException("El texto es demasiado grande para ocultarse en esta imagen. Pruebe con otro texto.");
        }
        
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                if (index < bytesTextoConFin.length * 8) {
                    int rgb = imagen.getRGB(i, j);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
        
                    red = modificarCanal(red, bytesTextoConFin, index++);
                    if (index < bytesTextoConFin.length * 8) green = modificarCanal(green, bytesTextoConFin, index++);
                    if (index < bytesTextoConFin.length * 8) blue = modificarCanal(blue, bytesTextoConFin, index++);
        
                    int rgbNuevo = (0xFF << 24) | (red << 16) | (green << 8) | blue; 
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
        try {
            BufferedImage imagen = cargarImagen(archivoImagen);
            ByteArrayOutputStream bytesRecuperados = reconstruirTextoDeImagen(imagen);

            String textoRecuperado = bytesRecuperados.toString("UTF-8").replace("\0EOF\0", "");
            
            Files.write(new File(archivoTextoSalida).toPath(), textoRecuperado.getBytes("UTF-8"));
        } catch (IOException e) {
            System.err.println("Error al revelar texto de la imagen.");
            e.printStackTrace();
        }
    }
    

    /**
     * Método auxiliar para reconstruir el texto oculto en una imagen.
     * @param imagen La imagen de la cual se extrae el texto oculto.
     * @return Un ByteArrayOutputStream que contiene los bytes del texto recuperado.
     * @throws IOException Si ocurre un error al procesar la imagen.
     */
    private static ByteArrayOutputStream reconstruirTextoDeImagen(BufferedImage imagen) throws IOException {
        ByteArrayOutputStream bytesRecuperados = new ByteArrayOutputStream();
        int index = 0;
        int byteActual = 0;
        boolean finDelTexto = false;

        for (int i = 0; i < imagen.getWidth() && !finDelTexto; i++) {
            for (int j = 0; j < imagen.getHeight() && !finDelTexto; j++) {
                int rgb = imagen.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                byteActual = (byteActual << 1) | extraerBitsDeCanal(red);
                if (++index % 8 == 0) {
                    bytesRecuperados.write(byteActual);
                    finDelTexto = detectarMarcadorDeFin(bytesRecuperados);
                    byteActual = 0;
                    if (finDelTexto) break;
                }

                byteActual = (byteActual << 1) | extraerBitsDeCanal(green);
                if (++index % 8 == 0) {
                    bytesRecuperados.write(byteActual);
                    finDelTexto = detectarMarcadorDeFin(bytesRecuperados);
                    byteActual = 0;
                    if (finDelTexto) break;
                }

                byteActual = (byteActual << 1) | extraerBitsDeCanal(blue);
                if (++index % 8 == 0) {
                    bytesRecuperados.write(byteActual);
                    finDelTexto = detectarMarcadorDeFin(bytesRecuperados);
                    byteActual = 0;
                    if (finDelTexto) break;
                }
            }
        }
        return bytesRecuperados;
    }


    /**
     * Método auxiliar para extraer un bit del canal de color de un píxel.
     * @param canal El valor del canal de color del cual se extrae el bit menos significativo.
     * @return El bit menos significativo del canal.
     */
    private static int extraerBitsDeCanal(int canal) {
        return canal & 1; 
    }


    /**
     * Método auxiliar para detectar el marcador de fin en el texto recuperado.
     * @param bytesRecuperados El ByteArrayOutputStream que contiene los bytes recuperados hasta ahora.
     * @return True si se detecta el marcador de fin, de lo contrario False.
     */
    private static boolean detectarMarcadorDeFin(ByteArrayOutputStream bytesRecuperados) {
        try {
            String textoRecuperado = bytesRecuperados.toString("UTF-8");
            return textoRecuperado.endsWith("\0EOF\0");
        } catch (IOException e) {
            System.err.println("Error de codificación UTF-8 al verificar el marcador de fin: " + e.getMessage());
            return false;
        }
    }

}