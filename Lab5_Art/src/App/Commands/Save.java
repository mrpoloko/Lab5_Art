package App.Commands;

import App.Collection.CollectionLabWork;
import App.Collection.LocalDateTimeAdapter;
import App.InputData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Save extends Command {
    public Save() {
        super("save", "сохранить коллекцию в файл", 0);
    }

    @Override
    public String execute(CollectionLabWork collection, InputData inputData) {
        Path filename = inputData.getFile();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filename.toString()), StandardCharsets.UTF_8)) {
            gson.toJson(collection, writer);
            writer.flush();
            return "Коллекция сохранена в файл: " + filename;
        } catch (IOException e) {
            return "Ошибка сохранения: " + e.getMessage();
        }
    }
}