import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.imageio.*;

public class Menu extends JPanel {
    protected Image image = null;
    String MenuFilePath = "/images/Menu.jpg";
    String GamesMenuFilePath = "/images/GamesMenu.jpg";
    JPanel games,treadingWater, back;
    JFrame mainFrame;
    Menu menu;
    public Menu(JFrame frame) throws IOException {

        menu = this;
        this.mainFrame = frame;

        try{
            image = ImageIO.read(getClass().getResource(MenuFilePath));
        }
        catch(IOException e){
            System.out.println("Cannot find file");
            System.out.println(MenuFilePath);
        }

        setSize(320, 750);
        //setPreferredSize(new Dimension(320,750));
        setLayout(null);
        setBounds(0, 30, 320, 750);

        games = new JPanel();
        games.setBackground(new Color(0,0,0,0));
        games.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                remove(games);
                add(treadingWater);
                add(back);
                try {
                    image = ImageIO.read(getClass().getResource(GamesMenuFilePath));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
            }
        });
        games.setSize(100,50);
        games.setBounds(30,175,250,100);
        add(games);
        repaint();

        treadingWater = new JPanel();
        treadingWater.setBackground(new Color(0,0,0,0));
        treadingWater.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InstructionScreen instructionScreen = new InstructionScreen(mainFrame);
                instructionScreen.setVisible(true);
                instructionScreen.repaint();
                mainFrame.add(instructionScreen);
                mainFrame.repaint();
                mainFrame.remove(menu);



            }
        });
        treadingWater.setSize(100,50);
        treadingWater.setBounds(30,175,250,100);

        back = new JPanel();
        back.setBackground(new Color(0,0,0,0));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                add(games);
                remove(treadingWater);
                remove(back);
                try {
                    image = ImageIO.read(getClass().getResource(MenuFilePath));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
            }
        });
        back.setSize(100,50);
        back.setBounds(30,525,250,100);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, 320, 700, null);
        }
    }



    public void redraw(){
        repaint();
    }

}
