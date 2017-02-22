/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class ExponentialSmoothing {
    
    public double calculate(double alpha,double payoffwindow[],int windowsize){
        double forecast=0.0;
        int i=0;
        
        forecast=payoffwindow[i];
        
        for(i=1;i<windowsize;i++){
            forecast=alpha*payoffwindow[i-1]+(1-alpha)*forecast;
        }
        return forecast;
    }    
}
