import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class box extends Actor
{
    private static String kata="ABCDEFGHIJKLMNOPQ";
    private int index=0;
    
    private boolean drag=false;
    private int rx=0,ry=0,apmove=0;
    
    public box()
    {
    
    }
    public box(int a)
    {
        index=a;
    }
    
    public int getIndex()
    {
        return index;
    }
    public void addedToWorld(World MyWorld)
    {
        GreenfootImage image=new GreenfootImage(128,128);
        image.setColor(Color.WHITE);
        image.fill();
        GreenfootImage word=new GreenfootImage(""+kata.charAt(index),100,new Color(150,0,0),null);
        image.drawImage(word,(int)(0.5*(image.getWidth() - word.getWidth())),(int)(0.5*(image.getHeight() - word.getHeight())));
        image.setColor(new Color(100,100,100));
        image.drawRect(0,0,image.getWidth()-1,image.getHeight()-1);
        setImage(image);
        
    }
    
    
    
    public box checkC(int posx,int posy){
        List<box>boxs=getWorld().getObjects(box.class);
        if (boxs!=null && boxs.size()>0){
            List<Integer>idobject=new ArrayList<Integer>();
            for(int i=0;i<boxs.size();i++){
                if(boxs.get(i).equals(this)){
                    int bts=2;
                    int axo=(int)Math.round(boxs.get(i).getX()-0.5*boxs.get(i).getImage().getWidth()+bts);
                    int bxo=(int)Math.round(boxs.get(i).getX()+0.5*boxs.get(i).getImage().getWidth()+bts);
                    int ax1=(int)Math.round(posx-0.5*getImage().getWidth()+bts);
                    int bx1=(int)Math.round(posx+0.5*getImage().getWidth()+bts);

                    int ayo=(int)Math.round(boxs.get(i).getY()-0.5*boxs.get(i).getImage().getHeight()+bts);
                    int byo=(int)Math.round(boxs.get(i).getY()+0.5*boxs.get(i).getImage().getHeight()+bts);
                    int ay1=(int)Math.round(posy-0.5*getImage().getHeight()+bts);
                    int by1=(int)Math.round(posy+0.5*getImage().getHeight()+bts);
                    
                    if(bx1<axo || bxo<ax1)continue;
                    if(by1<ayo || byo<ay1)continue;
                    
                    int rx=(int)Math.abs(boxs.get(i).getX()-posx);
                    int ry=(int)Math.abs(boxs.get(i).getY()-posy);
                    
                    idobject.add(i);
                    idobject.add((rx<ry)?rx:ry);
                }
            }
            if(idobject.size()>2){
                int id=0,r=0;
                for(int i=0;i<idobject.size()/2;i++){
                    if(i==0){
                        id=idobject.get(2*i+0);
                        r=idobject.get(2*i+1);
                    }else{
                        if(idobject.get(2*i+1)<r){
                            id=idobject.get(2*i+0);
                            r=idobject.get(2*i+1);
                        }
                    
                    }
                }
                return boxs.get(id);
                
            }
            else if(idobject.size()>0){
                return boxs.get(idobject.get(0));
            }else if(idobject.size()==0)return null;
        }
        return null;
    }
    
    
    
    public void moveTo(int posx,int posy,int mode)
    {
        int dx=posx-getX();
        int dy=posy-getY();
        
        if(mode==1){
            if(apmove==0)
            {
               if(Math.abs(dx)>Math.abs(dy)){
                    apmove=1;
                } else{
                    apmove=2;
                }
            }
            if(apmove==0)return;
            
            if(apmove==1){
                posy=getY();
                if(Math.abs(dx)>128)return;
            }else if (apmove==2){
                posx=getX();
                if(Math.abs(dy)>128)return;
            }
        }
        
        if(posx<0.5*getWorld().getWidth()-256+64){
            posx=(int)Math.round(0.5*getWorld().getWidth()-256+64);
        }
        if(posx>0.5*getWorld().getWidth()+256-64){
            posx=(int)Math.round(0.5*getWorld().getWidth()+256-64);
        }
        if(posy<0.5*getWorld().getHeight()-256+64){
            posy=(int)Math.round(0.5*getWorld().getHeight()-256+64);
        }
        if(posy>0.5*getWorld().getHeight()+256-64){
            posy=(int)Math.round(0.5*getWorld().getHeight()+256-64);
        }
        
        
        box boxo=checkC(posx,posy);
        if(boxo!=null){
            //if(apmove==1 ||mode==0){
                if(getX()<boxo.getX()){
                        boxo.moveTo((int)Math.round(posx+0.5*(getImage().getWidth()+boxo.getImage().getWidth())),boxo.getY(),0);
                        posx=(int)Math.round(boxo.getX()-0.5*(getImage().getWidth()+boxo.getImage().getWidth()));
                }else if(getX()>boxo.getX()){
                        boxo.moveTo((int)Math.round(posx+0.5*(getImage().getWidth()+boxo.getImage().getWidth())),boxo.getY(),0);
                        posx=(int)Math.round(boxo.getX()-0.5*(getImage().getWidth()+boxo.getImage().getWidth()));
                    
                }
            //}else if(apmove==2){
                if(getY()<boxo.getY()){
                        boxo.moveTo(boxo.getX(),(int)Math.round(posy+0.5*(getImage().getHeight()+boxo.getImage().getHeight())),0);
                        posy=(int)Math.round(boxo.getY()-0.5*(getImage().getHeight()+boxo.getImage().getHeight()));
                }else if(getY()>boxo.getY()){
                        boxo.moveTo(boxo.getX(),(int)Math.round(posy-0.5*(getImage().getHeight()+boxo.getImage().getHeight())),0);
                        posy=(int)Math.round(boxo.getY()+0.5*(getImage().getHeight()+boxo.getImage().getHeight()));
                    }
             //   }
        }
        setLocation(posx,posy);
    }
    
    public void act()   
    {
        if(Greenfoot.mouseDragged(this)){
            MouseInfo mouse=Greenfoot.getMouseInfo();
            if(!drag){
                drag=true;
                rx=getX()-mouse.getX();
                ry=getY()-mouse.getY();
                
            }else{
                int posx=mouse.getX()+rx;
                int posy=mouse.getY()+ry;
                moveTo(posx,posy,1);
            }
        }
        if (Greenfoot.mouseDragEnded(this)){
            drag=false;
            apmove=0;
            
            ((MyWorld)getWorld()).checkP();
        }
    }
}
