package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

/**
 * Вычисляет среднее значение minimalPoint для коллекции
 */
public class AverageOfMinimalPoint extends Command {
    public AverageOfMinimalPoint() {
        super("average_of_minimal_point", "вывести среднее значение поля minimalPoint", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        if (collection.size() == 0) {
            return "Коллекция пуста";
        }
        
        double sum = 0;
        for (LabWork labWork : collection.getCollection()) {
            sum += labWork.getMinimalPoint();
        }
        
        double average = sum / collection.size();
        return String.format("Среднее значение minimalPoint: %.2f", average);
    }
}