/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunspotworld;

/**
 *
 * @author SANDHYA
 */
import javax.swing.JOptionPane;


public class snake {
    
    /** Creates a new instance of snake */
    public snake() {
        for(int i=0;i<snake_length;i++) {
            PostionsX[i]=5;
            PostionsY[i]=5;
            
        }
    }
    
    public   void Move_snake() {
        for(int i=0;i<snake_length;i++) {
            PostionsX[snake_length-i]=PostionsX[snake_length-(i+1)];
            PostionsY[snake_length-i]=PostionsY[snake_length-(i+1)];
            
            
        }
        
        PostionsX[0]=headX;
        PostionsY[0]=headY;
        
    }
    
    public   void put_Head_postion(int x,int y) {
        headX+=x;
        headY+=y;
        
    }
    public    boolean check_game_over() {
        for(int i=1;i<snake_length;i++)
            
        {
            if((PostionsX[i]==headX)&&(PostionsY[i]==headY)) {
                
                
                return true;
            }
        }
        if(headX<0||headX>1040||headY<0||headY>1040) {
            
            return true;
        }
        
        return false;
    }
    
    public boolean compare_with_foodPostion(int x,int y) {
        
        for(int i=0;i<snake_length;i++) {
            if(x==PostionsX[i]&&y==PostionsY[i]) {
                return true;
            }
        }
        
        return false;
    }
    
    public synchronized void Increase_lenght() {
        snake_length++;
    }
    
    public int get_lenght() {
        return snake_length;
    }
    
    public int getOval_Size() {
        return Oval_size;
    }
    
    
    
    public int[] get_All_postionsX() {
        return PostionsX;
    }
    
    public int[] get_All_postionsY() {
        return PostionsY;
    }
    
    public int getHeadX() {
        return  PostionsX[0];
    }
    public int getHeadY() {
        return  PostionsY[0];
    }
    
    private int headX=5;
    private int headY=5;
    private int[] PostionsX=new int[1040];
    private int[] PostionsY=new int[1040];
    private int snake_length=5;
    private final int Oval_size=20;
    
}