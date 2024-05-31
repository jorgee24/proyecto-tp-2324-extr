import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 * @author Ainara Fernández Herreros
 * @author Jorge López Sosa
 * Grupo 24
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     * TODO instanciación e inicialización de objetos para la ejecución,
     *  ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     *  por teclado para jugar. Finalmente guardar la puntuación
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 10){
            System.out.println("Numero de argumentos incorrectos");
        }

        Motor motor = new Motor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        motor.iniciar(args[5], args[6], args[7], args[8]);

        Scanner tecladoPersonaje = new Scanner(System.in);
        Personaje personaje = Personaje.crearPersonaje(tecladoPersonaje);

        Scanner tecladoMotor = new Scanner(System.in);
        Random random = new Random();
        motor.jugar(tecladoMotor, personaje, random);

        System.out.println();
        guardarPuntuacion(args[9], personaje);
        mostrarPuntuaciones(args[9]);

        }
    /**
     * Metodo guardarPuntuación en fichero
     * TODO abrir y guardar en el fichero pasado como parametro el personaje
     *  siguiendo el formato descrito en la memoria de la práctica
     * @param ficheroPuntuaciones
     * @param jugador
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(ficheroPuntuaciones));
                bw.write("{ " + jugador.getNombre() + " (V: " + jugador.getVida() + ", A: " + jugador.getAtaque() + ", D: " + jugador.getDefensa() + ", X: " + jugador.getDestreza() + "} ," + jugador.getValorMochila() + " monedas");
            } catch (IOException e){
                System.out.println(e.getMessage());
            } finally {
                {
                    try {
                        if (bw != null){
                            bw.close();
                        }
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * TODO Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     *  pasado como parámetro. P.e:
     *              "Puntuaciones:
     *                  2024-04-04	{ Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * @param ficheroPuntuaciones
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        Scanner scanner = null;
            System.out.println("Puntuaciones: ");
            try {
                scanner = new Scanner(new java.io.File(ficheroPuntuaciones));
                while (scanner.hasNextLine()){
                    System.out.println(scanner.nextLine());
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            } finally {
                {
                    if (scanner != null){
                        scanner.close();
                    }
                }
            }
        }
    }
