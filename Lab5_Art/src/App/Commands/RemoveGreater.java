package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;
import App.Exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "удалить элементы, превышающие заданный", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        LabWork newLabWork = inputData.getLabWork();
        List<LabWork> toRemove = new ArrayList<>();
        for (LabWork labWork : collection.getCollection()) {
            if (labWork.compareTo(newLabWork) > 0) {
                toRemove.add(labWork);
            }
        }
        if (toRemove.isEmpty()) {
            System.out.println("Нет элементов для удаления");
        }
        collection.removeAll(toRemove);
        return "Удалено " + toRemove.size() + " элементов";
    }
}