import javax.swing.*;
import java.awt.*;


public class GUItest extends JFrame {
    public GUItest() {
        setTitle("Multi-Panel GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        // Set background colors for panels
        northPanel.setBackground(Color.RED);
        southPanel.setBackground(Color.GREEN);
        eastPanel.setBackground(Color.BLUE);
        westPanel.setBackground(Color.YELLOW);
        centerPanel.setBackground(Color.CYAN);

        // Add labels to panels
        northPanel.add(new JLabel("North Panel"));
        southPanel.add(new JLabel("South Panel"));
        eastPanel.add(new JLabel("East Panel"));
        westPanel.add(new JLabel("West Panel"));
        centerPanel.add(new JLabel("Center Panel"));

        // Add panels to the frame
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUItest().setVisible(true);
            }
        });
    }
}