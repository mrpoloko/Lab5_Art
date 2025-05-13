package App.Commands;

import App.Collection.CollectionLabWork;
import App.InputData;

public class Info extends Command {
    public Info() {
        super("info", "вывести информацию о коллекции", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        return collection.toString();
    }
}