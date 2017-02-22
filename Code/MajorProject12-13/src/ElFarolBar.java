/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class ElFarolBar {    
    int count;
    int own=0;
    int rent=1;
    int strategy;
    int elfarol=0;
    
    public int calcstrategy(double forecast,double utility[],int batchsize){
        count=0;
        elfarol++;
        for(int i=0;i<batchsize;i++){
//            System.out.println("utility :  forecast : "+" "+utility[i]+"  "+forecast );
            if(utility[i]>forecast)
                count++;            
        }

        double percentage=0.0;
        percentage=(double)count/batchsize;
        
        System.out.println("Elfarol : "+elfarol);
        if(percentage<0.4){
            strategy=rent;
            //rent++;
            //System.out.println("Rent: "+rent);
        }
        else{
            strategy=own;
            //own++;
            //System.out.println("Own: "+own);
        }
        return strategy;
    }
}
