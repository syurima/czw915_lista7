package classes;

public class WyplataBD implements Wyplata {
    public int liczWyplate(Object p){
        //stawka * etat * liczba godzin w etacie + 100 za każdą wydaną publikację* 4 tygodnie
        return ((PracownikBD)p).getPensja()*(((PracownikBD)p).getEtat()*40)*4+((PracownikBD)p).getLiczbaPublikacji()*100;
    }
}
