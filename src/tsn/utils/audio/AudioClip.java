package tsn.utils.audio;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Проигрывание звукового wav-файла
 *
 * @author Талипов С.Н.
 */
public class AudioClip {

    /**
     * Проигрывание звукового wav-файла
     *
     * @param filename имя звукового wav-файла, например: "snd.wav"
     * @param sleep задержка в мс. после проигрывания звука (0)
     */
    public AudioClip(String filename, int sleep) {
        try {
            Clip c = AudioSystem.getClip(); // Подключение к звуковой системе
            URL url = getClass().getResource(filename); // Находим звук в ресурсе
            // Подключение к звуковому файлу
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            c.open(ais); // Проиграть звук
            c.loop(0); // Проиграть звук один раз, без повторов
            // Если необходимо гарантированное время для проигрывания, то нужен следующий код:
            try {
                Thread.sleep(sleep); // Время ожидания программы пока играет звук, мс
            } catch (InterruptedException ex) {
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
        }
    }
}
