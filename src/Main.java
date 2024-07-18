import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static JPanel currentPanel = null;
    static ButtonPanel menuButton = null;
    static JFrame myFrame = null;
    static MainPanel mainPanel = null;
    static Menu menuPanel = null;
    public static void main(String[] args) throws IOException {


        /*JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setPreferredSize(new Dimension(320, 750));
        mainFrame.add(new InstructionScreen(mainFrame));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        */


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        createMain();

    }


    public static void createMain() throws IOException {
        JButton MenuButton;
        //JButton GameButton, DonationButton, WaterButton, AirButton, BackButton;
        //JPanel Games, Options;
        /*Games = new JPanel();
        Games.setBackground(new Color(147, 200, 8));
        Games.setSize(200,500);
        WaterButton = new JButton();
        WaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        AirButton = new JButton();
        Games.add(WaterButton);
        Games.add(AirButton);*/



        myFrame = new JFrame();
        myFrame.setPreferredSize(new Dimension(320, 750));

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Clean My City");
        myFrame.setLayout(null);
        mainPanel = new MainPanel(1,1);
        menuButton = new ButtonPanel(myFrame, mainPanel);
        myFrame.add(menuButton);
        myFrame.add(mainPanel);
        currentPanel = mainPanel;

        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setLocationRelativeTo(null);
    }
}