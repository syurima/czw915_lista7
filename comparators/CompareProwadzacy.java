package comparators;

import java.util.Comparator;
import classes.*;
public class CompareProwadzacy implements Comparator<Kurs> {
    public int compare(Kurs a, Kurs b){
        return a.getProwadzacy().getNazwisko().compareTo(b.getProwadzacy().getNazwisko());
    }
}
