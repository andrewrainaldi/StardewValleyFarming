import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameApp extends JPanel implements ActionListener {

    private Player player;
    private final Entity enemy;
    private final Timer timer;

    public GameApp() {
        // Impostazioni del pannello
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // Inizializziamo il giocatore (Blu) e un'entità generica (Bianca)
        player = new Player(100, 100, 40, 40, 5);
        enemy = new Spider(150, 300, 50, 50, 0);

        // Ascolto della tastiera
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        // Loop di gioco: chiama il metodo actionPerformed ogni ~16 millisecondi (60 FPS)
        timer = new Timer(16, this);
        timer.start();
    }

    // Logica di aggiornamento del gioco
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        enemy.update(); // Per ora non fa nulla, ma è pronta!
        
        // Forza il ridisegno dello schermo
        repaint();
    }

    // Rendering grafico
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna le entità
        enemy.draw(g);
        player.draw(g);

        g.dispose();
    }

    // Il Main che fa partire tutto
    public static void main(String[] args) {
        JFrame window = new JFrame("Java Game - Player & Entity");
        GameApp gamePanel = new GameApp();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack(); // Adatta la finestra alla dimensione del GamePanel
        window.setLocationRelativeTo(null); // Centra la finestra
        window.setVisible(true);
    }
}