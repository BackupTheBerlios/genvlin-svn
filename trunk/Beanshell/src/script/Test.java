/*
 * Test.java
 *
 * Created on 26. April 2006, 23:39
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

package script;

import de.genvlin.core.data.IntID;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import de.genvlin.core.plugin.PluginPool;

/**
 *
 * @author Peter Karich
 */
public class Test {
    
    /** Creates a new instance of Test */
    public Test() {
    }
    
    public Object obj(int n){
        return MainPool.getDefault().get(new IntID(n));
    }
    
    public void regression(int idX, int idY) {
        Object oX = obj(idX);
        Object oY = obj(idY);
        
        
        if(oX instanceof VectorInterface && oY instanceof VectorInterface) {
            VectorInterface y = (VectorInterface)oY;
            VectorInterface x = (VectorInterface)oX;
            
            XYPool xyPool = (XYPool)MainPool.getDefault().create(XYPool.class);
            XYInterface interfac = xyPool.add(x, y);
            xyPool.add(PluginPool.getDefault().getFitEngine().fit(interfac));
            PluginPool.getDefault().getPlotEngine().plot(xyPool, xyPool.getID());
        }
    }
    
    /**
     * This method calculates: n!/(n-m)!
     * I you want n!, try factorial(n,n);
     * @returns the result or 1 if m>n.
     */
    public long factorial(long n, long m)
    throws Exception {
        long res = 1;
        if(m<=0) throw new Exception("InvalidArgument! It should be m>0!");
        if(m>n) throw new Exception("InvalidArgument! It should be m<=n!");
        
        long start = n-m+1;
        
        // 0! == 1
        //if(start==0) start++;
        
        for(long i = start; i<= n ; i++)
            res *= i;
        
        return res;
    }
}
