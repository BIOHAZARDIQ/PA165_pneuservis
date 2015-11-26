package cz.muni.fi.pa165_pneuservis.sort;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.Comparator;

/**
 *
 * @author Jozef.Sumaj
 */
public class TireSortByWidthComp implements Comparator<Tire> {
    
    @Override
    public int compare(Tire t1, Tire t2) {
        return t1.getWidth() - t2.getWidth();
    }
}
