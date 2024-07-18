import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//This class will display the river image on a JPanel
//The river is made up of panels
//Each panel will have fish or litter that flows down it

public class River extends JPanel{

    Column[] riverColumns = new Column[3];
    static Timer addTimer ;
    Random random = new Random();
    Picture p = null;
    JFrame frame = null;
    WaterScreen waterScreen = null;

    static int TimeLapse =0;
    static int motionInterval = 50;
    static int generateInterval = 2000;


    //River is a transparent panel

    public River(WaterScreen waterScreen){
        //Initialising set up of panel
        TimeLapse =0;
        motionInterval = 50;
        generateInterval = 2000;
        this.setSize(200, 750);
        this.frame = frame;
        this.setBackground(new Color(0,0,0, 0));
        this.setLayout(null);
        this.waterScreen = waterScreen;

        //Adding columns
        for (int i =0; i<3; i++){
            riverColumns[i] = new Column(i, waterScreen);
            add(riverColumns[i]);
        }

        //Invoking the timer to add images to the columns
        addActionListener addListener = new addActionListener();
        addTimer = new Timer(generateInterval, addListener);
        addTimer.start();

        speedIncreaseActionListener speedIncreaseListener = new speedIncreaseActionListener();
        Timer speedIncreaseTimer = new Timer(1000, speedIncreaseListener); // Increase speed every 5 seconds
        speedIncreaseTimer.start();
    }

    //Method to randomly add pictures to the columns
    public class addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int columnIndex = random.nextInt(0, 3); //Chooses random column
            int typeNum = random.nextInt(0, 2); //Chooses random image type (fish or litter)
            int imageNum = random.nextInt(1, 5);//Chooses random image number

            p = new Picture(typeNum, imageNum, riverColumns[columnIndex], waterScreen);
            riverColumns[columnIndex].addPicture(p);

            TimeLapse += generateInterval;

            if (TimeLapse >= 4000 && generateInterval >= 1000) {


                 if (motionInterval >= 10) {
                    // System.out.println(TimeLapse);
                     //TimeLapse = 0;
                     motionInterval = motionInterval - 5;
                     generateInterval = generateInterval -5;
                     //addTimer.setDelay(50);
                     //System.out.println("increase");
                 }


            }
        }
    }

    public class speedIncreaseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Column column : riverColumns) {
                column.increaseSpeed();
            }
        }
    }

    //Calls the stopTimer method for each column and stops adding images when the game ends
    public void stopTimers() {
        addTimer.stop();
        for (Column column : riverColumns) {
            column.stopTimer();
        }
    }


}
