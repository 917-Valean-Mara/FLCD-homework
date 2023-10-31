public class PIFpair {
    private Pair position;
    private String token;

    public PIFpair(String token,Pair position) {
        this.position = position;
        this.token = token;
    }

    public Pair getPosition() {
        return this.position;
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public String toString() {
        return "(" + this.token + " at  " + this.position + ")";
    }
}
