import java.awt.event.*;
import java.util.ArrayList;

public class GameActionListener implements MouseListener, MouseMotionListener, KeyListener {
    //public boolean lunchBird= false;
    private int xLocationRealsedBird;//=Constans.SLING_SHOT_LOCATION_X+Constans.SLING_SHOT_WIDTH/2 - Constans.WIDTH_CHARACTER/2;
    private int yLocationRealsedBird;//=Constans.WINDOW_HIGHT-(Constans.SLING_SHOT_HIGHT+Constans.HIGHT_CHARACTER);
    private int xPulsPlusAmt=0; // incriment for x+= amount
    private int yPulsPlusAmt=0; // incriment for y+= amount
    private ArrayList<Character> birds;
    private Character currBird;
    private boolean birdCilkedAtSlig;


    public GameActionListener(){}

    public Character getCurrBird(){
        return this.currBird;
    }

    public void setBirdsList(ArrayList<Character> birds){
        this.birds=birds;
    }
    public void removeOneBird(Character bird){
        if(bird!=null) {
            this.currBird = null;
            this.birds.remove(currBird);

        }

    }


    public void mousePressed(MouseEvent e) {


         yLocationRealsedBird =0;
         xLocationRealsedBird =0;
         int countBirdsOnSling=0;
         for(Character b : birds) {
                if (b.getCharacterAsRectangle().contains(Constans.PUT_BIRD_ON_SLIG_X, Constans.PUT_BIRD_ON_SLIG_Y)){
                    countBirdsOnSling++; break;
                }
            }
         for( Character b : birds) {
                if (b.getCharacterAsRectangle().contains(e.getPoint())
                        && e.getX() <= Constans.REST_BENCH_WIDTH && e.getY() <=Constans.REST_BENCH_HIGHT) {
                    if(countBirdsOnSling==0 && this.currBird==null) {
                        b.setLocation(Constans.PUT_BIRD_ON_SLIG_X, Constans.PUT_BIRD_ON_SLIG_Y);
                        currBird=b;
                    }
                }
         }
         if(currBird!=null  && currBird.getCharacterAsRectangle().contains(e.getPoint())){
             birdCilkedAtSlig=true;
         }
         else{birdCilkedAtSlig=false;}
    }

    public void mouseReleased(MouseEvent e) {
        if(xLocationRealsedBird!=0 || yLocationRealsedBird!=0){
            if(e.getX()<Constans.SLING_SHOT_LOCATION_X){
            xPulsPlusAmt = (Constans.SLING_SHOT_LOCATION_X + Constans.SLING_SHOT_WIDTH - xLocationRealsedBird) / 10;
            yPulsPlusAmt = (Constans.WINDOW_HIGHT / 2 - yLocationRealsedBird) / 10;
            if (currBird != null) {
                currBird.setMoveRightIncremet(xPulsPlusAmt);
                currBird.setMoveUpIncremet(yPulsPlusAmt);

            }
        }
        }
    }


    public void mouseDragged(MouseEvent e) {

         if(e.getX()<Constans.SLING_SHOT_LOCATION_X &&birdCilkedAtSlig){
            yLocationRealsedBird = e.getY();
            xLocationRealsedBird = e.getX();
            currBird.setLocation(xLocationRealsedBird,yLocationRealsedBird);
        }
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void keyTyped(KeyEvent e) {
        String currKey=String.valueOf(e.getKeyChar()).toUpperCase();
        if(currKey.equals("R")  ){
            System.out.println("pressed refresh");


        }
        else if(currKey.equals("B")  ){
            System.out.println("pressed bomb");
            if(this.currBird!=null){
                this.currBird.setBomb();
                this.currBird.putImageOnCharcter("resources/angryBirdsBomberBird_transperent.png");
                System.out.println("changed pic");
            }
        }

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
