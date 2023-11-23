import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FiniteAutomata {
    private Set<String> Q; // states
    private Set<String> E; // inputs
    private String q0; // initial state
    private Set<String> F; // final states
    private Map<FApair, Set<String>> S; // transitions

    public FiniteAutomata(Set<String> Q, Set<String> E, String q0, Set<String> F, Map<FApair, Set<String>> S) {
        this.Q = Q;
        this.E = E;
        this.q0 = q0;
        this.F = F;
        this.S = S;
    }

    public Set<String> getQ() {
        return Q;
    }

    public Set<String> getE() {
        return E;
    }

    public String getQ0() {
        return q0;
    }

    public Set<String> getF() {
        return F;
    }

    public Map<FApair, Set<String>> getS() {
        return S;
    }

    public static FiniteAutomata readFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Set<String> Q = getLine(reader.readLine(), "Q");
            Set<String> E = getLine(reader.readLine(), "E");
            String q0 = getLine(reader.readLine(), "q0").iterator().next();
            Set<String> F = getLine(reader.readLine(), "F");

            // Read the transitions
            reader.readLine(); // Skip the line with '{'
            Map<FApair, Set<String>> S = new HashMap<>();
            String line;
            while (!(line = reader.readLine()).equals("}")) {
                String[] parts = line.split(",");
                String src = parts[0].trim();
                String route = parts[1].trim();
                String dst = parts[2].trim();

                FApair key = new FApair(src, route);
                S.computeIfAbsent(key, k -> new HashSet<>()).add(dst);
            }

            if (!validate(Q, E, q0, F, S)) {
                throw new IllegalArgumentException("Wrong input file.");
            }

            return new FiniteAutomata(Q, E, q0, F, S);
        }
    }

    private static Set<String> getLine(String line, String key) {
        String[] parts = line.split("=");
        if (parts.length == 2 && parts[0].trim().equals(key)) {
            String[] values = parts[1].split(",");
            Set<String> result = new HashSet<>();
            for (String value : values) {
                result.add(value.trim());
            }
            return result;
        } else {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
    }

    private static boolean validate(Set<String> Q, Set<String> E, String q0, Set<String> F, Map<FApair, Set<String>> S) {
        if (!Q.contains(q0)) {
            return false;
        }

        for (String f : F) {
            if (!Q.contains(f)) {
                return false;
            }
        }

        for (FApair key : S.keySet()) {
            String state = key.getState();
            String symbol = key.getSymbol();

            if (!Q.contains(state) || !E.contains(symbol)) {
                return false;
            }

            for (String dest : S.get(key)) {
                if (!Q.contains(dest)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isDfa() {
        for (FApair key : S.keySet()) {
            if (S.get(key).size() > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isAccepted(String seq) {
        if (isDfa()) {
            String currentState = q0;
            for (char symbol : seq.toCharArray()) {
                FApair key = new FApair(currentState, Character.toString(symbol));
                if (S.containsKey(key)) {
                    currentState = S.get(key).iterator().next();
                } else {
                    return false;
                }
            }
            return F.contains(currentState);
        }
        return false;
    }

    public void displayElements() {
        System.out.println("1. Set of states (Q): " + Q);
        System.out.println("2. Alphabet (E): " + E);
        System.out.println("3. Transitions (S): " + S);
        System.out.println("4. Initial state (q0): " + q0);
        System.out.println("5. Set of final states (F): " + F);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Display set of states (Q)");
            System.out.println("2. Display alphabet (E)");
            System.out.println("3. Display transitions (S)");
            System.out.println("4. Display initial state (q0)");
            System.out.println("5. Display set of final states (F)");
            System.out.println("6. Verify if a sequence is accepted (DFA only)");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Set of states (Q): " + Q);
                    break;
                case 2:
                    System.out.println("Alphabet (E): " + E);
                    break;
                case 3:
                    System.out.println("Transitions (S): " + S);
                    break;
                case 4:
                    System.out.println("Initial state (q0): " + q0);
                    break;
                case 5:
                    System.out.println("Set of final states (F): " + F);
                    break;
                case 6:
                    if (isDfa()) {
                        System.out.print("Enter the sequence to verify: ");
                        String sequence = scanner.next();
                        System.out.println("Sequence accepted: " + isAccepted(sequence));
                    } else {
                        System.out.println("The automata is not a DFA. Cannot verify sequences.");
                    }
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Exiting the program.");
    }

    public static void main(String[] args) {
        try {
            FiniteAutomata automata = readFromFile("lab2/FA.in");
            automata.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
