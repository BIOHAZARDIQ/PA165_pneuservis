package cz.muni.fi.pa165_pneuservis.service.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides helper methods for service test package
 * @author Jozef.Sumaj
 */
public class ServiceTestHelper {
    
    public static <T> List<T> toList(T[] array)
    {
        List<T> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));
        return list;
    }
}
