package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

public class AddIfMax extends Command {
    public AddIfMax() {
        super("add_if_max", "добавить новый элемент если его значение превышает значение наибольшего элемента этой коллекции", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        LabWork newLabWork = inputData.getLabWork();
        float max = Float.MIN_VALUE;
        for (LabWork labWork : collection.getCollection()) {
            max = Math.max(labWork.getMinimalPoint(), max);
        }
        if (newLabWork.getMinimalPoint() > max) {
            collection.add(newLabWork);
            collection.sortByMinimalPoint();
            return "Новый объект добавлен";
        }
        return "Объект не добавлен";
    }
}