/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class Deviation {
    int rentcount;
    double rentsum;

    int owncount;
    double ownsum;

    int own=0;
    int rent=1;
    int strategy;
    
    public int calcstrategy(double forecast,double utility[],int batchsize, double equilibrium,double average,int gamestrategy)
    {
        double rentmean,ownmean=0.0;
        
        for(int i=0;i<batchsize;i++){
            if(utility[i]>average){
                rentcount++;
                rentsum=rentsum+utility[i];
            }
            else{
                owncount++;
                ownsum=ownsum+utility[i];
            }
        }

        rentmean=rentsum/rentcount;
        ownmean=ownsum/owncount;
        
        if(gamestrategy==0){
            if(Math.abs(ownmean-equilibrium)>Math.abs(rentmean-forecast))
                strategy=rent;
            else
                strategy=own;
        }
        else{
            if(Math.abs(ownmean-forecast)>Math.abs(rentmean-equilibrium))
                strategy=rent;
            else
                strategy=own;
        }
        
        System.out.println("Strategy deviation : "+strategy);
        return strategy;
    }
    
    public int calcStrategy(double gtsd,double fvsd,int gamestrategy, int forecaststrategy){
        if(gtsd<fvsd){
            strategy = gamestrategy;
        }
        else{
            strategy = forecaststrategy;
        }
        System.out.println("Strategy deviation : "+strategy);
        return strategy;
    }
}
