import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class InstructionScreen extends JPanel{

    static final int  HEIGHT = 750;
    static final int  WIDTH = 320;
    JFrame frame;
    InstructionScreen This = null;

    public InstructionScreen(JFrame frame){

        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);
        this.frame = frame;

        frame.remove(Main.menuButton);

        buttonPanel continueButton = new buttonPanel(163, 405, 120, 65, "waterPanel", this);
        add(continueButton);
        buttonPanel cancelButton = new buttonPanel(32, 405, 123, 65, "mainPanel", this);
        add(cancelButton);

        This = this;
    }



    public class buttonPanel extends JPanel implements MouseListener{
        //"waterScreen" or "mainScreen"
        String nextPanel = null;

        JPanel parentPanel = null;

        public buttonPanel(int x, int y, int width, int height, String nextPanel, JPanel parentPanel){
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.WHITE);
            setBackground(new Color(0,0,0,0));
            setBounds(x,y,width,height);
            setVisible(true);
            this.nextPanel = nextPanel;
            this.parentPanel = parentPanel;
            this.addMouseListener(this);


        }


        @Override
        public void mouseClicked(MouseEvent e) {
            if (nextPanel != null){
                parentPanel.setVisible(false);
                frame.remove(parentPanel);

                if (nextPanel.equalsIgnoreCase("WaterPanel")){
                    frame.add(new WaterScreen(frame));
                    //frame.pack();
                    frame.repaint();

                }
                else if (nextPanel.equalsIgnoreCase("mainPanel")){
                    frame.add(Main.mainPanel);
                    Main.menuButton.reset();
                    frame.add(Main.menuButton);
                    frame.remove(This);
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
        try {
            Image image = ImageIO.read(new File("images_WaterGame/instructions.png"));
            g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);


        }
        catch (IOException e){
            System.out.println("Cannot find first screen");
        }
    }
}

