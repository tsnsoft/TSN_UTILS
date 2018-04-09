package tsn.utils.audio;

/**
 * Издать звук ошибки по-умолчанию (без задержки после проигрывания)
 *
 * @author Талипов С.Н.
 */
public class EBeep extends AudioClip {

    /**
     * Издать звук ошибки по-умолчанию
     */
    public EBeep() {
        super("/tsn/utils/audio/err.wav", 0);
    }

}
