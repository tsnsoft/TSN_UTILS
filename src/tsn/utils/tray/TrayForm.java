package tsn.utils.tray;

import tsn.utils.audio.AudioClip;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.net.URL;
import javax.swing.JFrame;

/**
 * Окно со сворачиванием в трей
 *
 * @author Талипов С.Н.
 */
public class TrayForm extends JFrame {

    private TrayIcon trayIcon;
    private String tipTray;
    private final String nameIconTray;
    private final String nameSoundTray;
    private boolean notice;
    private final SystemTray systemTray = SystemTray.getSystemTray();

    /**
     * Окно со сворачиванием в трей (конструктор по-умолчанию)
     */
    public TrayForm() {
        this("Моя программа", "/tsn/utils/tray/tray.png", "/tsn/utils/tray/tray.wav", true);
    }

    /**
     * Окно со сворачиванием в трей,
     * например: "super("Демонстрационная программа", "/tsn/utils/demos/ico1.png", 
                "/tsn/utils/demos/snd1.wav", false);"
     *
     * @param tipTray надпись у значка окна в трее, например: "Моя программа"
     * @param nameIconTray имя картинки трея, например:
     * "/tsn/utils/tray/tray.png"
     * @param nameSoundTray1 имя звука при сворачивании и разворачивании окна,
     * например: "/tsn/utils/tray/tray.wav"
     * @param notice нужно или нет выдавать сообщение в трее что программа
     * свернута (false)
     */
    public TrayForm(String tipTray, String nameIconTray, String nameSoundTray1,
            boolean notice) {
        super();
        this.tipTray = tipTray;
        this.nameIconTray = nameIconTray;
        this.nameSoundTray = nameSoundTray1;
        this.notice = notice;

        URL resource = getClass().getResource(nameIconTray); // Имя картинки для трея (из JAR-ресурса)
        Image image = Toolkit.getDefaultToolkit().getImage(resource);

        // Надпись в трее при наведении курсора на значок программы
        trayIcon = new TrayIcon(image, tipTray);

        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Восстановление программы из иконки
                setVisible(true);
                setState(JFrame.NORMAL);
                removeTrayIcon();
            }
        });
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) { // Сворачивание программы в иконку
                new AudioClip(nameSoundTray, 0);
                if (e.getNewState() == JFrame.ICONIFIED) {
                    setVisible(false);
                    addTrayIcon();
                }
            }
        });

        PopupMenu popupMenu = new PopupMenu(); // Создание меню для трея
        MenuItem item1 = new MenuItem("Развернуть программу");
        MenuItem item2 = new MenuItem("Выход");

        item1.addActionListener(new ActionListener() { // Команды контекстного меню трея
            @Override
            public void actionPerformed(ActionEvent e) { // Восстановление программы из иконки
                setVisible(true);
                setState(JFrame.NORMAL);
                removeTrayIcon();
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Выход из программы
                dispose();
                System.exit(0);
            }
        });

        // Установка меню для трея
        popupMenu.add(item1);
        popupMenu.add(item2);
        trayIcon.setPopupMenu(popupMenu);
    }

    private void removeTrayIcon() {
        systemTray.remove(trayIcon);
    }

    private void addTrayIcon() {
        try {
            systemTray.add(trayIcon);
            if (notice) {
                trayIcon.displayMessage(tipTray,
                        "Приложение свернуто. "
                        + "Двойной клик по значку для восстановления окна программы",
                        TrayIcon.MessageType.INFO);
            }
        } catch (AWTException ex) {
        }
    }

}
