import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class Picture extends JPanel implements MouseListener, MouseMotionListener {
    //variable to hold image type (fish or litter)
    int typeNum;
    //variable for which column the picture is in
    Column column;
    Image image = null;
    //Variables for position of the image
    int x, y =0;
    WaterScreen waterScreen;

    public Picture(int typeNum, int imageNum, Column column, WaterScreen waterScreen){
        this.typeNum = typeNum;
        this.column = column;
        x = Column.WIDTH* column.getIndex();
        y =0;
        this.waterScreen = waterScreen;

        this.setSize(Column.WIDTH,Column.WIDTH);
        this.setBounds(0, 0, Column.WIDTH, Column.WIDTH);
        this.setVisible(true);
        this.addMouseListener(this);
        this.setLayout(null);

        //Choosing an image based on the type and image number
        try {
            if (typeNum == 1) {
                image = ImageIO.read(new File("images_WaterGame/fish" + imageNum + ".png"));
            }
            else{
                image = ImageIO.read(new File("images_WaterGame/litter" + imageNum + ".png"));
            }
        }catch(IOException e){
            System.out.println("cannot find pictures");

        }



    }

    //Drawing the image
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0, Column.WIDTH, Column.WIDTH, this);
    }

    //Moving the image down the screen
    public void updatePosition(int dy){
        y += dy;
        setBounds(0, y, Column.WIDTH, Column.WIDTH);
        repaint();
    }

    public int getY(){
        return this.y;
    }


    //Removes the picture when clicked on
    //Also changes the score according to the type of image when clicked on
    @Override
    public void mouseClicked(MouseEvent e) {
        column.removePicture(this);
        //If the image clicked is a fish the score decreases by 5 and the fish clicked counter increases
        if (typeNum == 1) {
            waterScreen.updateScore(-5);
            waterScreen.incrementFishCount();
        } else {
            waterScreen.updateScore(20);
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

    @Override
    public void mouseDragged(MouseEvent e) {
        column.removePicture(this);
        //If the image clicked is a fish the score decreases by 5 and the fish clicked counter increases
        if (typeNum == 1) {
            waterScreen.updateScore(-5);
            waterScreen.incrementFishCount();
        } else {
            waterScreen.updateScore(20);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
