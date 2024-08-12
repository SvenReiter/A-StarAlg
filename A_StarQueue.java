package A_StarAlg2403150936;

import java.util.ArrayList;
import java.util.Comparator;

public class A_StarQueue extends ArrayList<A_StarElm> {

    @Override
    public boolean add(A_StarElm elm) {
        boolean result= super.add(elm);
        super.sort(sortByCombinedCostsAsc());
        return result;
    }

    private Comparator<A_StarElm> sortByCombinedCostsAsc() {
        Comparator<A_StarElm> result= new Comparator<A_StarElm>() {
            @Override
            public int compare(A_StarElm elmA, A_StarElm elmB) {
                return  (elmA.costs+ elmA.heuristics)<  (elmB.costs+ elmB.heuristics) ?(-1):
                        (elmA.costs+ elmA.heuristics)== (elmB.costs+ elmB.heuristics) ?  0 : 1;
            }
        };
        return result;
    }

    public A_StarElm getElmById(int idArg) {
        for(A_StarElm elm: this) {
            if(elm.id== idArg) {
                return elm;
            }
        }
        return null;
    }

    public void removeElmById(int idToRemove) {
        A_StarElm toRemove= getElmById(idToRemove);
        this.remove(toRemove);
    }
}
