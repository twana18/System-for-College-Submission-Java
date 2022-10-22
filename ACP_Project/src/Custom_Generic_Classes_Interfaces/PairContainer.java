package Custom_Generic_Classes_Interfaces;

public class PairContainer<K1, k2> implements Pair<K1, k2> {
    private K1 key1;
    private k2 key2;
    public PairContainer(K1 key1, k2 key2) {
        this.key1 = key1;
        this.key2 = key2;
    }
    public K1 getKey1() { return key1; }
    public k2 getKey2() { return key2; }
}
