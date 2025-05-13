package App.Commands;

import App.Collection.LabWork;
import App.Collection.CollectionLabWork;
import App.InputData;

import static App.IdGenerate.generateID;

public class Add extends Command {
    public Add() {
        super("add", "добавление нового элемента в коллекцию", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        LabWork labWork = inputData.getLabWork();
        System.out.println(labWork);
        collection.add(labWork);
        collection.sortByMinimalPoint();
        return "Элемент добавлен";
    }
}