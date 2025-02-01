package it.unicam.cs.FilieraAgricola.Command;

public class CommandInvoker {

    private Command command;

    public void setCommand(Command command) {
        if(command == null) throw new NullPointerException("Command cannot be null");

        this.command = command;
    }


    public void invoke(){
        command.execute();
    }
}
