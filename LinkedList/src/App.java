import javax.swing.SwingUtilities;
import samplellist.LinkedListGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinkedListGUI().setVisible(true);
            }
        });
    }
}