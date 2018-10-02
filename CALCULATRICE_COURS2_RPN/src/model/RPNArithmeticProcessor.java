package model;

public class RPNArithmeticProcessor {

    private HypeStack stack;
    public boolean hasResult;

    public RPNArithmeticProcessor(HypeStack stack) {
        this.stack = stack;
        this.hasResult = false;
    }

    public RPNArithmeticProcessor() {
        this(new HypeStack());
    }

    public HypeStack getStack() {
        return this.stack;
    }

    public void reset(){
            this.getStack().clear();
            this.hasResult = false;
    }

    public int compute() {
        ArithmeticOperator operator = ArithmeticOperator.fromString(this.stack.pop());
        int rhs = this.stack.popInt();
        int lhs = this.stack.popInt();
        int result = 0;
        switch (operator) {
            case PLUS:
                result = lhs + rhs;
                break;
            case MINUS:
                result = lhs - rhs;
                break;
            case MULTIPLY:
                result = lhs * rhs;
                break;
            case DIVIDE:
                result = lhs / rhs;
                break;
            case MODULO:
                result = lhs % rhs;
                break;
        }
        return result;
    }

    public void processNextToken(String tk) {
        if (ArithmeticExpressionHandler.isOperand(tk)){
            this.stack.push(tk);
            this.hasResult = false;

        }
        else if (ArithmeticExpressionHandler.isOperator(tk)){
            ArithmeticOperator op = ArithmeticOperator.fromString(tk);
            if(this.stack.size() < 2 ){
                throw new IllegalStateException("Error" );

            }
            this.stack.push(tk);
            processNextToken(Integer.toString(this.compute()));
            if(this.stack.size() == 1){
                this.hasResult = true;
            }
        }
        else{
            throw new IllegalArgumentException("Invalid token");
            }
        }
    }
