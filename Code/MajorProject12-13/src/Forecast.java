/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class Forecast {
    public double  forecastvalue;
    Holt ht=new Holt();
    ExponentialSmoothing es=new ExponentialSmoothing();
    double alpha=0.2;
    double beta=0.1;
    int own=0;
    int rent=1;
    double strategy[]=new double[2];
    double standarddeviation;
    
    public double[] calcForecast(int method,double window[], int windowsize,double previousforecast,double payoff[],int count) {
        
        if (method == 0) {
            forecastvalue = es.calculate(alpha, window, windowsize);
        } else {
            forecastvalue = ht.calculate(alpha, beta, window, windowsize);
        }
        
        strategy[0]=forecastvalue;
        
        if (payoff[count] <= previousforecast) {
            strategy[1]=own;
        } 
        else {
            strategy[1]=rent;
        }
                
        return strategy;
    }
    
    public double calcStandardDeviation(int batchsize,double utility[],double previousforecast){
        double deviation,sum=0.0;
        for(int i=0;i<batchsize;i++){
            deviation=previousforecast-utility[i];
            sum=sum+(deviation*deviation);
        }
        standarddeviation=Math.sqrt(sum/batchsize);
        return standarddeviation;
    }    
}
