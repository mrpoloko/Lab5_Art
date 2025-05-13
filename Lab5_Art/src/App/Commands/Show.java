package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

public class Show extends Command {
    public Show() {
        super("show", "вывести все элементы коллекции", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        StringBuilder sb = new StringBuilder();
        for (LabWork labWork : collection.getCollection()) {
            sb.append(labWork.toString()).append("\n");
        }
        return sb.toString();
    }
}