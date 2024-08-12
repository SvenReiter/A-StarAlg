import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class A_StarAlgTest {
    float[][]coordsA= new float[][]{
//              S,   a    ,   b       ,    c   ,    d   ,    e,    f   ,    g   ,    h,     i,    j      ,    k      ,    l   ,    m,   n
            {5,4},{2.5f,4},{3.5f,2.5f},{7,2.5f},{8,4.5f},{7,6},{4.5f,6},{2,6.5f},{1,5},{11,5},{9.5f,2.5f},{7.5f,0.5f},{5,0.5f},{2,1},{0.5f,2.5f}
    };
    float[][]adjacencyA= new float[][]{
//              S    a    b    c    d    e    f    g    h    i    j    k    l    m    n
/*    S   */{   0,   0,1.6f,   0,2.5f,2.4f,1.6f,   0,   0,   0,   0,   0,2.9f,   0,   0},
/*    a   */{   0,   0,1.3f,   0,   0,   0,2.4f,   0,1.3f,   0,   0,   0,   0,2.5f,   0},
/*    b   */{1.6f,1.3f,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1.9f,1.5f,   0},
/*    c   */{   0,   0,   0,   0,2.8f,   0,   0,   0,   0,   0,  2f,   0,2.3f,   0,   0},
/*    d   */{2.5f,   0,   0,2.8f,   0,1.4f,   0,   0,   0,2.5f,1.9f,   0,   0,   0,   0},
/*    e   */{2.4f,   0,   0,   0,1.4f,   0,2.1f,   0,   0,3.6f,   0,   0,   0,   0,   0},
/*    f   */{1.6f,2.4f,   0,   0,   0,2.1f,   0,2.1f,   0,   0,   0,   0,   0,   0,   0},
/*    g   */{   0,   0,   0,   0,   0,   0,2.1f,   0,1.4f,   0,   0,   0,   0,   0,   0},
/*    h   */{   0,1.3f,   0,   0,   0,   0,   0,1.4f,   0,   0,   0,   0,   0,   0,2.2f},
/*    i   */{   0,   0,   0,   0,2.5f,3.6f,   0,   0,   0,   0,2.3f,   0,   0,   0,   0},
/*    j   */{   0,   0,   0,  2f,1.9f,   0,   0,   0,   0,2.3f,   0,2.3f,   0,   0,   0},
/*    k   */{   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,2.3f,   0,  2f,   0,   0},
/*    l   */{2.9f,   0,1.9f,2.3f,   0,   0,   0,   0,   0,   0,   0,  2f,   0,   0,   0},
/*    m   */{   0,2.5f,1.5f,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,2.7f},
/*    n   */{   0,   0,   0,   0,   0,   0,   0,   0,2.2f,   0,   0,   0,   0,2.7f,   0}
    };

    float[][]coordsB= new float[][]{
//              S   ,   a   ,   b   ,    c ,    d ,     e ,    f ,    g ,    h ,     i
            {25,11} ,{21,14},{19,11},{21,9},{16,8},{14,13},{11,8},{13,5},{12,1},{1,11}
    };
    float[][]adjacencyB= new float[][]{
//               S    a    b    c    d    e    f    g     h     i
/*    S   */{    0,   0,  6f,4.5f,   0,   0,   0,   0,16.4f,    0},
/*    a   */{    0,   0,3.6f,   0,   0,   0,   0,   0,    0,    0},
/*    b   */{   6f,3.6f,   0,   0,4.2f,5.4f,   0,   0,    0,    0},
/*    c   */{ 4.5f,   0,   0,   0,   0,   0,   0,8.9f,    0,    0},
/*    d   */{    0,   0,4.2f,   0,   0,   0,  5f,   0,    0,    0},
/*    e   */{    0,   0,5.4f,   0,   0,   0,5.8f,   0,    0,    0},
/*    f   */{    0,   0,   0,   0,  5f,5.8f,   0,3.6f,    0,    0},
/*    g   */{    0,   0,   0,8.9f,   0,   0,3.6f,   0,    0,    0},
/*    h   */{16.4f,   0,   0,   0,   0,   0,   0,   0,    0,15.6f},
/*    i   */{    0,   0,   0,   0,   0,   0,   0,   0,15.6f,    0}
    };

    @Test
    void aTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyA, coordsA, 0, 14);
        A_StarElm elm= a.closed.getElmById(14);
        assertAll("Cheapest rout from node S -> n",
                () -> assertEquals(13, elm.parent.id),
                () -> assertEquals(2, elm.parent.parent.id),
                () -> assertEquals(0, elm.parent.parent.parent.id));
    }

    @Test
    void aaTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyA, coordsA, 11, 14);
        A_StarElm elm= a.closed.getElmById(14);
        assertAll("Cheapest rout from node k -> n",
                () -> assertEquals(13, elm.parent.id),
                () -> assertEquals(2, elm.parent.parent.id),
                () -> assertEquals(12, elm.parent.parent.parent.id),
                () -> assertEquals(11, elm.parent.parent.parent.parent.id));
    }

    @Test
    void aaaTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyA, coordsA, 5, 14);
        A_StarElm elm= a.closed.getElmById(14);
        assertAll("Cheapest rout from node e -> n",
                () -> assertEquals(13, elm.parent.id),
                () -> assertEquals(2, elm.parent.parent.id),
                () -> assertEquals(0, elm.parent.parent.parent.id),
                () -> assertEquals(5, elm.parent.parent.parent.parent.id));
    }

    @Test
    void aaaaTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyA, coordsA, 6, 14);
        A_StarElm elm= a.closed.getElmById(14);
        assertAll("Cheapest rout from node f -> n",
                () -> assertEquals(8, elm.parent.id),
                () -> assertEquals(1, elm.parent.parent.id),
                () -> assertEquals(6, elm.parent.parent.parent.id));
    }

    @Test
    void bTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyB, coordsB, 0, 9);
        A_StarElm elm= a.closed.getElmById(9);
        assertAll("Cheapest rout from node S -> i",
                () -> assertEquals(8, elm.parent.id),
                () -> assertEquals(0, elm.parent.parent.id));
    }

    @Test
    void bbTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyB, coordsB, 6, 1);
        A_StarElm elm= a.closed.getElmById(1);
        assertAll("Cheapest rout from node a -> f",
                () -> assertEquals(2, elm.parent.id),
                () -> assertEquals(4, elm.parent.parent.id),
                () -> assertEquals(6, elm.parent.parent.parent.id));
    }

    @Test
    void bbTestsRoutBackwards() {
        A_StarAlg a= new A_StarAlg(adjacencyB, coordsB, 1, 6);
        A_StarElm elm= a.closed.getElmById(6);
        assertAll("Cheapest rout from node a -> f",
                () -> assertEquals(4, elm.parent.id),
                () -> assertEquals(2, elm.parent.parent.id),
                () -> assertEquals(1, elm.parent.parent.parent.id));
    }

    @Test
    void bbbTestsRout() {
        A_StarAlg a= new A_StarAlg(adjacencyB, coordsB, 5, 4);
        A_StarElm elm= a.closed.getElmById(4);
        assertAll("Cheapest rout from node e -> d",
                () -> assertEquals(2, elm.parent.id));
    }
}
