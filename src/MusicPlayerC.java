import java.util.ArrayList;
import java.util.List;

public class MusicPlayerC {
    public static void main(String[] args) {
        List<String> listaCanciones = new ArrayList<>();
        listaCanciones.add("Bachata");
        listaCanciones.add("Cumbia");
        listaCanciones.add("Techno");
        listaCanciones.add("Salsa");
        listaCanciones.add("Reggaeton");
        listaCanciones.add("Pop");
        long startTime = System.currentTimeMillis();
        List<Thread> hilos = new ArrayList<>();
        
        for (String cancion : listaCanciones) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Reproduciendo " + cancion);
                    // Aquí iría el código para reproducir la canción
                }
            });
            hilos.add(hilo);
            hilo.start();
        }
        
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Fin de la lista de canciones");
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Total time elapsed: " + elapsedTime + "ms");


        long initialTimeS = System.nanoTime();
        // Collections.shuffle(listaCanciones);
        listaCanciones.stream().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeS = System.nanoTime();
        System.out.println("La diferencia de tiempo de programación secuencial: "+ (endTimeS - initialTimeS) / 1_000_000_000.0 + " segundos");
        System.out.println(" ");

        long initialTimeP = System.nanoTime();
      
        listaCanciones.stream().parallel().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeP = System.nanoTime();
        System.out.println("\nLa diferencia de tiempo de programación paralela: "+ (endTimeP - initialTimeP) / 1_000_000_000.0 + " segundos");

    }
}
