import java.util.Arrays;
import java.util.List;


public class Lexic {

    private final String[] operators = {"+","-","*","/","<",">","<=",">=","=","!=","==","%"};
    private final String[] separators = {"{","}","[","]","(",")",":"," ","\n",";"};
    private final String[] reservedWords = {"var","print","read","if","else","int","while","for","string","start","end"};
    public Lexic() {
    }

    public List<String> getOperators(){
        return Arrays.asList(operators);
    }

    public List<String> getSeparators(){
        return Arrays.asList(separators);
    }

    public List<String> getRw(){
        return Arrays.asList(reservedWords);
    }

}