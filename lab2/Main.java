public class Main {
    public static void main(String[] args) {
        SymbolTable<String> symbolTable = new SymbolTable<>(10);

        symbolTable.insert("key1");
        symbolTable.insert("key2");
        symbolTable.insert("key3");

        Pair position1 = symbolTable.getPosition("key1");
        Pair position2 = symbolTable.getPosition("key2");
        Pair position3 = symbolTable.getPosition("key3");

        String key1 = symbolTable.getKey(position1);
        String key2 = symbolTable.getKey(position2);
        String key3 = symbolTable.getKey(position3);


        System.out.println("Position of key1: " + position1);
        System.out.println("Retrieved key1: " + key1);
        System.out.println("Position of key2: " + position2);
        System.out.println("Retrieved key2: " + key2);
        System.out.println("Position of key3: " + position3);
        System.out.println("Retrieved key3: " + key3);
    }
}
