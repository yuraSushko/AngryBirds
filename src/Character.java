import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Character extends JComponent {
    private int sizeWidth;
    private int sizeHight;
    private int x;
    private int y;
    private int timePartInFalling;
    private int moveUpIncremet;
    private int moveRightIncremet;
    private boolean bomb;
    private BufferedImage charcterImage;

    public Character(int sizeWidth, int sizeHight,int x, int y, String imagePath) {
        this.bomb=false;
        this.sizeWidth = sizeWidth;
        this.sizeHight = sizeHight;
        this.x=x;
        this.y=y;
        this.timePartInFalling=0;
        this.moveRightIncremet=0;
        this.moveUpIncremet=0;
        putImageOnCharcter(imagePath);
    }
    
    public void putImageOnCharcter(String imagePath){
        try {
            this.charcterImage =ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    


    public boolean isBomb() {
        return bomb;
    }

    public void setBomb() {
        this.bomb = true;
    }

    public void setMoveUpIncremet(int moveUpIncremet) {
        this.moveUpIncremet = moveUpIncremet;
    }

    public void setMoveRightIncremet(int moveRightIncremet) {
        this.moveRightIncremet = moveRightIncremet;
    }

    void gravity(){

        if(this.x>Constans.SLING_SHOT_LOCATION_X+Constans.SLING_SHOT_WIDTH){
            if( !collisionWithBottom()){
                if(!collisionWithPiller()) {
                    this.y += 0.2 * timePartInFalling;
                    timePartInFalling++;
                }else{timePartInFalling=0;}
            }

        }
    }


    public boolean withinBounds(){
        boolean within = false;
        if(this.x>= 0 -Constans.WINDOW_WIDTH/4   && this.x<=Constans.WINDOW_WIDTH){
            if(this.y>=0-Constans.WINDOW_HIGHT && this.y<=Constans.WINDOW_WIDTH-Constans.HIGHT_CHARACTER){
                within=true;
            }
        }
        return within;
    }

    public boolean collisionWithPiller(){
        boolean collision = false;
        for(Rectangle piller : MainScene.terrein.getPillers() ) {
            if (this.getCharacterAsRectangle().intersects(piller)) {
                collision = true;
                this.moveRightIncremet=0;
                this.moveUpIncremet=0;
                if(this.bomb){MainScene.terrein.getPillers().remove(piller);}
                break;

            }
        }
        return collision;
    }



    public boolean collisionWithBottom() {
        return this.getCharacterAsRectangle().intersects(MainScene.terrein.getBottom());
   }


    Rectangle getCharacterAsRectangle(){
        return new Rectangle(this.x,this.y,this.sizeWidth,this.sizeHight);
    }



    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }


    public int getMoveUpIncremet() {
        return moveUpIncremet;
    }

    public int getMoveRightIncremet() {
        return moveRightIncremet;
    }

    public void moveUp(int amount){
        this.y+=amount;
    }
    public void moveRight(int amount){
        this.x+=amount;
    }


    public void drawCharacter( Graphics g ){
        g.drawImage(charcterImage, x, y, sizeWidth, sizeHight, this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public String toString() {
        return "Character{" +
                "sizeWidth=" + sizeWidth +
                ", sizeHight=" + sizeHight +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
