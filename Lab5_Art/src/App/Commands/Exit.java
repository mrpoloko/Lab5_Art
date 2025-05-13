package App.Commands;

import App.Collection.CollectionLabWork;
import App.InputData;

public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения)", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        inputData.setTrueFlag();
        return "Выход из программы";
    }
}