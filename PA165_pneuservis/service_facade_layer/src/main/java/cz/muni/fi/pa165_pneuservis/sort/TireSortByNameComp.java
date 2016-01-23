package cz.muni.fi.pa165_pneuservis.sort;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.Comparator;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class TireSortByNameComp implements Comparator<Tire>{
    
    @Override
    public int compare(Tire t1, Tire t2) {
        return t1.getName().compareTo(t2.getName());
    }
}