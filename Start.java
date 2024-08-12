import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Start {
    final static int ROOT_ID= 8; //14; //n   //8;            // h
    final static int TARGET_ID= 9; //9;  // i    //10;         // j
    static float[][]coords= new float[][]{      //absolute coords of every node to calculate the heuristics
//              S ,   a    ,   b       ,    c   ,    d   ,   e ,    f   ,    g   ,  h  ,   i  ,    j       ,    k      ,    l   ,  m  ,   n
            {5,4} ,{2.5f,4},{3.5f,2.5f},{7,2.5f},{8,4.5f},{7,6},{4.5f,6},{2,6.5f},{1,5},{11,5},{9.5f,2.5f} ,{7.5f,0.5f},{5,0.5f},{2,1},{0.5f,2.5f}
    };
    static float[][]adjacency= new float[][]{   //adjacency{length of edges between nodes}
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

    static byte[][]edges= new byte[][]{
//              S    a    b    c    d    e    f    g    h    i    j    k    l    m    n
/*    S   */{   0,   0,   1,   0,   1,   1,   1,   0,   0,   0,   0,   0,   1,   0,   0},
/*    a   */{   0,   0,   1,   0,   0,   0,   1,   0,   1,   0,   0,   0,   0,   1,   0},
/*    b   */{   1,   1,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   1,   1,   0},
/*    c   */{   0,   0,   0,   0,   1,   0,   0,   0,   0,   0,   1,   0,   1,   0,   0},
/*    d   */{   1,   0,   0,   1,   0,   1,   0,   0,   0,   1,   1,   0,   0,   0,   0},
/*    e   */{   1,   0,   0,   0,   1,   0,   1,   0,   0,   1,   0,   0,   0,   0,   0},
/*    f   */{   1,   1,   0,   0,   0,   1,   0,   1,   0,   0,   0,   0,   0,   0,   0},
/*    g   */{   0,   0,   0,   0,   0,   0,   1,   0,   1,   0,   0,   0,   0,   0,   0},
/*    h   */{   0,   1,   0,   0,   0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   1},
/*    i   */{   0,   0,   0,   0,   1,   1,   0,   0,   0,   0,   1,   0,   0,   0,   0},
/*    j   */{   0,   0,   0,   1,   1,   0,   0,   0,   0,   1,   0,   1,   0,   0,   0},
/*    k   */{   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   1,   0,   1,   0,   0},
/*    l   */{   1,   0,   1,   1,   0,   0,   0,   0,   0,   0,   0,   1,   0,   0,   0},
/*    m   */{   0,   1,   1,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   1},
/*    n   */{   0,   0,   0,   0,   0,   0,   0,   0,   1,   0,   0,   0,   0,   1,   0}
    };

    static float[][]getAdjacency(byte[][]edges, float[][]coords) {
        float[][]result= new float[edges.length][edges[0].length];
        for(int y=0; y<result.length; ++y) {
            for(int x=0; x<result[0].length; ++x) {
                if(edges[y][x] == (byte)1) {
                    result[y][x]= (float)Math.sqrt( Math.pow(coords[y][0]-coords[x][0],2) +
                                                    Math.pow(coords[y][1]-coords[x][1],2)
                                                  );
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        float[][]adjacency= getAdjacency(edges, coords);
        A_StarAlg a= new A_StarAlg(adjacency, coords, ROOT_ID, TARGET_ID);  //result of A_StarAlg is closed[]

        //A_StarAlgs result is closed[]
        A_StarElm target= a.closed.getElmById(TARGET_ID);

        List<A_StarElm> routeIds= new ArrayList<>();
        routeIds.add(target);

        //to retrieve the route elements
        while(!Objects.isNull(target.parent)) {                     //as long as <target.parent> is not NULL
            target= target.parent;                                  //<target> points on its parent
            routeIds.add(target);                                   //adds new <target> to <routIds>
        }

        //to iterate the routes elements to opposite direction
        for(int i= routeIds.size()-1; i>0; --i) {
            System.out.print(routeIds.get(i).toString()+" -> ");
        }
        System.out.print(routeIds.get(0).toString());
    }
}
