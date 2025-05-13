package App.Commands;

import App.Collection.CollectionLabWork;
import App.InputData;

public class Help extends Command {
    public Help() {
        super("help", "справка по доступным командам", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        StringBuilder sb = new StringBuilder();
        for (Command cmd : inputData.getCommandManager().getCommands()) {
            sb.append(cmd.getName()).append(": ").append(cmd.getDescription()).append("\n");
        }
        return sb.toString();
    }
}