import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;


public class MainPanel extends JPanel {
    protected int waterScore;
    protected int airScore;
    protected Image image = null;
    JPanel imagePanel = null;
    String imageFilePath;
    Timer timer;




    public class TimerActionListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {


            if (airScore >=1 && airScore < 4) {
                airScore++;
                setAirScore(airScore);
            }
            if (waterScore >=1 && waterScore < 4){
                waterScore++;
                setWaterScore(waterScore);
            }


            try {
                changeImage(airScore, waterScore);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }






    }




    public MainPanel(int airScore, int waterScore) throws IOException {
        this.airScore = airScore;
        this.waterScore = waterScore;

        String imageFilePath = "/Images/A" + this.airScore + "W" + this.waterScore + ".gif";
        TimerActionListener actionListener = new TimerActionListener();
        timer = new Timer(20000, actionListener);


        setPreferredSize(new Dimension(320,750));
        this.setBounds(0, 30, 320, 750);
        this.setVisible(true);
        setLayout(new BorderLayout());
        JPanel imagePanel = new JPanel();


        add(imagePanel, BorderLayout.SOUTH);




        //setting intial main screen
        try {
            image = ImageIO.read(getClass().getResource(imageFilePath));
        }
        catch (IOException ex) {
            System.out.println("Cannot find image");
        }





        imagePanel.repaint();
        changeImage(1,1);
        timer.start();


    }
    public void changeImage(int airScore, int waterScore) throws IOException {
        setAirScore(airScore);
        setWaterScore(waterScore);
        this.imageFilePath = "/Images/A" + this.airScore + "W" + this.waterScore + ".gif";
        this.image = ImageIO.read(getClass().getResource(imageFilePath));


        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, -50, 320, 750, null);
        }


    }


    public Image getImage() {
        return image;
    }


    public void setImage(Image image) {
        this.image = image;
    }
    public void setAirScore(int airScore){
        if (airScore >= 1 && airScore <= 4){
            this.airScore = airScore;
        }
    }
    public void setWaterScore(int waterScore){
        if (waterScore >= 1 && waterScore <= 4){
            this.waterScore = waterScore;
        }
    }




}

