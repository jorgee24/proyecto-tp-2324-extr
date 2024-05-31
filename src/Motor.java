import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Motor
 */
public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;

    /**
     * Constructor Clase Motor
     *
     * @param filas
     * @param columnas
     * @param maxItemsPorSala
     * @param maxMonstruosPorSala
     * @param maxTrampasPorSalas
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;
    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero
     * TODO leer los datos del fichero de mapa pasado por parametro y generar una matriz Sala[][]
     *  con dimension Sala[fila][columna] e inicializar la sala con los valores con la descripción del fichero
     *  y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     *
     * @param ficheroMapa
     * @return sala generada
     */
    Sala[][] cargarMapa(String ficheroMapa) {
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroMapa));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int filas = Integer.parseInt(partes[0]);
                    int columnas = Integer.parseInt(partes[1]);
                    if (filas > 0) filas--;
                    if (columnas > 0) columnas--;
                    String descripcion = partes[2];
                    Sala sala = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, filas,
                            columnas);
                    mapa[filas][columnas] = sala;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
        return mapa;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * TODO Método para leer un fichero de items pasado por parámetro y según
     *  la fila y columna introducir el item en la sala.
     *
     * @param ficheroItems
     */
    private void cargarItems(String ficheroItems) {
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroItems));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                if (fila > 0) fila--;
                int columna = Integer.parseInt(partes[1]);
                if (columna > 0) columna--;
                String descripcion = partes[2];
                Double valor = Double.parseDouble(partes[3]);
                int peso = Integer.parseInt(partes[4]);
                Item item = new Item(descripcion, valor, peso);
                mapa[fila][columna].agregarItem(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }


    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * TODO Método para leer un fichero de Monstruos pasado por parámetro y según
     *  la fila y columna introducir el monstruo en la sala.
     *
     * @param ficheroMonstruos
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroMonstruos));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                if (fila > 0){
                    fila--;
                }
                int columna = Integer.parseInt(partes[1]);
                if (columna > 0) {
                    columna--;
                }
                String descripcion = partes[2];
                int vida = Integer.parseInt(partes[3]);
                int ataque = Integer.parseInt(partes[4]);
                int defensa = Integer.parseInt(partes[5]);
                Monstruo monstruo = new Monstruo(descripcion, vida, ataque, defensa);
                mapa[fila][columna].agregarMonstruo(monstruo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa
     * TODO Método para leer un fichero de trampas pasado por parámetro y según
     *   la fila y columna introducir la trampa en la sala.
     *
     * @param ficheroTrampas
     */
    private void cargarTrampas(String ficheroTrampas) {
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroTrampas));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                if (fila > 0){
                    fila--;
                }
                int columna = Integer.parseInt(partes[1]);
                if (columna > 0) {
                    columna--;
                }
                String descripcion = partes[2];
                int danio = Integer.parseInt(partes[3]);
                Trampa trampa = new Trampa(descripcion, danio);
                mapa[fila][columna].agregarTrampa(trampa);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }


    /**
     * Metodo iniciar, para preparar el mapa
     * TODO instanciación del parametro mapa y carga de datos con los ficheros pasados como parámetros
     *
     * @param ficheroMapa
     * @param ficheroItems
     * @param ficheroMonstruos
     * @param ficheroTrampas
     */
    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }


    /**
     * Método getSala para obtener una sala concreta del mapa
     * TODO devolver una Sala concreta del mapa
     *
     * @param fila
     * @param columna
     * @return
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /**
     * Método mostrarMapa para transformar el mapa en String
     * TODO construir un String con la información contenida en el mapa
     *  respetando el formato que aparece en la memoria de la práctica
     *
     * @param fila
     * @param columna
     * @return
     */

    private boolean existeSala(int fila, int columna) {
        return mapa[fila][columna] != null;
    }

    public String mostrarMapa(int fila, int columna) {
        int filasMapa = mapa.length;
        int columnasMapa = mapa[0].length;
        String a = "";

        a += "╔";
        for (int i = 0; i < columnasMapa; i++) {
            a += "═";
        }
        a += "╗\n";
        for (int i = 0; i < filasMapa; i++) {
            a += "║";
            for (int j = 0; j < columnasMapa; j++) {
                if (i == fila && j == columna) {
                    a += "@";
                } else if (existeSala(i, j)) {
                    a += "░";
                } else {
                    a += " ";
                }
            }
            a += "║\n";
        }
        a += "╚";
        for (int i = 0; i < columnasMapa; i++) {
            a += "═";
        }
        a += "╝\n";
        return a;
    }


    /**
     * Método jugar para empezar a jugar con el personaje
     * TODO método complejo en el que hay que seguir la siguiente ejecución:
     *  1. mostrar el mapa por pantalla
     *  2. Obtener la sala actual y mientras el personaje tenga vida y no haya llegado a la casilla final
     *  3. Durante una jugada mostrar la descripcion de la sala actual
     *  4. Comprobar si hay monstruos en la sala y si es así entrar en combate
     *  4.a El combate acaba cuando la vida del monstruo o la vida del personaje llega a 0
     *  4.b cada turno en el combate el personaje ataca al monstruo y restamos su vida
     *  4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     *  5. Las salas pueden tener trampas
     *  5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es inferior a la destreza del personaje, si es asi esquiva la trampa
     *  5.b Si no esquiva la trampa el personaje recibe daño
     *  5.c al igual que en combate hay que tener en cuenta si la vida del personaje lleva a 0
     *  6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     *  ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando feedback al usuario de todo lo que ocurra (consultar enunciado)
     *
     * @param teclado
     * @param personaje
     * @param random
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        boolean salir = false;
        System.out.println(mostrarMapa(0, 0));
        Sala salaActual = mapa[0][0];
        while (personaje.getVida() > 0 && salaActual.getFila() != mapa.length - 1 && salaActual.getColumna() != mapa[0].length - 1 && !salir) {
            System.out.println(salaActual.getDescripcion());
            if (salaActual.hayMonstruos()) {
                Monstruo monstruo = salaActual.seleccionarMonstruo(teclado);
                while (personaje.getVida() > 0 && monstruo.getVida() > 0) {
                    monstruo.recibirDanyo(personaje.getAtaque());
                    if (monstruo.getVida() > 0) {
                        personaje.recibirDanyo(monstruo.getAtaque());
                    } else {
                        salaActual.eliminarMonstruo(monstruo.getNombre());
                    }
                    if (personaje.getVida() <= 0) {
                        System.out.println("El mounstro te ha eliminado, fin del juego");
                        salir = true;
                    }
                }
            }

            if (salaActual.hayTrampas()) {
                for (int i = 0; i < salaActual.getTrampas().length; i++) {
                    if (salaActual.getTrampas()[i] != null) {
                        if (random.nextInt(50) < personaje.getDestreza()) {
                            System.out.println("Has esquivado la trampa");
                        } else {
                            personaje.recibirDanyo(salaActual.getTrampas()[i].getDanyo());
                            if (personaje.getVida() <= 0) {
                                System.out.println("Has caido en una trampa y no has sobrevivido, fin del juego");
                                salir = true;
                            }
                        }
                    }
                }
            }
            if (salaActual.hayItems()) {
                Item item = salaActual.seleccionarItem(teclado);
                while (item != null) {
                    if (personaje.anyadirItem(item)) {
                        System.out.println("Has añadido el item a tu mochila");
                        salaActual.eliminarItem(item.getDescripcion());
                    } else {
                        System.out.println("No puedes añadir el item a tu mochila");
                    }
                    item = salaActual.seleccionarItem(teclado);
                }
            }
            seleccionarMovimiento(teclado, salaActual);
        }
    }

    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador con su personaje
     * TODO El desplazamiento del personaje se entiende como norte (N), sur (S), este (E) u oeste (O)
     *  en este método hay que capturar por pantalla la acción que va a tomar el usuario de entre las posibles
     *  para ello hay que tener en cuenta que se debe avisar al usuario si puede realizar o no la acción.
     *  Se devolverá la sala destino a la que se ha movido el personaje.
     *
     * @param teclado
     * @param salaActual
     * @return
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        Sala nuevaSala;
        do {
            System.out.println("Introduce el movimiento (N, E, S, O):");
            String movimiento = teclado.nextLine();
            switch (movimiento) {
                case "N":
                    if (salaActual.getFila() == 0) {
                        System.out.println("No puedes moverte al norte");
                        nuevaSala = salaActual;
                    } else if (!existeSala(salaActual.getFila(), salaActual.getColumna())) {
                        System.out.println("No puedes moverte al norte");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila() - 1][salaActual.getColumna()];
                    }

                    break;
                case "S":
                    if (salaActual.getFila() == mapa.length - 1) {
                        System.out.println("No puedes moverte al sur");
                        nuevaSala = salaActual;
                    } else if (!existeSala(salaActual.getFila(), salaActual.getColumna())) {
                        System.out.println("No puedes moverte al sur");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila() + 1][salaActual.getColumna()];
                    }
                    break;
                case "E":
                    if (salaActual.getColumna() == mapa[0].length - 1) {
                        System.out.println("No puedes moverte al este");
                        nuevaSala = salaActual;
                    } else if (!existeSala(salaActual.getFila(), salaActual.getColumna())) {
                        System.out.println("No puedes moverte al este");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() + 1];
                    }
                    break;
                case "O":
                    if (salaActual.getColumna() == 0) {
                        System.out.println("No puedes moverte al oeste");
                        nuevaSala = salaActual;
                    } else if (!existeSala(salaActual.getFila(), salaActual.getColumna())) {
                        System.out.println("No puedes moverte al oeste");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() - 1];
                    }
                    break;
                default:
                    System.out.println("Movimiento no válido. Comprueba que hayas introducido N, E, S o O");
                    nuevaSala = salaActual;
            }
        } while (nuevaSala == salaActual);

        return nuevaSala;
    }
}

