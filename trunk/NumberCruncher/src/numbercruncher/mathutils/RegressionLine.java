package numbercruncher.mathutils;

import com.sun.rsasign.a0;
import com.sun.rsasign.a1;

/**
 * A least-squares regression line function.<br>
 * y = m*x + n<br>
 * some changes by Peter Karich.
 */
public class RegressionLine {
    /** sum of x */     private double sumX;
    /** sum of y */     private double sumY;
    /** sum of x*x */   private double sumXX;
    /** sum of x*y */   private double sumXY;
    /** sum of y*y */   private double sumYY;
    
    /** line coefficient n */  private double n;
    /** line coefficient m */  private double m;
    
    /** number of data points */        private int     counts;
    /** true if coefficients valid */   private boolean coefsValid;
    
    /* delta == varianz*/ private double delta;
     
    /**
     * Constructor.
     */
    public RegressionLine() {}
    
    /**
     * Constructor.
     * @param data the array of data points
     */
    public RegressionLine(DataPoint data[]) {
        for (int i = 0; i < data.length; ++i) {
            addDataPoint(data[i]);
        }
    }
    
    /**
     * Add a new data point: Update the sums.
     * @param dataPoint the new data point
     */
    public void addDataPoint(DataPoint dataPoint) {
        sumX  += dataPoint.x;
        sumY  += dataPoint.y;
        sumXX += dataPoint.x*dataPoint.x;
        sumXY += dataPoint.x*dataPoint.y;
        sumYY += dataPoint.y*dataPoint.y;
        ++counts;
        coefsValid = false;
    }
    
    /**
     * Return the current number of data points.
     * @return the count
     */
    public int size() {
        return counts;
    }
    
    /**
     * Return the coefficient a0.
     * @return the value of a0
     */
    public double getN() {
        validateCoefficients();
        return n;
    }
    
    /**
     * Return the coefficient a1.
     * @return the value of a1
     */
    public double getM() {
        validateCoefficients();
        return m;
    }
    
    public double getMError() {
        validateCoefficients();
        return delta/Math.sqrt(sumXX - sumX*sumX/counts);
    }
    public double getNError() {
        validateCoefficients();
        return delta/Math.sqrt(sumXX/counts/(sumXX - sumX*sumX/counts));
    }
    public double getCorrelationCoeff() {
        validateCoefficients();
        return 0;
    }
    
    
    /**
     * Return the sum of the x values.
     * @return the sum
     */
    public double getSumX() { return sumX; }
    
    /**
     * Return the sum of the y values.
     * @return the sum
     */
    public double getSumY() { return sumY; }
    
    /**
     * Return the sum of the x*x values.
     * @return the sum
     */
    public double getSumXX() { return sumXX; }
    
    /**
     * Return the sum of the x*y values.
     * @return the sum
     */
    public double getSumXY() { return sumXY; }
    
    /**
     * Return the sum of the y*y values.
     * @return the sum
     */
    public double getSumYY() { return sumYY; }        
    
    /**
     * Return the value of the regression line function at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the value of the function at x
     */
    public double at(double x) {
        if (counts < 2) return Double.NaN;
        
        validateCoefficients();
        return n + m*x;
    }
    
    /**
     * Reset.
     */
    public void reset() {
        counts = 0;
        sumX = sumY = sumXX = sumXY = sumYY = 0;
        coefsValid = false;
    }
    
    /**
     * Validate the coefficients.
     */
    private void validateCoefficients() {
        if (coefsValid) return;
        
        if (counts >= 2) {
            //double xBar = (double) sumX/counts;
            //double yBar = (double) sumY/counts;
            m = (double) ((counts*sumXY - sumX*sumY)/(counts*sumXX - sumX*sumX));
            n = (double) ((sumY*sumXX/counts - sumX*sumXY/counts)/(sumXX - sumX*sumX/counts));
            //delta^2 = SUM((y_i - yBar_i)^2); where yBar_i = at(x_i);
            //=> delta^2 = SUM( y_i^2 - 2*(y_i*m*x_i + y_i*n) + ((m*x_i)^2 + 2*m*x_i*n + n^2))
            delta = Math.sqrt((sumYY - 2*(m*sumXY + n*sumY) 
            + (m*m*sumXX + 2*m*n*sumX + n*n*counts*(counts+1)/2))/(counts-1));
        } else {
            n = m = Double.NaN;
        }
        
        coefsValid = true;
    }
}