import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Terrein  extends JComponent {
    private ArrayList <Rectangle> pillers;
    private ArrayList <Rectangle> slingshot;
    private Rectangle restBench;
    private Rectangle bottom;
    private BufferedImage grassImg;
    private BufferedImage horisonalLog;
    private BufferedImage verticlLog;
    public Terrein() throws IOException {
        this.pillers = new ArrayList<>();
        this.slingshot = new ArrayList<>();
        grassImg = ImageIO.read(getClass().getResource("resources/grassNew_transperent.png"));
        verticlLog = ImageIO.read(getClass().getResource("resources/vertical log .jpg"));
        //verticlLog = ImageIO.read(getClass().getResource("resources/Toons_Stone_Block-removebg-preview.png"));

        horisonalLog = ImageIO.read(getClass().getResource("resources/horisontal log.jpg"));

    }
    void deleteAllPillers(){
        this.pillers.clear();
    }


    public ArrayList<Rectangle> getPillers(){
        return this.pillers;
    }

    public Rectangle getBottom(){
        return this.bottom;
    }


    void restBench(){
        this.restBench = new Rectangle(Constans.REST_BENCH_X, Constans.REST_BENCH_Y, Constans.REST_BENCH_WIDTH,Constans.REST_BENCH_HIGHT);
    }



    void bottom(){
        this.bottom = new Rectangle(0,Constans.WINDOW_HIGHT-(Constans.WINDOW_INSET+Constans.HIGHT_CHARACTER/2),Constans.WINDOW_WIDTH,30);//Constans.HIGHT_CHARACTER/3


    }
    void printBottom(Graphics g){
        g.drawImage(grassImg,bottom.x, bottom.y, bottom.width, bottom.height,this);
    }



    void slingshot(){

        // middle Main
        slingshot.add(new Rectangle(Constans.SLING_SHOT_LOCATION_X,
                Constans.WINDOW_HIGHT-Constans.SLING_SHOT_HIGHT,
                Constans.SLING_SHOT_WIDTH,
                Constans.SLING_SHOT_HIGHT
                ));

        //horisontal base
        slingshot.add(new Rectangle(
                Constans.SLING_SHOT_LOCATION_X-  Constans.SLING_BASE_WIDTH/2 + Constans.SLING_SHOT_WIDTH/2  ,
                slingshot.get(0).y  ,
                Constans.SLING_BASE_WIDTH,
                Constans.SLING_SHOT_ARM_WIDTH ));
        // left arm
        slingshot.add(new Rectangle(
                slingshot.get(1).x,
                slingshot.get(1).y - Constans.SLING_SHOT_ARM_LENGTH/2,
                Constans.SLING_SHOT_ARM_WIDTH,
                Constans.SLING_SHOT_ARM_LENGTH
        ));
        // right arm
        slingshot.add(new Rectangle(
                slingshot.get(1).x +slingshot.get(1).width - Constans.SLING_SHOT_ARM_WIDTH ,
                slingshot.get(1).y - Constans.SLING_SHOT_ARM_LENGTH/2,
                Constans.SLING_SHOT_ARM_WIDTH,
                Constans.SLING_SHOT_ARM_LENGTH

        ));

    }

    void paintSlingshotString(Graphics g , int xStart, int yStart){
        g.setColor(Color.BLACK);

        g.drawLine(xStart+Constans.WIDTH_CHARACTER/2,yStart+Constans.HIGHT_CHARACTER/2,
                Constans.PUT_BIRD_ON_SLIG_X+ Constans.WIDTH_CHARACTER/2,
                Constans.PUT_BIRD_ON_SLIG_Y+Constans.HIGHT_CHARACTER/2);


    }



    void printSlingshot(Graphics g){
        g.setColor(Color.MAGENTA);
        for(Rectangle part : slingshot){
            BufferedImage currImg;
            g.fillRect(part.x, part.y, part.width,part.height);
            if(part.width> part.height){currImg =horisonalLog;}
            else{currImg=verticlLog;}
            g.drawImage(currImg,part.x, part.y, part.width, part.height,this);

        }
        g.setColor(Color.BLACK);
        g.fillRect (slingshot.get(1).x,slingshot.get(2).y+slingshot.get(2).height/4,slingshot.get(1).width,2);
       // g.drawLine();

    }

    void randomTerreain(){
        for (int i = 0; i < Constans.NUMBER_OF_PILLERS; i++) {
            int pillerHight= random(Constans.MIN_TERRAIN_HIGHT,Constans.MAX_TERRAIN_HIGHT);
            pillers.add( new Rectangle(400+i*100,
                    Constans.WINDOW_HIGHT-pillerHight ,
                    Constans.PILLER_WIDTH,pillerHight));
        }

    }
    int random(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(min, max+1);
    }

    void printPiller(Graphics g){
        g.setColor(Color.ORANGE);
        for(Rectangle piller : pillers){
            g.fillRect(piller.x, piller.y, piller.width,piller.height);
            g.drawImage(verticlLog,piller.x, piller.y, piller.width, piller.height,this);

        }

    }

    void printRestBench(Graphics g ){
        g.setColor(Color.ORANGE);
        g.fillRect(this.restBench.x,this.restBench.y,this.restBench.width,this.restBench.height);
        g.drawImage(horisonalLog,restBench.x, restBench.y, restBench.width, restBench.height,this);
    }

}
