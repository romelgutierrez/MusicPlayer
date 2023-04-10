import java.util.ArrayList;
import java.util.Collections;

/*  Un usuario tiene una lista de musicas de regueton,
    se requiere resolver que las musicas se reproduscan de acuerdo al
    orden de la lista y de manera aleatoria. 
    
 */

public class RandomSongs {
    public static void main(String[] args) {
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Canción 1");
        songs.add("Canción 2");
        songs.add("Canción 3");
        songs.add("Canción 4");
        songs.add("Canción 5");
        
          System.out.println("Lista original de canciones:");
          long initialTimee = System.nanoTime();
              for (String song : songs) {
                  System.out.println("Reproduciendo la "+song);
              }
          long endTimee = System.nanoTime();
          System.out.println("La diferencia de tiempo de programación basico secuencial: "+ (endTimee - initialTimee) / 1_000_000_000.0 + " segundos");
          System.out.println("============================================================");
          long initialTime = System.nanoTime();
               songs.stream().forEach(s -> System.out.println("Reproduciendo la "+s));
          long endTime = System.nanoTime();
          System.out.println("La diferencia de tiempo de programación secuencial: "+ (endTime - initialTime) / 1_000_000_000.0 + " segundos");
          long initialTimeee = System.nanoTime();
               songs.stream().parallel().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeee = System.nanoTime();
        System.out.println("La diferencia de tiempo de programación paralela: "+ (endTimeee - initialTimeee) / 1_000_000_000.0 + " segundos");

        System.out.println("----------------------------------------------------------------");   

        System.out.println("\nLista de canciones mezcladas / aleatorias:");

        Collections.shuffle(songs);
        long initialTimeP1 = System.nanoTime();

        for (String song : songs) {
            System.out.println("Reproduciendo la "+song);
        }
        long endTimeP1 = System.nanoTime();
        System.out.println("\nLa diferencia de tiempo de programación basico secuencial: "+ (endTimeP1 - initialTimeP1) / 1_000_000_000.0 + " segundos");


        long initialTimeS = System.nanoTime();
        Collections.shuffle(songs);
        songs.stream().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeS = System.nanoTime();
        System.out.println("La diferencia de tiempo de programación secuencial: "+ (endTimeS - initialTimeS) / 1_000_000_000.0 + " segundos");
        System.out.println(" ");

        long initialTimeP = System.nanoTime();
        Collections.shuffle(songs);
        songs.stream().parallel().forEach(s -> System.out.println("Reproduciendo la "+s));
        long endTimeP = System.nanoTime();
        System.out.println("\nLa diferencia de tiempo de programación paralela: "+ (endTimeP - initialTimeP) / 1_000_000_000.0 + " segundos");
      
      
    }
}
