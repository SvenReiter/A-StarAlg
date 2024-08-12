public class A_StarElm {
    public final int id;
    public A_StarElm parent;
    public float costs, heuristics;

    public A_StarElm(int idArg, A_StarElm patentArg, float costsArg, float heuristicsArg) {
        id= idArg;
        parent= patentArg;
        costs= costsArg;
        heuristics= heuristicsArg;
    }

    public String toString() {
        return ((id== 0)?"S":((char)(id+96))+"");
    }
}
