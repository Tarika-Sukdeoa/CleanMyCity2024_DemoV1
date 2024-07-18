import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

//These columns will make up the river
//The code for the flow of the obejcts will be grouped here rather then the river
//The river will group the columns together
//The columns are transparent panels

public class Column extends JPanel {
    int x, y =0;
    static final int WIDTH =73;
    static final int HEIGHT =750;
    int index ;
    //ArrayList for pictures within the column
    ArrayList<Picture> pictures = new ArrayList<Picture>();
    Timer moveTimer;
    WaterScreen waterScreen;
    int moveDelay = 50;  // Initial delay for the timer


    public Column(int index, WaterScreen waterScreen) {
        //Initialising set up of panel
        setSize(WIDTH, HEIGHT);
        x = (index*WIDTH);
        this.setBounds(x, y, WIDTH, HEIGHT);
        this.waterScreen = waterScreen;
        setBackground(new Color(2, 185, 230));
        this.index=index;
        this.setVisible(true);
        this.setLayout(null);
        //Invoking the timer to make the images move
        moveActionListener moveListener = new moveActionListener();
        moveTimer = new Timer(River.motionInterval, moveListener);
        moveTimer.start();
    }

    public int getIndex(){
        return index;
    }

    //Adding a picture to the arraylist
    public void addPicture(Picture p){
        add(p);
        pictures.add(p);
        repaint();
    }

    //Removing a picture from the arraylist
    public void removePicture(Picture p){
        pictures.remove(p);
        remove(p);
        repaint();
    }

    //Moves the pictures down the screen
    //Creates a separate ArrayList for the pictures to be removed once they exit the screen to avoid errors in the list we iterate over
    public void movePictures(int dy){
        ArrayList<Picture> toRemove = new ArrayList<>();
        synchronized (pictures) {
            Iterator<Picture> iterator = pictures.iterator();
            while (iterator.hasNext()) {
                Picture p = iterator.next();
                p.updatePosition(dy);
                if (p.getY() > HEIGHT) {
                    if (p.typeNum == 0) {
                        waterScreen.incrementMissedlitterCount();
                    }
                    toRemove.add(p);
                }
            }
        }
        for (Picture p : toRemove) {
            removePicture(p);
        }

    }

    public class moveActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            movePictures(2);
            moveTimer.setDelay(River.motionInterval);


        }
    }

    public void increaseSpeed() {
        if (moveDelay > 1) { // Ensure there's a minimum delay
            moveDelay -= 1;
            moveTimer.setDelay(moveDelay);
        }
    }

    //method to stop the timer of the column when the game ends
    public void stopTimer() {
        moveTimer.stop();
    }
}
