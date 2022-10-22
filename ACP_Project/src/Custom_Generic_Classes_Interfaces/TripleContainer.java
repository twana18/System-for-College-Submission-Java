package Custom_Generic_Classes_Interfaces;

public class TripleContainer <K1, K2, K3> implements Triple<K1, K2, K3> {
        private K1 key1;
        private K2 key2;
        private K3 key3;
        public TripleContainer(K1 key1, K2 key2, K3 key3) {
            this.key1 = key1;
            this.key2 = key2;
            this.key3 = key3;
        }
        public K1 getKey1() { return key1; }
        public K2 getKey2() { return key2; }
        public K3 getKey3() { return key3; }

}
