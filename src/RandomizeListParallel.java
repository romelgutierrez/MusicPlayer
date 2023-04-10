import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomizeListParallel {
    public static void main(String[] args) throws InterruptedException {
        // Crear la lista de músicas
        List<String> musicas = new ArrayList<>();
        musicas.add("Cancion 1");
        musicas.add("Cancion 2");
        musicas.add("Cancion 3");
        musicas.add("Cancion 4");
        musicas.add("Cancion 5");
        musicas.add("Cancion 6");
        musicas.add("Cancion 7");
        musicas.add("Cancion 8");
        musicas.add("Cancion 9");
        musicas.add("Cancion 10");
        long startTime = System.currentTimeMillis();
        // Dividir la lista en partes
        int numThreads = 4;
        int size = musicas.size() / numThreads;
        List<List<String>> parts = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            int start = i * size;
            int end = (i == numThreads - 1) ? musicas.size() : (i + 1) * size;
            parts.add(new ArrayList<>(musicas.subList(start, end)));
        }
        
        // Crear los hilos y mezclar cada parte de la lista de forma aleatoria
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            List<String> part = parts.get(i);
            Thread thread = new Thread(() -> {
                Collections.shuffle(part, new Random());
            });
            thread.start();
            threads.add(thread);
        }
        
        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            thread.join();
        }
        
        // Combinar las partes de la lista
        List<String> randomized = new ArrayList<>();
        for (List<String> part : parts) {
            randomized.addAll(part);
        }
        
        // Mostrar la lista mezclada
        System.out.println(randomized);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Total time elapsed: " + elapsedTime + "ms");


        long initialTimeS = System.nanoTime();
        Collections.shuffle(musicas);
        musicas.stream().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeS = System.nanoTime();
        System.out.println("La diferencia de tiempo de programación secuencial: "+ (endTimeS - initialTimeS) / 1_000_000_000.0 + " segundos");
        System.out.println(" ");

        long initialTimeP = System.nanoTime();
      
        musicas.stream().parallel().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeP = System.nanoTime();
        System.out.println("\nLa diferencia de tiempo de programación paralela: "+ (endTimeP - initialTimeP) / 1_000_000_000.0 + " segundos");
    }
}

