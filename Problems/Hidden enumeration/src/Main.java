public class Main {
    enum Secret {
        STAR, CRASH, START, // ...
    }
    public static void main(String[] args) {
        int count = 0;
        for (Secret scr: Secret.values()) {
            if (scr.toString().startsWith("STAR")) {
                count++;
            }
        }
        System.out.println(count);

    }
}

/* sample enum for inspiration
   enum Secret {
    STAR, CRASH, START, // ...
}
*/