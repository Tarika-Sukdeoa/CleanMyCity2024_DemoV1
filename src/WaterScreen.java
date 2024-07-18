import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobPriority;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class WaterScreen extends JPanel {
    static int score = 0;
    River river;
    JPanel scorePanel = new JPanel();
    static JLabel scoreLabel = new JLabel(String.valueOf(score));
    //Variable to hold number of fish clicked
    static int fishCount = 0;
    //Variable to hold number of litter pieces missed
    static int missedLitterCount = 0;
    JFrame frame = null;



    public WaterScreen(JFrame frame){

        score =0;
        fishCount = 0;
        missedLitterCount = 0;
        scoreLabel.setText(String.valueOf(score));

        this.setSize(320, 750);
        this.setBounds(0, 0, 320, 750);
        this.frame = frame;

        river = new River(this);
        this.setBackground(new Color(147, 200, 8));
        this.setLayout(null);

        //adding river panel
        river.setBounds(0, 0, 220, 750);
        add(river);

        //adding score label
        scoreLabel.setVisible(true);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.CENTER);

        //This below is for testing
        //scorePanel.setBackground(Color.BLUE);

        scorePanel.setBackground(new Color(8, 106, 53));
        scorePanel.setSize(50, 20);
        scorePanel.setBounds(234, 45, 50, 20);
        add(scorePanel);

    }

    //Draws river background
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
            Image bg = ImageIO.read(new File("images_WaterGame/background.png"));
            g.drawImage(bg, 0, 0,  300, 750,null);
        } catch (IOException e) {
            System.out.println("Cannot find file");
        }

        g2d.setColor(new Color(8, 106, 53));
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Score", 238, 100);

    }

    //Method to update and display the score
    public void updateScore(int points) {
        score += points;
        scoreLabel.setText(String.valueOf(score));
    }

    public void incrementFishCount() {
        fishCount++;
        //if the user clicks 3 fish the game ends
        if (fishCount >= 3) {
            endGame("You clicked 3 fish! Game over!");
        }
    }

    public void incrementMissedlitterCount() {
        missedLitterCount++;
        //if the user misses 5 pieces of litter the game ends
        if (missedLitterCount >= 5) {
            endGame("You missed 5 pieces of litter! Game over!");
        }
    }


    //method to end the game
    public void endGame(String message) {
        river.stopTimers();
        GameOverScreen gameOverScreen = new GameOverScreen(frame);
        frame.add(gameOverScreen);
        this.setVisible(false);
        frame.remove(this);
        //JOptionPane.showMessageDialog(null, message);
        try {
            File scoreFile = new File("Score.txt");
            Scanner scan = new Scanner(scoreFile);
            FileWriter writer = new FileWriter("Score.txt");
            writer.write(String.valueOf(score));
            writer.close();


        } catch (IOException e) {
            System.out.println("Cannot find text file.");
        }

    }



}
