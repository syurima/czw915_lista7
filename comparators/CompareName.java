package comparators;

import java.util.Comparator;
import classes.*;
public class CompareName implements Comparator<Osoba> {
    public int compare(Osoba a, Osoba b){
        return a.getImie().compareToIgnoreCase(b.getImie());
    }
}
