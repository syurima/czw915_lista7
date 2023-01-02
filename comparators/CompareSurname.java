package comparators;

import java.util.Comparator;
import classes.*;
public class CompareSurname implements Comparator<Osoba> {
    public int compare(Osoba a, Osoba b){
        return a.getNazwisko().compareToIgnoreCase(b.getNazwisko());
    }
}
