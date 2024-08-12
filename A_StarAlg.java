package A_StarAlg2403150936;

import java.util.Objects;

public class A_StarAlg {
    final float adjacency[][];
    final float heuristics[];
    final int TARGET_ID;
    A_StarQueue open, closed;

    public A_StarAlg(float adjacencyArg[][], float coordsArg[][], int rootIdArg, int targetIdArg) {
        adjacency= adjacencyArg;
        heuristics= new float[coordsArg.length];
        for(int i=0; i< heuristics.length; ++i) {
            heuristics[i]= (float)Math.sqrt( Math.pow(coordsArg[i][0]-coordsArg[targetIdArg][0],2) +
                                             Math.pow(coordsArg[i][1]-coordsArg[targetIdArg][1],2));
        }
        TARGET_ID= targetIdArg;
        open= new A_StarQueue();
        closed= new A_StarQueue();
        open.add(new A_StarElm(rootIdArg, null, 0f, heuristics[rootIdArg]));

        traverse(open.get(0));
    }

    private void traverse(A_StarElm node) {
        if(node.id== TARGET_ID) {
            closed.add(node);
            open.removeElmById(node.id);
            return;
        }
        for(int i=0; i< adjacency[node.id].length; ++i) {
            if(adjacency[node.id][i]!= 0f && !closed.contains(node)) {      //&& die Differenz zum
                final A_StarElm childNode= open.getElmById(i);
                if(Objects.isNull(childNode) && Objects.isNull(closed.getElmById(i))) {
                    open.add(new A_StarElm(i, node, (node.costs + adjacency[node.id][i]), heuristics[i]));
                }else if(!Objects.isNull(childNode)) {
                    final float newCosts= node.costs+ adjacency[node.id][i];
                    if(childNode.costs > newCosts) {
                        childNode.parent= node;
                        childNode.costs= newCosts;
                    }
                }
            }
        }
        closed.add(node);
        open.removeElmById(node.id);
        
        if(open.size()>0) {
            traverse(open.get(0));
        }
    }
}
