import java.util.ArrayList;

public class ProgramInternalForm {
   private ArrayList<PIFpair> pif;
   public ProgramInternalForm() {
      this.pif = new ArrayList<PIFpair>();
   }
   public void add(String token, Pair position) {
       PIFpair pair = new PIFpair(token,position);
      this.pif.add(pair);
   }
    public ArrayList<PIFpair> getPIF() {
        return this.pif;
    }
    @Override
    public String toString() {
        String result = "";
        for(PIFpair pair : this.pif) {
            result += pair.toString() + "\n";
        }
        return result;
    }
}
