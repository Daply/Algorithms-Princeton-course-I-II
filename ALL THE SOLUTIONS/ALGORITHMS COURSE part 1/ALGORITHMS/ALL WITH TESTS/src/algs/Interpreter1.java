package algs;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Interpreter1 {
    
//    Task
//    You will create an interpreter which takes inputs 
//    described below and produces outputs, 
//    storing state in between each input.
//
//    If you're not sure where to start with this kata, 
//    check out my Simpler Interactive Interpreter kata, 
//    which greatly simplifies the interpreter by removing functions.
//
//    Note that the eval command has been disabled.
//
//    Concepts
//    The interpreter will take inputs in the language 
//    described under the language header below. 
//    This section will give an overview of the language constructs.
//
//    Variables
//    Any identifier which is not a keyword or a function name 
//    will be treated as a variable. 
//    If the identifier is on the left hand side of an assignment operator, 
//    the result of the right hand side will be stored in the variable. 
//    If a variable occurs as part of an expression, 
//    the value held in the variable will be substituted when the 
//    expression is evaluated.
//
//    Variables are implicitly declared the first time they are assigned to.
//
//    Example: Initializing a variable to a constant value and using 
//    the variable in another expression 
//    (Each line starting with a '>' indicates a separate call 
//    to the input method of the interpreter, other lines represent output)
//
//    >x = 7
//        7
//    >x + 6
//        13
//    Referencing a non-existent variable will cause the interpreter 
//    to throw an error. 
//    The interpreter should be able to continue accepting input even after throwing.
//
//    Example: Referencing a non-existent variable
//
//    >y + 7
//        ERROR: Invalid identifier. No variable with name 'y' was found."
//    Assignments
//    An assignment is an expression that has an identifier on left 
//    side of an = operator, and any expression on the right. 
//    Such expressions should store the value of the right hand side 
//    in the specified variable and return the result.
//
//    Example: Assigning a constant to a variable
//
//    x = 7
//        7
//    You should also be able to chain and nest assignments. 
//    Note that the assignment operator is one of the few that is right associative.
//
//    Example: Chained assignments. The statement below should set both x and y to 7.
//
//    x = y = 7
//        7
//    Example: Nested assignments. The statement below should set y to 3, 
//    but it only outputs the final result.
//
//    x = 13 + (y = 3)
//        16
//    Operator Precedence
//    Operator precedence will follow the common order. 
//    There is a table in the Language section below that 
//    explicitly states the operators and their relative precedence.
//
//    Functions
//    Functions are declared by the fn keyword followed by a name, 
//    an optional arguments list, the => operator, and finally an expression. 
//    All function variables are local to the function. That is, the only 
//    variable names allowed in the function body are those declared by the 
//    arguments list. If a function has an argument called 'x', and there is also 
//    a global variable called 'x', the function should use the value of the supplied 
//    argument, not the value of the global variable, when evaluating the expression. 
//    References to variables not found in the argument list should result in an error 
//    when the function is defined.
//
//    Example: declare a function to calculate the average of two variables and call it. 
//    (Each line starting with a '>' indicates a separate call to the input method of 
//    the interpreter, other lines represent output)
//
//    >fn avg => (x + y) / 2
//        ERROR: Unknown identifier 'x'
//    >fn avg x y => (x + y) / 2
//    >a = 2
//        2
//    >b = 4
//        4
//    >avg a b
//        3
//    Example: declare a function with an invalid variable name in the function body
//
//    >fn add x y => x + z
//        ERROR: Invalid identifier 'z' in function body.
//    Example: chain method calls (hint: function calls are right associative!)
//
//    >fn echo x => x
//    >fn add x y => x + y
//    >add echo 4 echo 3
//        7
//    Name conflicts
//    Because variable and function names share the same grammar, 
//    conflicts are possible. Precedence will be given to the first object declared. 
//    That is, if a variable is declared, then subsequent declaration of a function 
//    with the same name should result in an error. Likewise, declaration of a 
//    function followed by the initialization of a variable with the same name 
//    should result in an error.
//
//    Declaration of function with the same name as an existing function should 
//    overwrite the old function with the new one.
//
//    Example: Overwriting a function
//
//    >fn inc x => x + 1
//    >a = 0
//        0
//    >a = inc a
//        1
//    >fn inc x => x + 2
//    >a = inc a
//        3
//    Input
//    Input will conform to either the function production or the expression 
//    production in the grammar below.
//
//    Output
//    Output for a valid function declaration will be an empty string (null in Java).
//    Output for a valid expression will be the result of the expression.
//    Output for input consisting entirely of whitespace will be an empty string (null in Java).
//    All other cases will throw an error.
//    -- In Haskell that is:
//    Right (Nothing, Interpreter)
//    Right (Just Double, Interpreter) 
//    Right (Nothing, Interpreter)
//    Left String
//    Language
//    Grammar
//    This section specifies the grammar for the interpreter language in EBNF syntax
//
//    function        ::= fn-keyword fn-name { identifier } fn-operator expression
//    fn-name         ::= identifier
//    fn-operator     ::= '=>'
//    fn-keyword      ::= 'fn'
//
//    expression      ::= factor | expression operator expression
//    factor          ::= number | identifier | assignment | '(' expression ')' | function-call
//    assignment      ::= identifier '=' expression
//    function-call   ::= fn-name { expression }
//
//    operator        ::= '+' | '-' | '*' | '/' | '%'
//
//    identifier      ::= letter | '_' { identifier-char }
//    identifier-char ::= '_' | letter | digit
//
//    number          ::= { digit } [ '.' digit { digit } ]
//
//    letter          ::= 'a' | 'b' | ... | 'y' | 'z' | 'A' | 'B' | ... | 'Y' | 'Z'
//    digit           ::= '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
//    Operator Precedence
//    The following table lists the language's operators grouped in order of precedence. 
//    Operators within each group have equal precedence.
//
//    Category    Operators
//    Multiplicative  *, /, %
//    Additive    +, -
//    Assignment  =
//    Function    =>
    
    Set<String> functionNames = null; 
    Set<String> varitiesNames = null; 
    Set<Variety> varities = null;
    Set<Function> functions = null;
    
    Interpreter1() {
        this.functionNames = new HashSet<String>(); 
        this.varitiesNames = new HashSet<String>(); 
        this.varities = new HashSet<Variety>(); 
        this.functions = new HashSet<Function>(); 
    }
    
    /**
     *  Whole the algorithm:
     *  
     *  1) if function command was involved (fn)
     *     -> creating function and adding it to 
     *        list of existing functions
     *  2) if expression
     *     -> firstly wrap all the operations
     *        with ( ) scopes for better counting
     *     -> wrap all the functions with [ ] scopes
     *        for further reworking of functions
     *     -> reworking functions: exchanging
     *        function name in calling on its 
     *        expression with appropriate 
     *        varieties
     *     -> solving whole the expression 
     * 
     * ERROR situations:
     * >fn avg => (x + y) / 2
     *     ERROR: Unknown identifier 'x'
     * >fn add x y => x + z
     *     ERROR: Invalid identifier 'z' in function body.
     * 
     * 
     *  */  
  public Double input(String input) {
    Deque<String> tokens = tokenize(input);
    String firstToken = tokens.getFirst();
    String expression = "";
    if (firstToken.equals("fn")) {
        functionCreate(tokens);
    }
    else {
        expression = expressionCreate(tokens);
        expression = expressionResolve(expression);
    }  
    
    if (!expression.isEmpty()) {
        return Double.parseDouble(expression);
    }
    else
        return Double.NaN;
  }
  
  /**
   * Function creation
   * 
   * ERROR situations:
   * >fn avg => (x + y) / 2
   *     ERROR: Unknown identifier 'x'
   * >fn add x y => x + z
   *     ERROR: Invalid identifier 'z' in function body.
   * 
   * 
   *  */
  private void functionCreate(Deque<String> tokens) {
      boolean functionName = false;
      String fnName = new String();
      boolean functionExpression = false;
      StringBuilder fnExpression = new StringBuilder();
      boolean functionVarities = false;
      StringBuilder fnVarities = new StringBuilder();
      Set<String> varities = new HashSet<String>();
      String errorText = new String();
      while (!tokens.isEmpty()) {
          String token = tokens.poll();
          
          // function definition
          if (token.equals("fn")) {
              functionName = true;
              functionExpression = true;
              functionVarities = true;
          }
          else {
              if (!functionVarities && functionExpression) {
                  
                  if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                      // variable check
                      if (!varities.contains(token) &&
                              !varities.isEmpty()) {
                          // ERROR
                          errorText += "ERROR: Invalid identifier '" + token 
                                  + "' in function body.";
                          break;
                      }
                      if (!varities.contains(token) &&
                              varities.isEmpty()) {
                          // ERROR
                          errorText += "ERROR: Unknown identifier '" + token + "'";
                          break;
                      }
                  }
                  
                  fnExpression.append(token);
                  fnExpression.append(" ");
              }
              if (!functionName && functionVarities) {
                  if (token.equals("=>"))
                      functionVarities = false;
                  else {
                      if (varities.contains(token)) {
                          // ERROR
                          errorText += "ERROR: Varity '" + token 
                                  + "' is already defined in function.";
                          break;
                      }
                      else {
                          varities.add(token);
                      }
                      fnVarities.append(token);
                      fnVarities.append(" ");
                  }
              }
              if (functionName) {
                  fnName = token;
                  functionName = false;
              }
          }
      }
      if (!errorText.equals("")) {
          throw new IllegalArgumentException(errorText);
      }
      else {
          Function function = new Function(fnName, fnVarities.toString(), fnExpression.toString());
          // overwriting a function
          if (this.functionNames.contains(fnName)) {
              for (Function func: this.functions) {
                  if (func.functionName.equals(fnName)) {
                      this.functions.remove(func);
                      break;
                  }
              }
          }
          this.functionNames.add(fnName);
          this.functions.add(function);
      }
  }
  
  /**
   * Expression creation and varieties addition
   *  */
  private String expressionCreate(Deque<String> tokens) {
      Stack<String> names = new Stack<String>();
      Stack<String> operators = new Stack<String>();
      
      // gather whole expression for further reworking
      StringBuilder expression = new StringBuilder();
      Deque <Integer> functionsVars = new LinkedList<Integer>();
      expression.append("( ");
      int curFuncVarities = -1;
      boolean operatorFound = false;
      boolean extraScope = false;
      while (!tokens.isEmpty()) {
          String token = tokens.poll();
          
          if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) {      
              names.push(token);
              if (this.functionNames.contains(token)) {                
                  for (Function func: this.functions) {
                      if (func.functionName.equals(token)) {
                          functionsVars.addLast(func.varities.size());
                          break;
                      }
                  }
                  expression.append("[ ");
                  expression.append(token);
                  expression.append(" ");
              }
              else {
                  // check if function variables
                  if (!functionsVars.isEmpty()) {
                      
                      // reduce all arguments
                      // numbers of previous functions
                      List <Integer> list = functionsVars.stream().map(s -> s - 1)
                                            .collect(Collectors.toList());
                      functionsVars.clear();
                      functionsVars.addAll(list);
                      
                      curFuncVarities = functionsVars.peekLast();
                  }
                  else {
                      curFuncVarities = -1;
                  }
                  
                    if (curFuncVarities == -1 && !operatorFound && !extraScope)
                        expression.append("( ");
                    expression.append(token);
                    if (curFuncVarities == -1 && operatorFound && !extraScope) {
                        expression.append(" ) ");
                        operatorFound = false;
                    }
                    else
                        expression.append(" ");

                    
                    if (curFuncVarities == 0) {
                        expression.append("] ");
                        functionsVars.pop();
                    }
              }      
          }
          else if (token.matches("[-+*/%=\\(\\)]")) {
              operators.push(token);
              expression.append(token);
              expression.append(" ");
              if (token.matches("[\\(\\)]") && !extraScope) {
                  extraScope = true;
              }
              else if (token.matches("[\\(\\)]") && extraScope) {
                  extraScope = false;
              }
              operatorFound = true;
          }
          else if (token.matches("[0-9]*(\\.?[0-9]+)")) {
              if (!operators.isEmpty()) {
                  if (operators.lastElement().equals("=")) {
                      String varName = names.pop();
                      operators.pop();
                      Variety var = new Variety(varName, token);
                      this.varities.add(var);
                      this.varitiesNames.add(varName);
                  }
              }
              // check if function variables
              if (!functionsVars.isEmpty()) {
                  
                  // reduce all arguments
                  // numbers of previous functions
                  List <Integer> list = functionsVars.stream().map(s -> s - 1)
                                        .collect(Collectors.toList());
                  functionsVars.clear();
                  functionsVars.addAll(list);
                  
                  curFuncVarities = functionsVars.peekLast();
              }
              
                if (curFuncVarities == -1 && !operatorFound && !extraScope)
                    expression.append("( ");
                expression.append(token);
                if (curFuncVarities == -1 && operatorFound && !extraScope) {
                    expression.append(" ) ");
                    operatorFound = false;
                }
                else
                    expression.append(" ");

                
                if (curFuncVarities == 0) {
                    expression.append("] ");
                    functionsVars.pop();
                }
          }
      }
      // check if function variables
      if (!functionsVars.isEmpty()) {
          if (functionsVars.peekLast() == 0) {
              expression.append("] ");
              functionsVars.pop();
          }
      }
      expression.append(")");
      // resolve functions
      expression = new StringBuilder(functionExpressionResolve(expression.toString()));
      
      return expression.toString();
  }
  
  
  /**
   * Function expression resolving
   * (on the previous step all the function were added to [ ] scopes)
   *  */
  private String functionExpressionResolve(String expression) {
      String[] tokens = expression.split(" ");
      
      Stack<String> functions = new Stack<String>();
      Stack<String> values = new Stack<String>();
      StringBuilder funcInExpression = new StringBuilder();
      int countScopes = 0;
      for (String token: tokens) {
           if (token.matches("[\\[\\]]")) {
               if (token.matches("\\[")) {
                   if (countScopes == 0) {
                       funcInExpression = new StringBuilder();
                   }
                   countScopes++;
               }
               if (token.matches("\\]")) {
                   if (!functions.isEmpty() &&
                           !values.isEmpty()) {
                       
                       String fn = functions.pop();
                       Function curFunc = null;
                       for (Function func: this.functions) {
                           if (func.functionName.equals(fn)) {
                               curFunc = func;
                               break;
                           }
                       }
                       String functionModifiedExpression = curFunc.expression;
                       for (int i = curFunc.varities.size() - 1; i >= 0; i--) {
                           String val1 = values.pop();
                           functionModifiedExpression = functionModifiedExpression
                                                        .replaceAll(curFunc.varities.get(i), val1);
                       }
                       values.push(functionModifiedExpression.trim());
                   }
                   countScopes--;
                   if (countScopes == 0) {
                       // replace result
                       funcInExpression.append("\\]");
                       String result = "( " + values.pop() + " )";
                       expression = expression.replaceAll(funcInExpression.toString().trim(), result);
                       funcInExpression = new StringBuilder();
                   }
               }
           }
           else if (token.matches("[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)")) {
               if (this.functionNames.contains(token)) {
                   functions.push(token);
               }
               else {
                   values.push(token);
               }
           }
           if (countScopes > 0) {
               if (token.matches("\\[")) {
                   funcInExpression.append("\\[");
               }
               else if (token.matches("\\]")) {
                   funcInExpression.append("\\]");
               }
               else funcInExpression.append(token);
               funcInExpression.append(" ");
           }
      }
      return expression;
  }
  
  /**
   * Expression resolving
   *  */
  private String expressionResolve(String expression) {
      String[] tokens = expression.split(" ");
      
      Stack<String> operators = new Stack<String>();
      Stack<String> values = new Stack<String>();
      
      for (String token: tokens) {
           if (token.matches("[-+=*/%\\(\\)]")) {
               if (token.matches("[-+=*/%]")) {
                   operators.push(token);
               }
               if (token.matches("[\\)]")) {
                   if (!operators.isEmpty() &&
                           !values.isEmpty()) {
                       String op = operators.pop();
                       String val2 = values.pop();
                       String val1 = values.pop();
                       String result = evaluate(op, val1, val2);
                       values.push(result);
                   }
               }
           }
           else if (token.matches("[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)")) {
               values.push(token);
           }
      }
      return values.pop();
  }

  private String evaluate(String operator, String val1, String val2) {
      String result = "";
      
      if (val1.matches("[A-Za-z_][A-Za-z0-9_]*") &&
              !operator.equals("=")) {
          // replacing variable on its value
          if (this.varitiesNames.contains(val1)){
              for (Variety var: this.varities) {
                  if (var.varietyName.equals(val1)) {
                      val1 = var.varietyValue;
                      break;
                  }
              }
          }
          else {
              // ERROR
          }
      }
      if (val2.matches("[A-Za-z_][A-Za-z0-9_]*") &&
              !operator.equals("=")) {
          // replacing variable on its value
          if (this.varitiesNames.contains(val2)){
              for (Variety var: this.varities) {
                  if (var.varietyName.equals(val2)) {
                      val2 = var.varietyValue;
                      break;
                  }
              }
          }
          else {
              // ERROR
          }
      }
      
      if (operator.equals("+")) {
          double value = Double.parseDouble(val1) +
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("-")) {
          double value = Double.parseDouble(val1) -
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("*")) {
          double value = Double.parseDouble(val1) *
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("/")) {
          double value = Double.parseDouble(val1) /
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("%")) {
          double value = Double.parseDouble(val1) %
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("=")) {
          for (Variety var: this.varities) {
              if (var.varietyName.equals(val1)) {
                  var.varietyValue = String.valueOf(val2);
                  result = String.valueOf(val2);
                  break;
              }
          }
      }
      return result;
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
  
  class Function {
      public String functionName = "";
      public List<String> varities = null;
      public String expression = "";
      
      Function (String functionName, String varities, String expression) {
          this.functionName = functionName;
          this.varities = new ArrayList<String>();
          String[] vars = varities.split(" ");
          for (String var: vars) {
              this.varities.add(var);
          }
          this.expression = expression;
      }
  }
  class Variety {
      public String varietyName = "";
      public String varietyValue = "";
      Variety (String varietyName, String varietyValue) {
          this.varietyName = varietyName;
          this.varietyValue = varietyValue;
      }
  }
  
  public static void main(String args[]) throws Exception {
      Interpreter1 interpreter = new Interpreter1();
      System.out.println(interpreter.input("fn avg x y => (x + y) / 2"));
      System.out.println(interpreter.input("6 + (y = 5) + 7"));
      System.out.println(interpreter.input("a = 2"));
      System.out.println(interpreter.input("b = 4"));
      System.out.println(interpreter.input("avg a b + avg 3 y")); // 7.0
      System.out.println(interpreter.input("a * b + y"));
      System.out.println(interpreter.input("( 6 + 7 ) * 2"));
      
      // test 1
      interpreter = new Interpreter1();
      interpreter.input("fn avg x y => (x + y) / 2");
      System.out.println(interpreter.input("avg 4 2")); //3
      
      // test 2
      interpreter = new Interpreter1();
      System.out.println(interpreter.input("7 % 4")); //3
      
      // test 3 !!!
//    >fn echo x => x
//    >fn add x y => x + y
//    >add echo 4 echo 3 // answer: 7
      interpreter = new Interpreter1();
      interpreter.input("fn echo x => x");
      interpreter.input("fn add x y => x + y");
      System.out.println(interpreter.input("add echo 4 echo 3"));
      
      // test 4 !!! (overwriting a function and variable)
//    >fn inc x => x + 1
//    >a = 0
//        0
//    >a = inc a
//        1
//    >fn inc x => x + 2
//    >a = inc a
//        3
      interpreter = new Interpreter1();
      interpreter.input("fn inc x => x + 1");
      interpreter.input("a = 0");
      System.out.println(interpreter.input("a = inc a"));
      interpreter.input("fn inc x => x + 2");
      System.out.println(interpreter.input("a = inc a"));
    
      // test 5 !!! (overwriting a function and variable)
      interpreter = new Interpreter1();
      interpreter.input("fn inc x => x + 1");
      interpreter.input("a = 0");
      System.out.println(interpreter.input("3 + (a = inc a)*3"));
      interpreter.input("fn inc x => x + 2");
      System.out.println(interpreter.input("((a = inc a)-12)*2"));
      
      interpreter = new Interpreter1();
      interpreter.input("fn echo x => x");
      interpreter.input("fn add x y => x + y");
      System.out.println(interpreter.input("add echo 4 echo 3"));
      interpreter.functionExpressionResolve("[ add [ echo 4 ] [ echo 3 ] ] + [ echo 5 ]");
      
      // test 6 (error occurences)
      interpreter = new Interpreter1();
      interpreter.input("fn avg => (x + y) / 2");
      interpreter.input("fn add x y => x + z");
  }
}
