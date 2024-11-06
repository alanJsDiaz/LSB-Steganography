package mx.unam.esteganografia;


import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Clase de pruebas unitarias para la clase UtilidadEsteganografia.
 */
public class UtilidadEsteganografiaTest extends Calificador {


    public UtilidadEsteganografiaTest() {
    }

    @Test
    public void testLeerArchivoTexto() throws IOException {
        inicioPrueba("leerArchivoTexto", 1.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto.txt";
        byte[] expected = {(byte) 72, (byte) 111, (byte) 108, (byte) 97, (byte) 33, (byte) 32, (byte) 194, (byte) 191, (byte) 67, (byte) 195, (byte) 179, (byte) 109, (byte) 111, (byte) 32, (byte) 101, (byte) 115, (byte) 116, (byte) 195, (byte) 161, (byte) 115, (byte) 63, (byte) 32, (byte) 69, (byte) 115, (byte) 116, (byte) 111, (byte) 32, (byte) 101, (byte) 115, (byte) 32, (byte) 117, (byte) 110, (byte) 97, (byte) 32, (byte) 112, (byte) 114, (byte) 117, (byte) 101, (byte) 98, (byte) 97, (byte) 46};
        byte[] result = Esteganografia.leerArchivoTexto(archivoTexto);
        assertArrayEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void testLeerArchivoTextoVacio() throws IOException {
        inicioPrueba("leerArchivoTexto", 1.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_vacio.pdf";
        try {
            Esteganografia.leerArchivoTexto(archivoTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        }
    }

    @Test
    public void testLeerArchivoTextoNoValido() throws IOException {
        inicioPrueba("leerArchivoTexto", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_no_valido.pdf";
        try {
            Esteganografia.leerArchivoTexto(archivoTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        }
    }

    @Test
    public void testLeerArchivoTextoNoExiste() throws IOException {
        inicioPrueba("leerArchivoTexto", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_no_existe.txt";
        try {
            Esteganografia.leerArchivoTexto(archivoTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        }
    }

    @Test
    public void testLeerArchivoNombreLargo() throws IOException {
        inicioPrueba("leerArchivoTexto", 1.0);
        String archivoTexto = "texto_nombre_largooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo.txt";
        try {
            Esteganografia.leerArchivoTexto(archivoTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        }
    }

    @Test
    public void testCargarImagen() {
        inicioPrueba("cargarImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image.png";
        try {
            Esteganografia.cargarImagen(archivoImagen);
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al cargar la imagen.");
        }
    }

    @Test
    public void testCargarImagenNoValida() {
        inicioPrueba("cargarImagen", 2.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_valida.jpg";
        try {
            Esteganografia.cargarImagen(archivoImagen);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al cargar la imagen.");
        }
    }

    @Test
    public void testCargarImagenInexistente() {
        inicioPrueba("cargarImagen", 2.0);
        String archivoImagen = "imagen_no_existe.png";
        try {
            Esteganografia.cargarImagen(archivoImagen);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al cargar la imagen.");
        }
    }

    @Test
    public void testCargarImagenNombreLargo() {
        inicioPrueba("cargarImagen", 1.0);
        String archivoImagen = "imagen_nombre_largoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo.png";
        try {
            Esteganografia.cargarImagen(archivoImagen);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al cargar la imagen.");
        }
    }

    @Test 
    public void testModificarCanal() {
        inicioPrueba("modificarCanal", 1.0);
        int canal = 120;
        byte[] bytesTexto = {38};
        int index = 2;
        int expected = 121;
        int result = Esteganografia.modificarCanal(canal, bytesTexto , index);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void testModificarCanalFinal(){
        inicioPrueba("modificarCanal", 1.0);
        int canal = 120;
        byte[] bytesTexto = {38};
        int index = 6;
        int expected = 121;
        int result = Esteganografia.modificarCanal(canal, bytesTexto, index);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test 
    public void testModificarCanalConCero() {
        inicioPrueba("modificarCanal", 1.0);
        int canal = 0;
        byte[] bytesTexto = {0};
        int index = 0;
        int expected = 0;
        int result = Esteganografia.modificarCanal(canal, bytesTexto, index);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void testModificarCanalConUno() {
        inicioPrueba("modificarCanal", 1.0);
        int canal = 1;
        byte[] bytesTexto = {1};
        int index = 0;
        int expected = 0;
        int result = Esteganografia.modificarCanal(canal, bytesTexto, index);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void testModificarCanalExtra() {
        inicioPrueba("modificarCanal", 1.0);
        int canal = 120;
        byte[] bytesTexto = {38,42};
        int index = 14;
        int expected = 121;
        int result = Esteganografia.modificarCanal(canal, bytesTexto, index);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void testOcultarTextoEnPixels() {
        inicioPrueba("ocultarTextoEnPixels", 1.0);
        byte[] bytesTexto = {(byte) 72, (byte) 111, (byte) 108, (byte) 97}; // "Hola" en ASCII
        try {
            BufferedImage imagen = Esteganografia.cargarImagen("src/test/java/mx/unam/esteganografia/imagenesPrueba/image.png");
            Esteganografia.ocultarTextoEnPixels(imagen, bytesTexto);
            agregaPuntos(1.0);
            aprobada();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al cargar la imagen de prueba.");
        }
    }

    @Test 
    public void testOcultarTextoEnPixelsTextoVacio() {
        inicioPrueba("ocultarTextoEnPixels", 1.0);
        byte[] bytesTexto = new byte[0];
        try {
            BufferedImage imagen = Esteganografia.cargarImagen("src/test/java/mx/unam/esteganografia/imagenesPrueba/image2.png");
            Esteganografia.ocultarTextoEnPixels(imagen, bytesTexto);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al cargar la imagen de prueba.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        }
    }

    @Test 
    public void testOcultarTextoEnPixelsImagenNula() {
        inicioPrueba("ocultarTextoEnPixels", 1.0);
        byte[] bytesTexto = {(byte) 72, (byte) 111, (byte) 108, (byte) 97}; // "Hola" en ASCII
        try {
            Esteganografia.ocultarTextoEnPixels(null, bytesTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        }
    }

    @Test
    public void testOcultarTextoConTextoDemas(){
        inicioPrueba("ocultarTextoEnPixels", 1.0);
        byte[] bytesTexto = new byte[526344];
        for ( int  i = 0; i <= 526343; i++){
            bytesTexto[i] = (byte) 72;
        }
        try {
            BufferedImage imagen = Esteganografia.cargarImagen("src/test/java/mx/unam/esteganografia/imagenesPrueba/image4.png");
            Esteganografia.ocultarTextoEnPixels(imagen, bytesTexto);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al cargar la imagen de prueba.");
        }
    }

    @Test 
    public void guardarImagen() {
        inicioPrueba("guardarImagen", 1.0);
        try {
            Esteganografia.guardarImagen(null, "imagen.png");
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al guardar la imagen.");
        }
    }

    @Test 
    public void guardarImagenNombreLargo() {
        inicioPrueba("guardarImagen", 1.0);
        try {
            Esteganografia.guardarImagen(null, "Salida.png"); 
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al guardar la imagen.");
        }
    }

    @Test
    public void guardarImagenNombreVacio() {
        inicioPrueba("guardarImagen", 1.0);
        try {
            BufferedImage imagen = Esteganografia.cargarImagen("src/test/java/mx/unam/esteganografia/imagenesPrueba/image4.png");
            Esteganografia.guardarImagen(imagen, "    "); 
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al guardar la imagen.");
        }
    }

    @Test 
    public void ocultarTextoEnImagen() {
        inicioPrueba("ocultarTextoEnImagen", 1.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_valido.txt";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image3.png";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen3_con_texto.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            if(imagenesIguales("src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen3.1_con_texto.png", archivoImagenSalida)){
                agregaPuntos(1.0);
                aprobada();
            } else {
                fail("Las imágenes no son iguales.");
            }
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void ocultarTextoEnImagenTextoVacio() {
        inicioPrueba("ocultarTextoEnImagen", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_vacio.txt";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image.png";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image_con_texto.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void ocultarTextoEnImagenTextoNoValido() {
        inicioPrueba("ocultarTextoEnImagen", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_no_valido.pdf";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen.png";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_con_texto.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void ocultarTextoEnImagenImagenNoValida() {
        inicioPrueba("ocultarTextoEnImagen", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto.txt";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_valida.jpg";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_con_texto.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void ocultarTextoEnImagenImagenNombreLargo() {
        inicioPrueba("ocultarTextoEnImagen", 1.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_valido.txt";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen.png";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_nombre_largoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void ocultarTextoEnImagenImagenInexistente() {
        inicioPrueba("ocultarTextoEnImagen", 2.0);
        String archivoTexto = "src/test/java/mx/unam/esteganografia/textosPrueba/texto.txt";
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_existe.png";
        String archivoImagenSalida = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_existe_con_texto.png";
        try {
            Esteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al ocultar el texto en la imagen.");
        }
    }

    @Test
    public void extraerBitsDeCanal(){
        inicioPrueba("extraerBitsDeCanal", 1.0);
        int canal = 120;
        int expected = 0;
        int result = Esteganografia.extraerBitsDeCanal(canal);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void extraerBitsDeCanalCero(){
        inicioPrueba("extraerBitsDeCanal", 1.0);
        int canal = 0;
        int expected = 0;
        int result = Esteganografia.extraerBitsDeCanal(canal);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void extraerBitsDeCanalUno(){
        inicioPrueba("extraerBitsDeCanal", 1.0);
        int canal = 1;
        int expected = 1;
        int result = Esteganografia.extraerBitsDeCanal(canal);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void extraerBitsDeCanalExtra(){
        inicioPrueba("extraerBitsDeCanal", 1.0);
        int canal = 87;
        int expected = 1;
        int result = Esteganografia.extraerBitsDeCanal(canal);
        assertEquals(expected, result);
        agregaPuntos(1.0);
        aprobada();
    }

    @Test
    public void detectarMarcadorDeFin_SinMarcadorDeFin(){
        inicioPrueba("detectarMarcadorDeFin", 1.0);
        ByteArrayOutputStream bytesRecuperados = new ByteArrayOutputStream();
        try {
            bytesRecuperados.write("Texto de prueba sin marcador".getBytes("UTF-8"));
            if (Esteganografia.detectarMarcadorDeFin(bytesRecuperados) == false){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void detectarMarcadorDeFin_ConMarcadorDeFin(){
        inicioPrueba("detectarMarcadorDeFin", 1.0);
        ByteArrayOutputStream bytesRecuperados = new ByteArrayOutputStream();
        try {
            bytesRecuperados.write("Texto de prueba con marcador\0EOF\0".getBytes("UTF-8"));
            if (Esteganografia.detectarMarcadorDeFin(bytesRecuperados) == true){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void detectarMarcadorDeFin_TextoVacio(){
        inicioPrueba("detectarMarcadorDeFin", 1.0);
        ByteArrayOutputStream bytesRecuperados = new ByteArrayOutputStream();
        try {
            bytesRecuperados.write("".getBytes("UTF-8"));
            if (Esteganografia.detectarMarcadorDeFin(bytesRecuperados) == false){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void detectarMarcadorDeFin_MarcadorDeFinVacio(){
        inicioPrueba("detectarMarcadorDeFin", 1.0);
        ByteArrayOutputStream bytesRecuperados = new ByteArrayOutputStream();
        try {
            bytesRecuperados.write("Texto de prueba con marcador\0\0".getBytes("UTF-8"));
            if (Esteganografia.detectarMarcadorDeFin(bytesRecuperados) == false){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reconstruirTextoDeImagen_TextoConMarcadordeFin(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image5_con_texto.png";
        try {
            BufferedImage imagen = Esteganografia.cargarImagen(archivoImagen);
            ByteArrayOutputStream textoRecuperado = Esteganografia.reconstruirTextoDeImagen(imagen);
            String resultado = textoRecuperado.toString("UTF-8");
            if (resultado.equals("Hola\0EOF\0")){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (Exception e) {
            fail("Error al reconstruir el texto de la imagen.");
        }
    }

    @Test
    public void reconstruirTextoDeImagen_TextoSinMarcadordeFin(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image_con_texto.png";
        try {
            BufferedImage imagen = Esteganografia.cargarImagen(archivoImagen);
            ByteArrayOutputStream textoRecuperado = Esteganografia.reconstruirTextoDeImagen(imagen);
            String resultado = textoRecuperado.toString("UTF-8");
            if (resultado.equals("Hola")){
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (Exception e) {
            fail("Error al reconstruir el texto de la imagen.");
        }
    }

    @Test
    public void reconstruirTextoDeImagen_ImagenVacia(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        try {
        Esteganografia.reconstruirTextoDeImagen(null);
        } catch (Exception e) {
            agregaPuntos(1.0);
                aprobada();
        }
    }


    @Test
    public void reconstruirTextoDeImagen_ImagenNoValida(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_valida.jpg";
        try {
            BufferedImage imagen = Esteganografia.cargarImagen(archivoImagen);
            Esteganografia.reconstruirTextoDeImagen(imagen);
            
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al reconstruir el texto de la imagen.");
        }
    }

    @Test
    public void reconstruirTextoDeImagen_ImagenNombreLargo(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_nombre_largooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";
        try {
            BufferedImage imagen = Esteganografia.cargarImagen(archivoImagen);Esteganografia.reconstruirTextoDeImagen(imagen);
            
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al reconstruir el texto de la imagen.");
        }
    }

    @Test
    public void reconstruirTextoDeImagen_ImagenInexistente(){
        inicioPrueba("reconstruirTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_existe.png";
        try {
            BufferedImage imagen = Esteganografia.cargarImagen(archivoImagen);
            Esteganografia.reconstruirTextoDeImagen(imagen);
            
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al reconstruir el texto de la imagen.");
        }
    }

    @Test
    public void revelarTextoDeImagen(){
        inicioPrueba("revelarTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen3_con_texto.png";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_salida.txt";
        String textoOriginal = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_valido.txt";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            if (archivosIguales(textoOriginal, archivoTextoSalida)) {
                agregaPuntos(1.0);
                aprobada();
            }
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
            aprobada();
        }
    }

    @Test
    public void revelarTextoDeImagenImagenNoValida(){
        inicioPrueba("revelarTextoDeImagen", 2.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_valida.jpg";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_salida.txt";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
        }
    }

    @Test
    public void revelarTextoDeImagenImagenNombreLargo(){
        inicioPrueba("revelarTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_nombre_largooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_salida.txt";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
        }
    }

    @Test
    public void revelarTextoDeImagenImagenInexistente(){
        inicioPrueba("revelarTextoDeImagen", 2.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/imagen_no_existe.png";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_salida.txt";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
        }
    }

    @Test
    public void revelarTextoDeImagenTextoNombreLargo(){
        inicioPrueba("revelarTextoDeImagen", 1.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image_con_texto.png";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_nombre_largoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(1.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
        }
    }

    @Test
    public void revelarTextoDeImagenTextoNoValido(){
        inicioPrueba("revelarTextoDeImagen", 2.0);
        String archivoImagen = "src/test/java/mx/unam/esteganografia/imagenesPrueba/image_con_texto.png";
        String archivoTextoSalida = "src/test/java/mx/unam/esteganografia/textosPrueba/texto_no_valido.pdf";
        try {
            Esteganografia.revelarTextoDeImagen(archivoImagen, archivoTextoSalida);
            fail("Se esperaba una excepción IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            agregaPuntos(2.0);
            aprobada();
        } catch (Exception e) {
            fail("Error al revelar el texto de la imagen.");
        }
    }
}
