import java.util.*;
import java.util.regex.*;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scientific Calculator (type 'exit' to quit):");

        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().replaceAll("\\s+", "");

            if (input.equalsIgnoreCase("exit")) break;

            try {
                double result = evaluateExpression(input);
                System.out.println("Result = " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Basic function support
    private static double applyFunction(String func, double value) {
        switch (func) {
            case "sin": return Math.sin(Math.toRadians(value));
            case "cos": return Math.cos(Math.toRadians(value));
            case "tan": return Math.tan(Math.toRadians(value));
            case "asin": return Math.toDegrees(Math.asin(value));
            case "acos": return Math.toDegrees(Math.acos(value));
            case "atan": return Math.toDegrees(Math.atan(value));
            case "log": return Math.log10(value);
            case "ln": return Math.log(value);
            case "sqrt": return Math.sqrt(value);
            case "cbrt": return Math.cbrt(value);
            case "exp": return Math.exp(value);
            case "fact": return factorial((int) value);
            default: throw new RuntimeException("Unknown function: " + func);
        }
    }

    private static int factorial(int n) {
        if (n < 0) throw new RuntimeException("Factorial of negative not defined.");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    private static double evaluateExpression(String expr) {
        List<String> tokens = toPostfix(expr);
        return evaluatePostfix(tokens);
    }

    // Tokenize and convert to postfix using Shunting Yard Algorithm
    private static List<String> toPostfix(String expr) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        Pattern pattern = Pattern.compile(
            "(\\d+(\\.\\d+)?)|([a-zA-Z]+)|([()+\\-*/^])");
        Matcher matcher = pattern.matcher(expr);

        while (matcher.find()) {
            String token = matcher.group();

            if (token.matches("\\d+(\\.\\d+)?")) {
                output.add(token);
            } else if (token.matches("[a-zA-Z]+")) {
                stack.push(token); // function
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("("))
                    output.add(stack.pop());
                stack.pop(); // pop '('
                if (!stack.isEmpty() && stack.peek().matches("[a-zA-Z]+"))
                    output.add(stack.pop()); // function
            } else if (token.matches("[+\\-*/^]")) {
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek()))
                    output.add(stack.pop());
                stack.push(token);
            }
        }

        while (!stack.isEmpty())
            output.add(stack.pop());

        return output;
    }

    private static double evaluatePostfix(List<String> tokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else if (token.matches("[+\\-*/^]")) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    case "^": stack.push(Math.pow(a, b)); break;
                }
            } else if (token.matches("[a-zA-Z]+")) {
                double val = stack.pop();
                stack.push(applyFunction(token, val));
            }
        }

        return stack.pop();
    }

    private static int precedence(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return 0;
        }
    }
}
