package comparators;

import classes.*;

import java.util.Comparator;
public class CompareWiek implements Comparator<Osoba> {
    public int compare(Osoba a, Osoba b){
        return a.getWiek() < b.getWiek() ? -1 : a.getWiek() > b.getWiek() ? 1 : 0;
    }
}
