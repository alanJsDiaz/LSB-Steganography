package mx.unam.esteganografia;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.annotation.Target;
import ed.Calificador;


public class UtilidadEsteganografiaTest {
    
    public UtilidadEsteganografiaTest() {
    }

    @Test
    public void testOcultarTextoEnImagen() {
        startTest("ocultarTextoEnImagen", 1.0);
        System.out.println("ocultarTextoEnImagen");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen.png";
        String archivoImagenSalida = "imagen_con_texto.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
        assertEquals("imagen_con_texto.png", archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConTextoLargo() {
        System.out.println("ocultarTextoEnImagenConTextoLargo");
        String archivoTexto = "texto_largo.txt";
        String archivoImagen = "imagen.png";
        String archivoImagenSalida = "imagen_con_texto_largo.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }



    @Test
    public void testOcultarTextoEnImagenConTextoCorto() {
        System.out.println("ocultarTextoEnImagenConTextoCorto");
        String archivoTexto = "texto_corto.txt";
        String archivoImagen = "imagen.png";
        String archivoImagenSalida = "imagen_con_texto_corto.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }

    @Test
    public void testOcultarTextoEnImagenConTextoVacio() {
        System.out.println("ocultarTextoEnImagenConTextoVacio");
        String archivoTexto = "texto_vacio.txt";
        String archivoImagen = "imagen.png";
        String archivoImagenSalida = "imagen_con_texto_vacio.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConTextoNulo() {
        System.out.println("ocultarTextoEnImagenConTextoNulo");
        String archivoTexto = "texto_nulo.txt";
        String archivoImagen = "imagen.png";
        String archivoImagenSalida = "imagen_con_texto_nulo.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConImagenNula() {
        System.out.println("ocultarTextoEnImagenConImagenNula");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen_nula.png";
        String archivoImagenSalida = "imagen_con_texto_nula.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConImagenVacia() {
        System.out.println("ocultarTextoEnImagenConImagenVacia");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen_vacia.png";
        String archivoImagenSalida = "imagen_con_texto_vacia.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConImagenPequena() {
        System.out.println("ocultarTextoEnImagenConImagenPequena");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen_pequena.png";
        String archivoImagenSalida = "imagen_con_texto_pequena.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConImagenGrande() {
        System.out.println("ocultarTextoEnImagenConImagenGrande");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen_grande.png";
        String archivoImagenSalida = "imagen_con_texto_grande.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }


    @Test
    public void testOcultarTextoEnImagenConImagenCuadrada() {
        System.out.println("ocultarTextoEnImagenConImagenCuadrada");
        String archivoTexto = "texto.txt";
        String archivoImagen = "imagen_cuadrada.png";
        String archivoImagenSalida = "imagen_con_texto_cuadrada.png";
        UtilidadEsteganografia.ocultarTextoEnImagen(archivoTexto, archivoImagen, archivoImagenSalida);
    }
}
