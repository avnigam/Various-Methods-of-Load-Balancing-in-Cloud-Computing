/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class RandomBatch {
    
    int method=0;
    double forecastvalue,previousforecast=0.0;
    double payoff[]=new double[10000];
    double utility[]=new double[100];
    
    CheckForecastingMethod cfm=new CheckForecastingMethod();
    UtilityFunction uf=new UtilityFunction();
    ElFarolBar efb=new ElFarolBar();
    GameTheoreticEquilibrium gte=new GameTheoreticEquilibrium();
    Forecast f = new Forecast();
    Tuple t = new Tuple();
    Deviation d = new Deviation();
    
    int windowsize=7;
    int batchsize=10;
    
    int forecastingrent,forecastingown=0;
    int gamerent,gameown=0;
    int rentcount,owncount=0;
    double payoffmatrix[][];
    double strategyforecasting[]=new double[2];
    int strategygametheory;
    int finalstrategy;
    
    int conflict[]=new int[100];
    int batchsizetaken[]=new int[100];
    
    public void generate(){
        
        for(int count=0;count<1000;count++){
        
            batchsize=2+(int)(Math.random()*24);
            
            batchsizetaken[batchsize-1]++;
            t=uf.calculate(batchsize);
            payoff[count]=t.getAvgutility();
            utility=t.getUtility();
            payoffmatrix=t.getPayoffmatrix();
            double gtsd, fvsd;
            int j=0;
            if(count>=7){
                if(batchsize==1)
                    finalstrategy=0;
                else{
                    double window[]=new double[windowsize];
                        for(j=0;j<windowsize;j++){   

                            window[j]=payoff[count+j-windowsize];
                        }
                        method=cfm.check(window,windowsize);

                        strategyforecasting=f.calcForecast(method, window, windowsize, previousforecast, payoff, count);
                        previousforecast=strategyforecasting[0];  
                        
                        if((int)strategyforecasting[1]==0)
                            forecastingown++;
                        else
                            forecastingrent++;

                        
                        strategygametheory=gte.calcequilibrium(payoffmatrix, batchsize);

                        if(strategygametheory==0)
                            gameown++;
                        else
                            gamerent++;
                        
                        
                        if(strategygametheory!=(int)strategyforecasting[1]){
                            conflict[batchsize-1]++;
                            gtsd=gte.getStandarddeviation();
                            fvsd=f.calcStandardDeviation(batchsize, utility, previousforecast);
                            finalstrategy=d.calcStrategy(gtsd, fvsd, strategygametheory,(int)strategyforecasting[1]);
                            //finalstrategy=efb.calcstrategy(strategyforecasting[0], utility, batchsize);
                            //finalstrategy=d.calcstrategy(strategyforecasting[0],utility, batchsize, gte.getEquilibrium(),payoff[count],strategygametheory);
                        }

                        else{
                            finalstrategy=strategygametheory;
                        }
                }        
                    if(finalstrategy==0){
                        owncount++;
                        //System.out.println("Own: "+owncount);
                    }
                    else{
                        rentcount++;
                        //System.out.println("Rent: "+rentcount);
                    }
                    System.out.println();
                    System.out.println();
                }
            
            else
            {
                finalstrategy = (int)Math.random()*2;
                
                if(finalstrategy==0){
                        owncount++;
                        //System.out.println("Own: "+owncount);
                    }
                else{
                        rentcount++;
                        //System.out.println("Rent: "+rentcount);
                    }
                previousforecast=payoff[count];
            }            
        }
        
        System.out.println("Game Strategy Rent : "+gamerent);
        System.out.println("Game Strategy Own : "+gameown);
        
        System.out.println("Forecasting Strategy Rent : "+forecastingrent);
        System.out.println("Forecasting Strategy Own : "+forecastingown);
        
        System.out.println("Final Strategy Rent : "+rentcount);
        System.out.println("Final Strategy Own : "+owncount);
        
        /*for(int i=0;i<25;i++){
            System.out.println("Precantage conflict on batchsize : "+(i+1) +": "+((conflict[i]/(double)batchsizetaken[i])*100));
        }*/

    }
}
