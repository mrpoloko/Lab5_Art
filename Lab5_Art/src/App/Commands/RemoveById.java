package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;
import App.Exception.NotFoundException;

import static App.IdGenerate.releaseId;

public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id", "удалить элемент по ID", 1);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        Integer id = Integer.parseInt(inputData.getFirstArgument());
        LabWork labWork = collection.getById(id);
        if (labWork == null) {
            throw new NotFoundException("Элемент с ID " + id + " не найден");
        }
        releaseId(labWork.getId());
        collection.remove(labWork);
        return "Элемент с ID " + id + " удален";
    }
}