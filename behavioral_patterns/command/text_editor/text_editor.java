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
import java.util.Stack ;

interface Command {
    public void execute() ;
    public void undo();
}

class TypeText implements Command {
    private String text ;
    int position ;
    private Editor editor;

    TypeText(Editor editor, int position, String text){
        this.editor = editor ;
        this.position = position ;
        this.text = text ;
    }

    @Override
    public void undo() {
        editor.delete(position, text.length());
    }

    @Override
    public void execute() {
        editor.insert(text, position) ;
    }
}


class DeleteCommand implements Command{
    int position ;
    int length ;
    private Editor editor ;
    String deletedText ; // we need this deleted text for undo operation

    DeleteCommand(Editor editor, int position, int length){
        this.editor = editor;
        this.position = position ;
        this.length = length ;
    }

    @Override
    public void undo() {
        editor.insert(deletedText, position) ;
    }

    @Override
    public void execute() {
        deletedText = this.editor.getContent().substring(position, position + length) ;
        editor.delete(position, length);
    }
}

class Editor {
    private StringBuilder text ;

    public Editor(){
        this.text =  new StringBuilder() ;
    }

    public void insert(String newText, int position){
        text.insert(position, newText) ;
    }

    public void delete(int position, int length){
        text.delete(position, position + length) ;
    }

    public String getContent(){
        return text.toString() ;
    }

    public void showContent(){
        System.out.println("Editor contents : " + text.toString()) ;
    }
}

class Invoker {
    private Stack<Command> undoStack ;
    private Stack<Command> redoStack ;

    Invoker(){
        undoStack = new Stack<>() ;
        redoStack = new Stack<>() ;
    }

    public void execute(Command cmd) {
        cmd.execute();
        undoStack.push(cmd) ;
        redoStack.clear() ;
    }

    public void undo() {
        if(undoStack.empty()){
            System.out.println("Nothing to undo") ;
            return ;
        }

        // Add to redo stack
        // And perform undo operation
        Command cmd = undoStack.peek() ;
        undoStack.pop() ;
        redoStack.push(cmd) ;
        cmd.undo() ;
    }

    public void redo() {
        if(redoStack.empty()){
            System.out.print("Nothing to redo. You are already at the oldest change") ;
            return ;
        }

        // Add back to undo stack
        // And perform undo operation
        Command cmd = redoStack.peek() ;
        redoStack.pop();
        cmd.execute();
        undoStack.push(cmd) ;
    }
}

class text_editor {
    public static void main(String[] args){
        Editor editor = new Editor() ;
        Invoker invoker = new Invoker() ;

        System.out.println("Added hello \n") ;
        invoker.execute(new TypeText(editor, 0 , "Hello"));
        editor.showContent();


        System.out.println("Added world\n") ;
        invoker.execute(new TypeText(editor, 5, " World"));
        editor.showContent();

        System.out.println("Undo last operation\n") ;
        invoker.undo();
        editor.showContent();


        System.out.println("Redo last operation\n") ;
        invoker.redo();
        editor.showContent();

        System.out.println("Delete text\n") ;
        invoker.execute(new DeleteCommand(editor, 0, 3) );
        editor.showContent();


        System.out.println("Undo last operation\n") ;
        invoker.undo();
        editor.showContent();
    }
}