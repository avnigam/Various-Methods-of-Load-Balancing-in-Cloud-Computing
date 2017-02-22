/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class UtilityFunction {
    int jobsize[]=new int[100];
    int exectime[]=new int[100];
    double waitingprob[]=new double[100];
    double avgexectime=0.0;
    double utility[]=new double[100];
    double avgutility;
    double payoffmatrix[][]=new double[100][2];
    Tuple t = new Tuple(utility, avgutility,payoffmatrix);
    
    public Tuple calculate(int batchsize){
        double utility1=0.0;
        double utility2=0.0;
        double p=0.0;
        double p1=0.0;
        double p2=0.0;
        
            for(int j=0;j<batchsize;j++){
                jobsize[j]=1+(int)(Math.random()*10);
                exectime[j]=1+(int)(Math.random()*10);
                waitingprob[j]=jobsize[j]/(double)10;
                p=Math.random();
                if(p<0.5){
                    p1=waitingprob[j]*p;
                    p2=waitingprob[j]*(1-p);
                }
                else{
                    p2=waitingprob[j]*p;
                    p1=waitingprob[j]*(1-p);
                }
                
                avgexectime=(exectime[j]+avgexectime*(j))/(j+1);
                utility[j]=(((exectime[j]/avgexectime)+(Math.pow(jobsize[j],(1-waitingprob[j]))))/batchsize)*10;
                utility1=(((exectime[j]/avgexectime)+(Math.pow(jobsize[j],(1-(p1)))))/batchsize)*10;
                utility2=(((exectime[j]/avgexectime)+(Math.pow(jobsize[j],(1-(p2)))))/batchsize)*10;
             //u1<u2
                if((int)(Math.random()*2)==1){
                    payoffmatrix[j][0]=utility1;
                    payoffmatrix[j][1]=utility2;
                }
                else{
                    payoffmatrix[j][0]=utility2;
                    payoffmatrix[j][1]=utility1;
                }
                avgutility=avgutility+(utility[j]);
            }
            avgutility=avgutility/batchsize;
            
            t.setAvgutility(avgutility);
            t.setUtility(utility);
            t.setPayoffmatrix(payoffmatrix);
            return t;
    }    
}
