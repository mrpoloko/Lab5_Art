package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Фильтрует элементы по содержанию подстроки в имени
 */
public class FilterContainsName extends Command {
    public FilterContainsName() {
        super("filter_contains_name", 
             "вывести элементы, имя которых содержит подстроку", 1);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        String substring = inputData.getFirstArgument().toLowerCase();
        List<LabWork> filtered = new ArrayList<>();
        
        for (LabWork labWork : collection.getCollection()) {
            if (labWork.getName().toLowerCase().contains(substring)) {
                filtered.add(labWork);
            }
        }
        
        if (filtered.isEmpty()) {
            return "Элементы с именем содержащим '" + substring + "' не найдены";
        }
        
        StringBuilder result = new StringBuilder();
        filtered.forEach(labWork -> result.append(labWork).append("\n"));
        return result.toString();
    }
}