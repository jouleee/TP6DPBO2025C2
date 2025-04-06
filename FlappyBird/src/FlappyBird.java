import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;
    // pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    int score = 0;
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean isGameOver = false;
    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
//        setBackground(Color.blue);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0,0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        for(int i = 0; i< pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);

    }
    public void resetGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipes.clear();
        score = 0;
        isGameOver = false;
        gameLoop.start();
        pipesCooldown.start();
    }

    public void gameOver() {
        gameLoop.stop();
        pipesCooldown.stop();
        isGameOver = true;

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Game Over", false); // false = non-modal
        dialog.setLayout(new BorderLayout());

        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);

        messagePanel.add(gameOverLabel);
        messagePanel.add(scoreLabel);

        dialog.add(messagePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                resetGame();
            }
        });
        buttonPanel.add(restartButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Supaya bisa tekan R untuk restart
        dialog.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
                    dialog.dispose();
                    resetGame();
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

        dialog.setSize(200, 120);
        dialog.setLocationRelativeTo(this);
        dialog.setFocusable(true);
        dialog.setVisible(true);
        dialog.requestFocusInWindow();
    }

    public void checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            if (playerRect.intersects(pipeRect)) {
                gameOver();
                break;
            }
        }
        // Tambahkan di akhir fungsi checkCollision()
        if (player.getPosY() + player.getHeight() > frameHeight || player.getPosY() < 0) {
            gameOver();
        }

    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i< pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // Deteksi kalau player lewat pipa
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                if (pipe.getImage() == upperPipeImage) {
                    score++;
                    pipe.setPassed(true);
                }
            }

        }

    }

    public void placePipes() {
        int openingSpace = frameHeight / 4;

        // Posisi acak pipa atas (negatif supaya sebagian nongol dari atas layar)
        int randomPosY = - (int)(Math.random() * (pipeHeight / 2));

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        upperPipe.setVelocityX(-4); // supaya bisa jalan ke kiri
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        lowerPipe.setVelocityX(-4);
        pipes.add(lowerPipe);
    }

    public void actionPerformed(ActionEvent e){
        move();
        checkCollision();
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
    }

    public void keyReleased(KeyEvent e){

    }
}
