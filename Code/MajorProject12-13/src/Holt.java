/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class Holt {
    
    public double calculate(double alpha,double beta,double payoffwindow[],int windowsize){
        double forecast = 0.0;
        double a1,a2,b1,b2;
        
        a1=payoffwindow[0];
        b1=(payoffwindow[windowsize-1]-payoffwindow[0])/windowsize;
        
        for(int i=1;i<windowsize;i++){
            a2=alpha*payoffwindow[1]+(1-alpha)*(a1+b1);
            b2=beta*(a2-a1)+(1-beta)*b1;
            
            forecast=a2+b2;
            a1=a2;
            b1=b2;
        }
        
        if(forecast<0.5)
            forecast=0.5;
        return forecast;
    }
    
}
