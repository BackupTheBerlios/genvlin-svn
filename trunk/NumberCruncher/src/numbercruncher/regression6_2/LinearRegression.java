/*
 * LinearRegression.java
 *
 * Created on 26. April 2006, 13:38
 *
 * genvlin project.
 * Copyright (C) 2005, 2006 Peter Karich.
 *
 * This project is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * version 2.1 of the License.
 *
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this project; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * or look at http://www.gnu.org
 */

package numbercruncher.regression6_2;

import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.RegressionLine;

/**
 * PROGRAM 6-2: Linear Regression
 *
 * Demonstrate linear regression by constructing
 * the regression line for a set of data points.
 *
 * @author: Ronald Mak - "Java Number Cruncher"; The Java Programmer's Guide To 
 * Numerical Computing
 */
public class LinearRegression
{
    private static final int MAX_POINTS = 10;

    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        RegressionLine line = new RegressionLine();

        line.addDataPoint(new DataPoint(6.2f, 6.0f));
        line.addDataPoint(new DataPoint(1.3f, 0.75f));
        line.addDataPoint(new DataPoint(5.5f, 3.05f));
        line.addDataPoint(new DataPoint(2.8f, 2.96f));
        line.addDataPoint(new DataPoint(4.7f, 4.72f));
        line.addDataPoint(new DataPoint(7.9f, 5.81f));
        line.addDataPoint(new DataPoint(3.0f, 2.49f));

        printSums(line);
        printLine(line);
    }

    /**
     * Print the computed sums.
     * @param line the regression line
     */
    private static void printSums(RegressionLine line)
    {
        System.out.println("n      = " + line.size());
        System.out.println("Sum x  = " + line.getSumX());
        System.out.println("Sum y  = " + line.getSumY());
        System.out.println("Sum xx = " + line.getSumXX());
        System.out.println("Sum xy = " + line.getSumXY());
    }

    /**
     * Print the regression line function.
     * @param line the regression line
     */
    private static void printLine(RegressionLine line)
    {
        System.out.println("\nRegression line:  y = " +
                           line.getM() +
                           "x + " + line.getN());
    }
}
/*
Output:
n      = 7
Sum x  = 31.399999618530273
Sum y  = 25.77999973297119
Sum xx = 171.71999621391296
Sum xy = 138.7909932732582

Regression line:  y = 0.74993044x + 0.31888318
*/