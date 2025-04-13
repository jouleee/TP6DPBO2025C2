import javax.swing.*;
import java.awt.*;

public class Startmenu {
    private BackgroundPanel mainPanel;
    private JButton mulaiButton;

    public Startmenu() {
        mainPanel = new BackgroundPanel("src/assets/background.png");
        mainPanel.setLayout(new GridBagLayout()); // biar tombol di tengah

        mulaiButton = new JButton("Start Game");
        mulaiButton.setPreferredSize(new Dimension(180, 60));
        mulaiButton.setFont(new Font("Arial", Font.BOLD, 22));
        mulaiButton.setFocusPainted(false);
        mulaiButton.setForeground(Color.WHITE);
        mulaiButton.setContentAreaFilled(false);
        mulaiButton.setOpaque(false);
        mulaiButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        mulaiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover efek
        mulaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mulaiButton.setForeground(Color.CYAN);
                mulaiButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mulaiButton.setForeground(Color.WHITE);
                mulaiButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
        });

        mainPanel.add(mulaiButton); // otomatis di tengah karena pakai GridBagLayout
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getMulaiButton() {
        return mulaiButton;
    }
}
