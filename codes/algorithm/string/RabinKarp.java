package string;

public class RabinKarp {

    /*
     * Rabin-karp is an algorithms that finds whether given text has a string that matches a pattern by hashing.
     *
     * case 1 (different hash): not match
     * case 2 (same hash): match or not -> must check one by one character whether string are same.
     * */
    private final String pat; // target pattern
    private final int M; // length of pattern
    private final long patHash; // hash of pattern
    private final int R = 256; // radix of data (8 bits for ASCII)
    private final long Q = 997; // Random prime number for modular
    private long RM; // R^(M-1) % Q (use for removing leading digit)

    public RabinKarp(String pat) {
        // init member field
        this.pat = pat;
        M = pat.length();
        patHash = hash(pat, M);

        // precompute RM
        RM = 1;
        for (int i = 0; i < M - 1; i++) {
            RM = (R * RM) % Q;
        }
    }

    public int search(String txt) {
        int N = txt.length();

        if (N < M) {
            return N; // can't match when text length is less than pattern length
        }

        long txtHash = hash(txt, M);
        if (txtHash == patHash && check(txt, 0)) {
            return 0; // match at start point of the text
        }

        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash && check(txt, i - M + 1)) {
                return i - M + 1; // match in the middle of text
            }
        }

        return N; // not match
    }

    private boolean check(String txt, int i) {
        // check one by one character (Las vegas: O(NM))
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false; // same hash but string does not match
            }
        }
        return true; // same hash and matching string
    }

    public long hash(String key, int M) {
        // hash function of Horner's rule
        long h = 0;
        for (int i = 0; i < M; i++) {
            h = (R * h + key.charAt(i)) % Q;
        }

        return h;
    }

    public static void main(String[] args) {
        String pattern = "World!";
        String text = "Hello World! Greeting!";

        RabinKarp rk = new RabinKarp(pattern);
        int index = rk.search(text);

        System.out.println(index);
    }

}
