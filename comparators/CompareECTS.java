package comparators;

import java.util.Comparator;
import classes.*;
public class CompareECTS implements Comparator<Kurs> {
    public int compare(Kurs a, Kurs b){
        return a.getPunktyECTS() < b.getPunktyECTS() ? -1 : a.getPunktyECTS() > b.getPunktyECTS() ? 1 : 0;
    }
}
