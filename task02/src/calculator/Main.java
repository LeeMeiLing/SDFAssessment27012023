package calculator;

import java.io.Console;

public class Main{

    public static void main(String[] args) {
        
        boolean exit = false;
        Console cons = System.console();
        String userInput;
        String[] input;
        Calculator cal;
        Double $last = 0d;

        System.out.println("Welcome.");

        while(!exit){

            userInput = cons.readLine(">");

            if(userInput.equalsIgnoreCase("exit")){
                exit= true;
                System.out.println("Bye bye");
                continue;
            }

            input = userInput.trim().split(" ");

            if(input[0].equals("$last")){
                input[0] = String.valueOf($last);
            }else if(input[2].equals("$last")){
                input[2] = String.valueOf($last);
            }
            
            switch(input[1]){

                case "+" :{

                    System.out.println("performing addition");
                    cal = new Calculator(input[0], input[2]);
                    $last = cal.add();
                    break;
                }
                case "-" :{

                    System.out.println("performing subtraction");
                    cal = new Calculator(input[0], input[2]);
                    $last = cal.subtract();
                    break;
                }
                case "*" :{

                    System.out.println("performing multiplication");
                    cal = new Calculator(input[0], input[2]);
                    $last = cal.multiply();
                    break;
                }
                case "/" :{

                    System.out.println("performing division");
                    cal = new Calculator(input[0], input[2]);
                    $last = cal.divide();
                    break;
                }
            }

            System.out.println($last);

        }
    }
}