package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;
import App.Exception.NotFoundException;

public class UpdateId extends Command {
    public UpdateId() {
        super("update", "обновить элемент по ID", 1);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        Integer id = Integer.parseInt(inputData.getFirstArgument());
        LabWork existing = collection.getById(id);
        if (existing == null) {
            throw new NotFoundException("Элемент с ID " + id + " не найден");
        }
        LabWork updated = inputData.getLabWork();
        existing.update(updated); // Предполагается, что у LabWork есть метод update()
        return "Элемент с ID " + id + " обновлен";
    }
}