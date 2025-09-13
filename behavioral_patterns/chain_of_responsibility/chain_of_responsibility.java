/*
You are required to design a ATM. The ATM can have different dispensers based on the currency notes : 2000, 500, 100 etc.
Each dispenser can only issue it's denomination an pass the reamining to the next one.

Followup : Considering that a new denomination (200) is added later on how will you modeify your code to cater to the
change in the denominations.

Solution : We will use Chain of Responsibility to solve this problem. We will have handlers which will disburse the
amount it can and pass the rest of the amount to the next handler.

For the followup on creating a dynamic handler, we are going to use a chain builder class that reads config and writes
the handlers in that order. That way handlers stay dumb - they don't care about what comes next
 */
import java.lang.Override ;
import java.util.List ;
import java.lang.IllegalArgumentException;

// This is the interface which will be used accross the concrete handlers
interface Handler {
    void handle(int amount);
    void setNext(Handler handler) ;
}

// This interface is the base handler which will be inherited by concerete handlers
// It contains only function to set next handler which is the backbone for creating chain of responsibility
abstract class BaseHandler implements Handler {
    protected Handler next ;

    @Override
    public void setNext(Handler next) {
        this.next = next ;
    }
}

// Concreate handler for 2000 notes
class TwoThousandNotesDispenser extends BaseHandler {
    public void handle(int amount) {
        int notes = amount/2000 ;
        if (notes > 0) {
            System.out.printf("Dispensing : %d notes for 2000\n", notes) ;
        }
        amount -= notes*2000 ;
        if(amount > 0 && this.next != null){
            this.next.handle(amount) ;
        }
    }
}

// Concreate handler for 500 notes
class FiveHunderNotesDispenser extends BaseHandler {
    public void handle(int amount){
        int notes = amount/500 ;
        if(notes > 0){
            System.out.printf("Dispensing : %d notes for 500\n", notes) ;
        }
        amount -= notes*500 ;
        if(amount > 0 && this.next != null){
            this.next.handle(amount) ;
        }
    }
}

// Concerete handler for 100 notes
class HunderNotesDispenser extends BaseHandler{
    public void handle(int amount){
        int notes = amount/100 ;
        if (notes > 0){
            System.out.printf("Dispensing : %d notes for 100\n", notes) ;
        }
        amount -= notes*100 ;
        if(amount > 0 && this.next != null){
            this.next.handle(amount) ;
        }
    }
}

class ChainBuilder {
    public static Handler createHandler(int denom) {
        return switch (denom){
            case 2000 -> new TwoThousandNotesDispenser() ;
            case 500 -> new FiveHunderNotesDispenser() ;
            case 100 -> new HunderNotesDispenser() ;
            default -> throw new IllegalArgumentException("Unsupported denomination : " + denom) ;
        };
    }

    public static Handler buildChain(List<Integer> denominations) {
        Handler head = null ;
        Handler curr = null ;

        for(int denom : denominations){
            Handler handler = createHandler(denom) ;

            if(head == null){
                head = handler ;
                curr = handler ;
                continue;
            }
            curr.setNext(handler);
            curr = handler ;
        }

        return head ;
    }
}

class chain_of_responsibility{
    public static void main(String[] args){
        List<Integer> denominations = List.of(2000, 500, 100) ;
        Handler handler = ChainBuilder.buildChain(denominations);

        int amount = 3700 ;

        handler.handle(amount) ;
    }
}