# LSB-Steganography
Este proyecto permite ocultar y revelar texto en imágenes utilizando técnicas de esteganografía. Fue desarrollado en Java y se configura como un proyecto Maven, permitiendo empaquetarlo en un archivo JAR ejecutable.

## Requisitos

- Java 1.8 o superior
- Maven
- Una imagen en formato PNG para ocultar el texto.

## Instalación

1. Clona el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/alanJsDiaz/LSB-Steganography.git
   ```
2. Accede al directorio del proyecto:
   ```bash
   cd LSB-Steganography
   ```
3. Compila el proyecto usando Maven:
   ```bash
   mvn compile
   ```
4. Empaqueta el proyecto:
   ```bash
   mvn package
   ```
   Esto generará un archivo `esteganografia.jar` en el directorio `target`.

## Uso

### Comandos

El programa permite dos comandos principales:

1. **Ocultar texto en una imagen**  
   Utiliza la bandera `-h` junto con el nombre del archivo de texto a ocultar, el nombre del archivo de imagen y el nombre del archivo de salida.

   ```bash
   java -jar target/esteganografia.jar -h <archivo_texto> <archivo_imagen> <archivo_salida>
   ```

   - `<archivo_texto>`: archivo de texto con el mensaje a ocultar (debe tener extensión `.txt`).
   - `<archivo_imagen>`: archivo de imagen en formato PNG donde se ocultará el texto.
   - `<archivo_salida>`: archivo PNG donde se guardará la imagen con el texto oculto.

2. **Revelar texto de una imagen**  
   Utiliza la bandera `-u` junto con el nombre del archivo de imagen y el archivo de texto de salida.

   ```bash
   java -jar target/esteganografia.jar -u <archivo_imagen> <archivo_salida>
   ```

   - `<archivo_imagen>`: archivo de imagen que contiene el texto oculto (debe ser PNG).
   - `<archivo_salida>`: archivo de texto donde se guardará el texto revelado (debe tener extensión `.txt`).

### Ejemplos

1. **Ocultar texto en una imagen**:
   ```bash
   java -jar target/esteganografia.jar -h mensaje.txt imagen.png imagen_oculta.png
   ```

2. **Revelar texto de una imagen**:
   ```bash
   java -jar target/esteganografia.jar -u imagen_oculta.png mensaje_revelado.txt
   ```

## Estructura del Código

- **Main**: Clase principal que inicializa el programa y procesa los argumentos de línea de comandos.
- **ProcesadorEntrada**: Procesa los argumentos y selecciona el comando adecuado (`ComandoOcultar` o `ComandoRevelar`).
- **Esteganografia**: Clase principal con los métodos para ocultar y revelar el texto en imágenes.
- **Comando**: Interfaz para los comandos.
- **ComandoOcultar** y **ComandoRevelar**: Implementaciones específicas de la interfaz `Comando` para ocultar y revelar texto.

## Pruebas

El proyecto incluye dependencias para JUnit en el archivo `pom.xml`. Puedes ejecutar las pruebas unitarias con el siguiente comando:

```bash
mvn test
```

## Créditos

-Alan Joshep Diaz Quijada
-Diego Hazael Vega Alonso

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---
