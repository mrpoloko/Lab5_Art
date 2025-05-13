package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;
import App.Exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Удаляет из коллекции все элементы, меньшие чем заданный
 */
public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_lower", "удалить из коллекции все элементы, меньшие чем заданный", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        LabWork comparisonLabWork = inputData.getLabWork();
        List<LabWork> toRemove = new ArrayList<>();
        
        for (LabWork labWork : collection.getCollection()) {
            if (labWork.compareTo(comparisonLabWork) < 0) {
                toRemove.add(labWork);
            }
        }
        
        if (toRemove.isEmpty()) {
            System.out.println("Нет элементов для удаления");
        }
        
        collection.removeAll(toRemove);
        return "Удалено " + toRemove.size() + " элементов, меньших чем заданный";
    }
}