package App.Commands;

import App.Collection.CollectionLabWork;
import App.InputData;

import static App.IdGenerate.resetId;

public class Clear extends Command {
    public Clear() {
        super("clear", "очистить коллекцию", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        collection.clear();
        resetId();
        return "Коллекция очищена";
    }
}