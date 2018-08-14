/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Shams Sherif
 */
public class ImageObject implements GameObject {

    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int type;
    private int Screenwidth;

    public ImageObject(int posX, int posY, String path) {
        this(posX, posY, path, 0);
    }

    public ImageObject(int posX, int posY, String path, int type) {
        this.x = posX;
        this.y = posY;
       
        this.type = type;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
       int n = getX() - mX;
      
        if(n>0){
            if(type==1){
                
            
            if(mX>100){
                this.x=mX;
            }
            }else if(type==0){
                if(mX>90){
                this.x=mX;
            }
            }
            
        }else if(n<0){
            if(type==1){
            if(mX<900){
                //System.out.println(n);
                this.x=mX;
            }
            }else{
                 if(mX<875){
                //System.out.println(n);
                this.x=mX;
            }
            }
        }
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {

        return;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        if (type == 1) {
            return spriteImages[0].getWidth();
        }
		//return spriteImages[0].getWidth();
        return 20;

    }

    @Override
    public int getHeight() {
        if (type == 1) {
            return spriteImages[0].getHeight();
        }
        //return spriteImages[0].getHeight();
        return 30;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getType() {
        return type;
        
    }

    public void setType(int type) {
        this.type = type;
    }

}
