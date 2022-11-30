package Models;

import java.io.Serializable;

interface SendObjectWithCommand <C, O>{
    public C getCommand();
    public O getObject();
}

public class SendOrReceiveData<C, O> implements SendObjectWithCommand, Serializable {
    private C command;
    private O object;

    public SendOrReceiveData(C command, O object){
        this.command = command;
        this. object = object;
    }

    @Override
    public C getCommand() {
        return command;
    }

    @Override
    public O getObject() {
        return object;
    }
}
