/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssladmin
 */
public class Tuple {
    double utility[]=new double[10];
    double avgutility;
    double payoffmatrix[][]=new double[10][2];

    public double[][] getPayoffmatrix() {
        return payoffmatrix;
    }

    public void setPayoffmatrix(double[][] payoffmatrix) {
        this.payoffmatrix = payoffmatrix;
    }

    public double getAvgutility() {
        return avgutility;
    }

    public void setAvgutility(double avgutility) {
        this.avgutility = avgutility;
    }

    public double[] getUtility() {
        return utility;
    }

    public void setUtility(double[] utility) {
        this.utility = utility;
    }
    
    public Tuple(double utility[],double avgutility,double payoffmatrix[][]){
        this.avgutility=avgutility;
        this.utility=utility;
        this.payoffmatrix=payoffmatrix;
    }
    
    public Tuple(){
        
    }
}
