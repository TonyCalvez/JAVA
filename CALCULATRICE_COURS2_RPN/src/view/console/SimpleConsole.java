package view.console;


import control.RPNExpressionProcessor;

import java.util.Scanner;

public class SimpleConsole {

    private Scanner scanner;
    private String lastInput;
    private RPNExpressionProcessor processor;

    public SimpleConsole() {
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
        this.lastInput = "";
        this.processor = new RPNExpressionProcessor();
    }

    public void start(){
        while(this.getInput()){
            System.out.println(this.lastInput);
            if (this.lastInput.equals("quit")) break;
            if (this.lastInput.equals("reset")) this.processor.reset();
            this.processor.processExpression(this.lastInput);
            if(this.processor.hasResult()){
                System.out.println(this.processor.getResult());

            }
        }
    }

    public boolean getInput(){
        System.out.print("\n$");
        if(this.scanner.hasNext()){
            this.lastInput = this.scanner.next();
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String args[]){
        SimpleConsole console = new SimpleConsole();
        console.start();

    }


}


