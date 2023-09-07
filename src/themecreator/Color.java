/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themecreator;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author PC
 */
public class Color {

    private final SimpleDoubleProperty red = new SimpleDoubleProperty();
    private final SimpleDoubleProperty green = new SimpleDoubleProperty();
    private final SimpleDoubleProperty blue = new SimpleDoubleProperty();
    private final SimpleBooleanProperty changed = new SimpleBooleanProperty(false);
    
    public Color() {
        init();
    }
    
    public Color(javafx.scene.paint.Color color) {
        this.red.set(color.getRed());
        this.green.set(color.getGreen());
        this.blue.set(color.getBlue());
        init();
    }
    
    public Color(Double red, Double green, Double blue) {
        this.red.set(red);
        this.green.set(green);
        this.blue.set(blue);
        init();
    }
    
    private void init(){
        this.red.addListener((observable) -> {
            if(this.red.get() > 255){
                this.red.set(this.red.get()-255);
            }
            if(this.red.get() < 0){
                this.red.set(255+this.red.get());
            }
            changed.set(true);
            changed.set(false);
        });
        this.green.addListener((observable) -> {
            if(this.green.get() > 255){
                this.green.set(this.green.get()-255);
            }
            if(this.green.get() < 0){
                this.green.set(255+this.green.get());
            }
            changed.set(true);
            changed.set(false);
        });
        this.blue.addListener((observable) -> {
            if(this.blue.get() > 255){
                this.blue.set(this.blue.get()-255);
            }
            if(this.blue.get() < 0){
                this.blue.set(255+this.blue.get());
            }
            changed.set(true);
            changed.set(false);
        });
    }
    
    public javafx.scene.paint.Color getColor(){
        return javafx.scene.paint.Color.color(red.get(), green.get(), blue.get());
    }
    
    public String getRGB(){
        return "("+(int)this.red.get()+", "+(int)this.green.get()+", "+(int)this.blue.get()+")";
    }
    
    public String getHEX(){
        return "#"
                +(Integer.toHexString((int)this.red.get()).length() == 1 ? "0"+Integer.toHexString((int)this.red.get()) : Integer.toHexString((int)this.red.get()))
                +(Integer.toHexString((int)this.green.get()).length() == 1 ? "0"+Integer.toHexString((int)this.green.get()) : Integer.toHexString((int)this.green.get()))
                +(Integer.toHexString((int)this.blue.get()).length() == 1 ? "0"+Integer.toHexString((int)this.blue.get()) : Integer.toHexString((int)this.blue.get()));
    }
    
    public void setColor(javafx.scene.paint.Color color){
        this.red.set(color.getRed());
        this.green.set(color.getGreen());
        this.blue.set(color.getBlue());
    }
    
    public void setColor(Double red, Double green, Double blue){
        this.red.set(red);
        this.green.set(green);
        this.blue.set(blue);
    }
    
    public Double getRed(){
        return this.red.get();
    }
    
    public Double getGreen(){
        return this.green.get();
    }
    
    public Double getBlue(){
        return this.blue.get();
    }
    
    public SimpleBooleanProperty valueChangingProperty(){
        return changed;
    }
}
