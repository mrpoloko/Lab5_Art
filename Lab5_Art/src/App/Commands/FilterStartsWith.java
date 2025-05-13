package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

import java.util.ArrayList;
import java.util.List;

public class FilterStartsWith extends Command {
    public FilterStartsWith() {
        super("filter_starts_with_name", "вывести элементы, имя которых начинается с заданной подстроки", 1);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        String prefix = inputData.getFirstArgument();
        List<LabWork> filtered = new ArrayList<>();
        for (LabWork labWork : collection.getCollection()) {
            if (labWork.getName().startsWith(prefix)) {
                filtered.add(labWork);
            }
        }
        return filtered.toString();
    }
}