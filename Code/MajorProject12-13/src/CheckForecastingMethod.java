/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class CheckForecastingMethod {
    int method;
    static int holt,expo=0;
    int increase,decrease,constant;
    
    public int check(double payoffwindow[],int windowsize){
        increase=decrease=constant=0;
        
        for(int j=0;j<windowsize-1;j++){
            if(payoffwindow[j]<payoffwindow[j+1])
                increase++;
            else if(payoffwindow[j]>payoffwindow[j+1])
                decrease++;
            else
                constant++;
        }   
        
        if(constant>=windowsize-3){
            expo++;
            System.out.println("Exponential Smoothing Method "+expo);
            method=0;
        }
        else if(increase+constant>=windowsize-3 || decrease+constant>=windowsize-3){
            holt++;
            System.out.println("Holt's Method "+holt);
            method=1;
        }
        else{
            method=0;
            expo++;
            System.out.println("Exponential Smoothing Method "+expo);
        }
        return method;
    }
}
