package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

public class AddIfMin extends Command {
    public AddIfMin() {
        super("add_if_min", "добавить новый элемент если его значение меньше значения наименьшего элемента этой коллекции", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        LabWork newLabWork = inputData.getLabWork();
        float min = Float.MAX_VALUE;
        for (LabWork labWork : collection.getCollection()) {
            min = Math.min(labWork.getMinimalPoint(), min);
        }
        if (newLabWork.getMinimalPoint() < min) {
            collection.add(newLabWork);
            return "Новый объект добавлен";
        }
        return "Объект не добавлен";
    }
}