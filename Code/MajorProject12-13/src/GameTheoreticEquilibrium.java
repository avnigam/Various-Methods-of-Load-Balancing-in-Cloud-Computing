/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class GameTheoreticEquilibrium {
        double calcmatrix[][]=new double[2][2];
        double p=0.0;
        int own=0;
        int rent=1;
        double arr[]=new double[100];
        int strategy;
        double equilibrium;
        double standarddeviation;

        public double getStandarddeviation() {
            return standarddeviation;
        }

        public double getEquilibrium() {
            return equilibrium;
        }
        
        public int calcequilibrium(double payoffmatrix[][],int batchsize){

              for(int i=0;i<batchsize;i++){
                    arr[i]=payoffmatrix[i][0];
                }
                int rentindex = maximum(arr);

                for(int i=0;i<batchsize;i++){
                    arr[i]=payoffmatrix[i][1];
                }
                int ownindex = maximum(arr);

                if(ownindex==rentindex){
                    arr[ownindex]=0;
                    ownindex=maximum(arr);
                }
                if(batchsize==2){
                    calcmatrix[0][0]=payoffmatrix[0][0];  // a
                    calcmatrix[0][1]=payoffmatrix[0][1];  // b
                    calcmatrix[1][0]=payoffmatrix[1][0];   // c
                    calcmatrix[1][1]=payoffmatrix[1][1];
                }
                else{
                    calcmatrix[0][0]=payoffmatrix[rentindex][0];  // a
                    calcmatrix[0][1]=payoffmatrix[rentindex][1];  // b
                    calcmatrix[1][0]=payoffmatrix[ownindex][0];   // c
                    calcmatrix[1][1]=payoffmatrix[ownindex][1];  // d
                }
                p= (calcmatrix[1][1]-calcmatrix[0][1])/((calcmatrix[0][0]-calcmatrix[0][1])+(calcmatrix[1][1]-calcmatrix[1][0]));

                if(p<0.5){
                    strategy=own;
                }
                else{
                    strategy=rent;
                }
                equilibrium=p*calcmatrix[0][0]+(1-p)*calcmatrix[0][1];
            standarddeviation = calcStandardDeviation(payoffmatrix, batchsize, p, equilibrium);
            return strategy;
        }
        
        private int maximum(double t[]){
            int j=0;
            double maximum = t[0];   // start with the first value
            for (int i=1; i<t.length; i++) {
                if (t[i] > maximum) {
                    maximum = t[i];
                    j=i;// new maximum
                }
            }
            return j;
        }
        
        public double calcStandardDeviation(double payoffmatrix[][],int batchsize,double p, double equilibrium){
            double value,deviation,sd, sum=0.0;
            
            for(int i=0;i<batchsize;i++){
                value = p*payoffmatrix[i][0]+(1-p)*payoffmatrix[i][1];
                deviation=equilibrium-value;
                sum = sum+(deviation*deviation);
            }
            
            sd = Math.sqrt(sum/batchsize);
            return sd;
        }        
}
