package chris.myapplication;


/**
 * InfixConversion class, contains methods to validate the input string and
 * the method to complete the conversion from infix to postfix notation
 * @author Thomas Christopher Daniels
 *
 */
public class InfixConversion {
    protected ArrayStack operatorStack;

    public InfixConversion() {
        //ArrayStack is used in two methods, instantiated locally each time to save having two stacks in memory.

    }

    /**
     * Uses the stack to check the expression has no parentheses missing.
     * Loops through each character of the input string and adds each '('
     * to stack and pops from every time a ')' is encountered. If the stack is full at the end or
     * empty when a ')' is encountered the parentheses are mismatched
     *
     * @return true if all parentheses are matched, false if any are missing
     */
    public boolean checkParentheses(String input)
    {
        operatorStack = new ArrayStack();
        //Loops through each character of the input String
        for (int i=0; i<input.length(); i++)
        {
            char item = input.charAt(i);	//retrieves character at loop position
            if (item == '(') {
                operatorStack.push(item);
            }
            else if (item == ')')
            {
                if(operatorStack.isEmpty()) // no matching '(' so false
                {
                    return false;
                }
                else
                {
                    operatorStack.pop();
                }
            }
        }
        if (operatorStack.isEmpty())
        {return true;}
        else
        {return false;} 	//If stack isn't empty there is a ')' parenthesis missing from expression

    }

    /**
     * Checks that the input expression is no more than 20 characters
     *
     * @return true if 20 characters or below, otherwise return false
     */
    public boolean containsNumbers(String input)
    {
        boolean isNumber = false;
        for (int i=0; i<input.length(); i++)  //loops through each string character to check it
        {
            //local variable item given the value of input.charAt(i) to save space
            char item = input.charAt(i);
            if ((item >= '0' && item <= '9'))
            {
                isNumber = true;	 //found a character with a numeric value
            }
        }
        return isNumber;
    }

    /**
     *
     * @param input infix expression entered by user
     * @return the postfix expression string
     */
    public String convert(String input)
    {

        StringBuilder postfixExp = new StringBuilder(); //String builder to create output String with characters
        operatorStack = new ArrayStack();

        for (int i=0; i<input.length(); i++)
        {
            //local variable item given the value of input.charAt(i) to save space
            char item = input.charAt(i);
            //confirms item is letter
            if ((item >= 'A' && item <= 'Z') || (item >= 'a' && item <= 'z')) {
                postfixExp.append(item); //append item onto end of output String
            }
            else if (item == '(') {
                operatorStack.push(item);
            }
            else if (item == ')') {
                //appends item on top of stack until '(' is encountered
                while (!operatorStack.peek().toString().equals("(")) {

                    postfixExp.append(operatorStack.pop());
                }
                operatorStack.pop();  //pops ( symbol to discard it
            }
            else if (item == '+' || item == '-' || item == '*' || item == '/') {
                if (operatorStack.isEmpty() || operatorStack.peek().toString().equals("(")) {
                    operatorStack.push(item);	//add operator to stack if top of stack is empty or a '('
                } else {
                    //checks operator precedence, pops stack (and appends to output string) until stack is empty or operator on top of stack has lower precedence than comparison operator
                    while (!operatorStack.isEmpty() && opPrecedence(item) <= opPrecedence(operatorStack.peek().toString().charAt(0))) {
                        postfixExp.append(operatorStack.pop());
                    }
                    operatorStack.push(item); // pushes operator from input string onto stack
                }
            }
        }
        while (!operatorStack.isEmpty())
        {
            //if there are any operators left in the stack when expression is evaluated, pop and append them to output string.
            postfixExp.append(operatorStack.pop());
        }


        return postfixExp.toString(); //returns output String to be used in main activity


    }

    /**
     * Operator precedence, assigns a numeric value to an operator to enable
     * comparison of operators in the convert method
     * @param input character being evaluated from input string
     * @return numeric value to indicate operators precedence.
     */
    public int opPrecedence(char input)
    {
        switch (input) {
            case '-':
            case '+':
                return 1; //assigns a value of 1 to - and +
            case '/':
            case '*':
                return 2; // assigns a value of 2 to / and *
            default:
                return 0; //default case set to 0, should not occur in program
        }
    }


}
