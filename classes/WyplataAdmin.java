package classes;

public class WyplataAdmin implements Wyplata {
    public int liczWyplate(Object p){
        return ((PracownikAdmin)p).getPensja()*(((PracownikAdmin)p).getEtat() * 40 * 4) + ((PracownikAdmin)p).getNadgodziny()*((PracownikAdmin)p).getPensja();
    }

}
