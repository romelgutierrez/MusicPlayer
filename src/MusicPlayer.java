import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*
 * musica aleatoria consurreceia
 */
public class MusicPlayer {
    private List<String> musicList;

    public MusicPlayer(List<String> musicList) {
        this.musicList = musicList;
    }

    public void playRandom() throws InterruptedException {
        List<String> shuffledList = new ArrayList<>(musicList);
        Collections.shuffle(shuffledList, new Random());

        for (String music : shuffledList) {
            System.out.println("Playing music: " + music);
            Thread.sleep(1000); // simulating music playback time
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> musicList = List.of("Music A", "Music B", "Music C", "Music D", "Music E", "Music A", "Music B", "Music C", "Music D", "Music E");
        MusicPlayer musicPlayer = new MusicPlayer(musicList);

        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            try {
                musicPlayer.playRandom();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join(); // esperar a que termine el hilo

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Total time elapsed: " + elapsedTime + "ms");
    }
}
