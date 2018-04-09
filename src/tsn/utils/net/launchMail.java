package tsn.utils.net;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Отправка электронного письма
 *
 * @author Талипов С.Н.
 */
public class launchMail {

    /**
     * Отправка электронного письма (используется отдельный поток)
     *
     * @param addr адрес получателя
     * @param subject тема письма
     * @param body текст письма
     */
    public launchMail(String addr, String subject, String body) {
        final String mailTo = addr + "?SUBJECT=" + subject + "&BODY=" + body;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Desktop desktop;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.MAIL)) {
                        URI uriMailTo;
                        try {
                            if (mailTo.length() > 0) {
                                uriMailTo = new URI("mailto", mailTo, null);
                                desktop.mail(uriMailTo);
                            } else {
                                desktop.mail();
                            }
                        } catch (IOException | URISyntaxException e) {
                        }
                    }
                }

            }
        }).start();

    }
}
