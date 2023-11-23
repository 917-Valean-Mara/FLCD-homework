 public class FApair {
        private final String state;
        private final String symbol;

        public FApair(String state, String symbol) {
            this.state = state;
            this.symbol = symbol;
        }

        public String getState() {
            return state;
        }

        public String getSymbol() {
            return symbol;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            FApair pair = (FApair) obj;
            return state.equals(pair.state) && symbol.equals(pair.symbol);
        }

        @Override
        public int hashCode() {
            return state.hashCode() + symbol.hashCode();
        }

        @Override
        public String toString() {
            return "(" + state + ", " + symbol + ")";
        }
    }
