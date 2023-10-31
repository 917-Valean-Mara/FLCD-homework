import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
    SymbolTable<String> symbolTable = new SymbolTable<String>(10);
    ProgramInternalForm pif = new ProgramInternalForm();
    Lexic lexic = new Lexic();

    public Scanner(){
    }
    public void scan(String fileName){
        try{
            File file = new File(fileName);
            StringBuilder result = new StringBuilder();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                int i = 0;
                String line;

                while ((line=bufferedReader.readLine())!=null){
                    List<String> tokensList = tokenizeLine(line);
                    readLine(tokensList, i, result);
                    i++;
                }
            }

            writePif(fileName,result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readLine(List<String> tokensList, int line, StringBuilder result) {
        for (int i=0;i<tokensList.size();++i){
            String token=tokensList.get(i);

            if (isConstant(token)){
                symbolTable.insert(token);
                Pair pos = symbolTable.getPosition(token);
                pif.add("const",pos);
                result.append("Token CONST " + token + " on position " + pos + "\n");
            }

            else if (operator(token) || separator(token) || reservedWord(token)){
                Pair pos = new Pair(-1,-1);
                pif.add(token,pos);
                if(operator(token))
                    result.append("Token OPERATOR " + token + " on position " + -1 +"\n");
                else if (separator(token))
                    result.append("Token SEPARATOR " + token + " on position " + -1 +"\n");
                else if (reservedWord(token))
                    result.append("Token RESERVED WORD "+ token +" on position " + -1 + "\n");
            }

            else if (isIdentifier(token)){
                symbolTable.insert(token);
                Pair pos = symbolTable.getPosition(token);
                pif.add("id",pos);
                result.append("Token IDENTIFIER " + token + " on position " + pos +  "\n");
            }


            else {
                result.append("\nLexical ERROR at line → " + line + ". Invalid token → " + token + "\n\n");
            }
        }
    }

    private List<String> tokenizeLine(String line) {
        List<String> tokens = new ArrayList<>();
        int i = 0;

        while (i < line.length()) {

            if (line.charAt(i) == '"') {
                // users declared strings
                StringBuilder stringToken = new StringBuilder("\"");
                i++;
                while (i < line.length() && line.charAt(i) != '"') {
                    stringToken.append(line.charAt(i));
                    i++;
                }
                stringToken.append("\"");
                i++;
                tokens.add(stringToken.toString());
            }
            else if (separator(String.valueOf(line.charAt(i)))) {
                // separators excluding space, tab and new line
                if (!(line.charAt(i) == ' ') && !(line.charAt(i) == '\t') && !(line.charAt(i) == '\n')) {
                    tokens.add(String.valueOf(line.charAt(i)));
                }
                i++;
            }
            else if (operator(String.valueOf(line.charAt(i)))) {
                // composed operators
                if(operator(String.valueOf(line.charAt(i + 1)))) {
                    tokens.add(line.charAt(i) + String.valueOf(line.charAt(i + 1)));
                    i = i + 2;
                }
                else {
                    tokens.add(String.valueOf(line.charAt(i)));
                    i++;
                }
            }
            else {
                // other tokens like reserved words, constants and identifiers
                StringBuilder token = new StringBuilder(String.valueOf(line.charAt(i)));
                i++;
                while (i < line.length() && !separator(String.valueOf(line.charAt(i))) && !operator(String.valueOf(line.charAt(i)))) {
                    token.append(line.charAt(i));
                    i++;
                }
                tokens.add(token.toString());
            }
        }

        return tokens;
    }

    public void writePif(String outputFile, StringBuilder result){
        try{
            outputFile = "lab2/output.txt";
            String pifFile = "lab2/pif.txt";
            String stFile = "lab2/st.txt";
            FileWriter fileWriterOutput= new FileWriter(outputFile);
            FileWriter fileWriterST= new FileWriter(stFile);
            FileWriter fileWriterPif= new FileWriter(pifFile);
            fileWriterST.write(symbolTable.toString());
            fileWriterOutput.write(result.toString());
            fileWriterPif.write(pif.toString());
            fileWriterST.close();
            fileWriterOutput.close();
            fileWriterPif.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public boolean operator(String operator){
        return this.lexic.getOperators().contains(operator);
    }

    public boolean separator(String separator){
        return this.lexic.getSeparators().contains(separator);
    }

    public boolean reservedWord(String reservedWord){
        return this.lexic.getRw().contains(reservedWord);
    }


    public boolean isNumber(String token){
        String number = "^([+|-]?[1-9][0-9]*)|0$";
        return token.matches(number);
    }

    public boolean isString(String token){
        String string = "^\"[a-zA-Z0-9_.:;,?!*' ]*\"$";
        return token.matches(string);
    }

    public boolean isConstant(String token){
        return isNumber(token) || isString(token);
    }

    public boolean isIdentifier(String token){
        String pattern = "^[a-zA-Z]([a-zA-Z0-9_]*$)";
        return token.matches(pattern);
    }

}