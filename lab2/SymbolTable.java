import java.util.ArrayList;

public class SymbolTable<T> {
    private Integer size;
    private ArrayList<ArrayList<T>> table;

    public SymbolTable(Integer size) {
        this.size = size;
        this.table = new ArrayList<ArrayList<T>>();
        for(int i = 0; i < size; i++) {
            this.table.add(new ArrayList<T>());
        }
    }

    //hash function using sum of ASCII codes of chars (can i use the hashcode() method?
    private Integer hash(T key) {
        Integer hash = 0;
        for(int i = 0; i < ((String) key).length(); i++) {
            hash += ((String) key).charAt(i);
        }
        return hash % this.size;
    }

    public void insert(T key) {
        //check if key is already in table
        if (this.table.get(this.hash(key)).contains(key)) {
            return;
        }
        Integer bucket = this.hash(key);
        this.table.get(bucket).add(key);

    }
   
    public Pair getPosition(T key) {
        Integer bucket = this.hash(key);
        ArrayList<T> bucketList = this.table.get(bucket);
        Integer position = bucketList.indexOf(key);
        return new Pair(bucket, position);
    }

    public T getKey(Pair pair) {
        return this.table.get(pair.getBucket()).get(pair.getPosition());
    }
    


}