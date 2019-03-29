import java.util.Hashtable;

public class testHash {

  public static void main(String[] args) {
    Hashtable<Integer, Integer> ds = new Hashtable<Integer, Integer>();
    for (int i = 0; i < 10; i++) {
      ds.put(i, i);
    }

  }

}
