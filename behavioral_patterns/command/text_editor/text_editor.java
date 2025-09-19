/*
Problem : Design a text editor which supports undo and redo operation. Ensure that the text editor can perform operations
like TypeText, Delete, Undo, Redo. How would your code look like ?

Followup : Let's say you want to persiste the undo and redo operations even after the text editor is closed, how would
you implement that

Solution : We will be using Command Design pattern, which will have a command interface. The concrete operations like
Typing text and Deleting Text will be inherit from this interface. The interface will have an execute and undo functions
Also we will have an invoker class which will act as middleware which will actually set the command which needs to be
executed. This invoker will have setCommand function which will refer to the object of the Command for which execute
needs to be called. The invoker will actually have the undo and redo operations and corresponding stack as well.
Each of the commands will be tied to the text editor and invoker will be independently calling their respective funtions
*/

import java.lang.Override ;

interface Command {
    public void execute() ;
    public void undo();
}

class TypeText implements Command {
    @Override
    public void undo() {
        // Add functionality here
    }

    @Override
    public void execute() {
        // Add functionality here
    }
}


class DeleteCommand implements Command{
    @Override
    public void undo() {
        // Add functionality here
    }

    @Override
    public void execute() {
        // Add functionality here
    }
}

class TextEditor {
    String text ;
    int currPtr ;
}

class Invoker {
    private Command command ;

    public void setCommand(Command){
        this.command = command ;
    }

    public void execute() {
        // TODO : Clear redo stack
        //TODO :  Add to undo stack
        this.command.execute();
    }

    public void undo() {
        // Add to redo stack
        // And perform undo operation
    }

    publi void redo() {
        // Add back to undo stack
        // And perform undo operation
    }
}