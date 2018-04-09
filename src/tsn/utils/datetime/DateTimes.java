package tsn.utils.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Получение текущей системной даты и времени
 *
 * @author Талипов С.Н.
 */
public class DateTimes {

    /**
     * Получение текущей даты
     *
     * @return текущая системная дата (формат "dd.MM.yyyy")
     */
    public String getDate() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(d);
    }

    /**
     * Получение текущего времени
     *
     * @return текущее системное время (формат "HH:mm:ss")
     */
    public String getTime() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(d);
    }

}
