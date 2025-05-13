package App.Commands;

import App.Collection.CollectionLabWork;
import App.InputData;

public abstract class Command {
    private final String name;
    private final String description;
    private final int countOfArguments;

    public Command(String name, String description, int countOfArguments) {
        this.name = name;
        this.description = description;
        this.countOfArguments = countOfArguments;
    }

    public abstract String execute(CollectionLabWork collection, InputData inputData);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCountOfArguments() {
        return countOfArguments;
    }
}