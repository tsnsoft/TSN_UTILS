package tsn.utils.audio;

/**
 * Издать звук по-умолчанию (без задержки после проигрывания)
 *
 * @author Талипов С.Н.
 */
public class Beep extends AudioClip {

    /**
     * Издать звук по-умолчанию
     */
    public Beep() {
        super("/tsn/utils/audio/snd.wav", 0);
    }

}
