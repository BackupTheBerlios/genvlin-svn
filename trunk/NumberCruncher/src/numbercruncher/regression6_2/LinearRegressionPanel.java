/*
 * LinearRegressionPanel.java
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

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.RegressionLine;
import numbercruncher.pointutils.InterRegressPanel;

/**
 * The demo panel for the Linear Regression program and applet.
 *
 * @author: Ronald Mak - "Java Number Cruncher"; The Java Programmer's Guide To 
 * Numerical Computing 
 */
public class LinearRegressionPanel extends InterRegressPanel
{
    private static final int MAX_POINTS = 100;

    /** regression line function */
    RegressionLine line = new RegressionLine();

    /**
     * Constructor.
     */
    LinearRegressionPanel()
    {
        super(MAX_POINTS, "Regression line", "Reset", false);
    }

    /**
     * The user has added a data point.
     * @param r the dot's row
     * @param c the dot's column
     */
    protected void doDotAction(int r, int c)
    {
        if (n > 1) actionButton1.setEnabled(true);

        PlotProperties props = getPlotProperties();

        float x = props.getXMin() + c*props.getXDelta();
        float y = props.getYMax() - r*props.getYDelta();

        line.addDataPoint(new DataPoint(x, y));
    }

    /**
     * Button 1 action: Construct and plot the regression line.
     */
    protected void doButton1Action()
    {
        drawDots();
        plotOK = true;
        plotFunction();

        setHeaderLabel("Linear regression line:  y = " + line.getM() +
                       "x + " + line.getN());
    }

    /**
     * Button 2 action: Reset.
     */
    protected void doButton2Action()
    {
        reset();
        draw();

        setHeaderLabel("");
        actionButton1.setEnabled(false);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Return the value of the regression line function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return (float)line.at(x); }

    /**
     * Reset.
     */
    protected void reset()
    {
        super.reset();
        plotOK = false;
        line.reset();
    }
}
