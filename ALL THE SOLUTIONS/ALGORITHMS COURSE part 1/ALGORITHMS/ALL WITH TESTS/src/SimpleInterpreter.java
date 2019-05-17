import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInterpreter {
  public Double input(String input) {
    Deque<String> tokens = tokenize(input);
    
    // functions
    // add x y
    // 
    
    while (tokens.isEmpty()) {
        String token = tokens.pop();
        if (token.matches("")) {
            
        }
    }
    
    return null;
  }

  private static Deque<String> tokenize(String input) {
    Deque<String> tokens = new LinkedList<>();
    Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
    Matcher m = pattern.matcher(input);
    while (m.find()) {
      tokens.add(m.group());
    }
    return tokens;
  }
  
  public static void main(String args[] ) throws Exception {
      
  }
  
}