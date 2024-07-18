import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

//This will be the menue button on the mainframe
public class ButtonPanel extends JPanel implements MouseListener {

    boolean isSelected = false;
    JLabel buttonLabel = new JLabel("v");
    //Menu menuPanel;
    JPanel currentPanel;
    JFrame frame;

    public ButtonPanel(JFrame frame, JPanel currentPanel) {

        setForeground(Color.WHITE);
        setSize(320, 30);
        setBounds(0, 0, 320, 30);
        setBackground(new Color(147, 200, 8));
        setLayout(new BorderLayout());

        buttonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        buttonLabel.setForeground(Color.WHITE);
        buttonLabel.setOpaque(true);
        buttonLabel.setBackground(new Color(147, 200, 8));
        buttonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(buttonLabel, BorderLayout.CENTER);

        try {
           Main.menuPanel = new Menu(frame);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Cannot create menu");
        }

        this.addMouseListener(this);
        this.currentPanel = currentPanel;
        this.frame = frame;




    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if (!isSelected) {
            frame.remove(currentPanel);
            frame.add(Main.menuPanel);
            Main.menuPanel.redraw();
            isSelected = true;
            buttonLabel.setText("^");
        }
        else{
            isSelected = false;
            frame.remove(Main.menuPanel);
            frame.add(currentPanel);
            try {
                if (currentPanel.getClass() == MainPanel.class) {
                    MainPanel mainPanel = (MainPanel) currentPanel;
                    mainPanel.changeImage(mainPanel.airScore, mainPanel.waterScore);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            buttonLabel.setText("v");
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

    public void reset(){
        isSelected = false;
        buttonLabel.setText("v");
    }
}
