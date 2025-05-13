package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Группирует элементы по дате создания
 */
public class GroupCountingByCreationDate extends Command {
    public GroupCountingByCreationDate() {
        super("group_counting_by_creation_date", 
             "сгруппировать элементы по дате создания", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        Map<String, Integer> dateGroups = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        for (LabWork labWork : collection.getCollection()) {
            String dateKey = labWork.getCreationDate().format(formatter);
            dateGroups.merge(dateKey, 1, Integer::sum);
        }
        
        if (dateGroups.isEmpty()) {
            return "Коллекция пуста";
        }
        
        StringBuilder result = new StringBuilder("Группировка по дате создания:\n");
        dateGroups.forEach((date, count) -> 
            result.append(date).append(": ").append(count).append(" элементов\n"));
        
        return result.toString();
    }
}