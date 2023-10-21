public class Pair {
    private Integer bucket;
    private Integer position;

    public Pair(Integer bucket, Integer position) {
        this.bucket = bucket;
        this.position = position;
    }

    public Integer getBucket() {
        return this.bucket;
    }

    public Integer getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "(" + this.bucket + ", " + this.position + ")";
    }

}
