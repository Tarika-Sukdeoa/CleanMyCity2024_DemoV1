import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GameOverScreen extends JPanel{

    JFrame frame;
    JLabel scoreLabel = new JLabel("Score: 0");
    JPanel scorePanel = new JPanel();



    public GameOverScreen(JFrame frame){

        setPreferredSize(new Dimension(320, 750));
        setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);
        this.frame = frame;
        setBounds(0, 0, 320, 750);
        scoreLabel.setText(String.valueOf(WaterScreen.score));
        scoreLabel.setVisible(true);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Chalkboard", Font.BOLD, 40));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        scorePanel.setSize(200, 100);
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.CENTER);
        scorePanel.setBackground(new Color(2, 185, 230));
        //scorePanel.setBackground(Color.BLACK);
        scorePanel.setBounds(60, 210, 200, 100);
        add(scorePanel);



        buttonPanel noButton = new buttonPanel(163, 405, 120, 65, "no", this);
        buttonPanel yesButton = new buttonPanel(32, 405, 123, 65, "yes", this);
        this.add(yesButton);
        this.add(noButton);


    }

    public class buttonPanel extends JPanel implements MouseListener {
        //"waterScreen" or "mainScreen"
        String action = null;

        JPanel parentPanel = null;

        public buttonPanel(int x, int y, int width, int height, String action, JPanel parentPanel){
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.WHITE);
            setBackground(new Color(0,0,0,0));
            setBounds(x,y,width,height);
            setVisible(true);
            this.action = action;
            this.parentPanel = parentPanel;
            this.addMouseListener(this);


        }


        @Override
        public void mouseClicked(MouseEvent e) {
            if (action != null){

                System.out.println(action);
                parentPanel.setVisible(false);
                frame.remove(parentPanel);

                if (action.equalsIgnoreCase("Yes")){
                    frame.add(new WaterScreen(frame));
                    frame.pack();
                    frame.repaint();

                }
                else if (action.equalsIgnoreCase("No")){
                    frame.add(Main.mainPanel);
                    frame.add(Main.menuButton);
                    Main.currentPanel = Main.mainPanel;
                    Main.menuButton.reset();
                    frame.remove(this);
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        try{
            Image image = ImageIO.read(new File("images_WaterGame/gameOver.png"));
            g.drawImage(image, 0, 0, 320, 750, null);

        }catch(IOException e){
            System.out.println("Cannot find gameOver image");
        }
    }

}
