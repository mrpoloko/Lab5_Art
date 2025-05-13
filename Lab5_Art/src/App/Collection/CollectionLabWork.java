package App.Collection;

import App.Exception.CollectionLoadException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

import static App.Validation.validateCollection;

public class CollectionLabWork {
    private TreeSet<LabWork> collection = new TreeSet<>();
    private Date initializationDate = new Date();

    public TreeSet<LabWork> getCollection() {
        return collection;
    }

    public void add(LabWork labWork) {
        collection.add(labWork);
    }

    public void remove(LabWork element) {
        collection.remove(element);
    }


    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает размер коллекции.
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }


    /**
     * Находит элемент по ID.
     * @param id идентификатор для поиска
     * @return найденный объект или null
     */

    public LabWork getById(Integer id) {
        for (LabWork LabWork : collection) {
            if (Objects.equals(LabWork.getId(), id)) {
                return LabWork;
            }
        }
        return null;
    }

    /**
     * Удаляет группу элементов из коллекции.
     * @param c коллекция элементов для удаления
     */
    public void removeAll(java.util.Collection<LabWork> c) {
        collection.removeAll(c);
    }

    private Date getInitializationDate(){
        return initializationDate;
    }


    /**
     * Загружает коллекцию из файла.
     * @param filename путь к файлу
     * @throws CollectionLoadException если произошла ошибка загрузки
     */
    public void loadFromFile(Path filename) {
        try {
            if (Files.size(filename) == 0) {
                System.out.println("Создана пустая коллекция");
                return;
            }

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(filename), StandardCharsets.UTF_8)) {
                CollectionLabWork wrapper = gson.fromJson(reader, CollectionLabWork.class);

                initializationDate = wrapper.initializationDate;

                if (wrapper.getCollection() != null) {
                    validateCollection(wrapper.getCollection());

                    collection.clear();
                    collection.addAll(wrapper.getCollection());
                    sortByMinimalPoint();
                    System.out.println("Загружено " + wrapper.getCollection().size() + " элементов.");
                } else {
                    System.out.println("Файл пуст или содержит некорректные данные");
                }
            }
        } catch (Exception e) {
            throw new CollectionLoadException("Ошибка загрузки коллекции: " + e.getMessage());
        }
    }

    public void sortByMinimalPoint() {
        ArrayList<LabWork> list = new ArrayList<>(collection);

        // Сортируем с помощью Comparator
        Collections.sort(list, new Comparator<LabWork>() {
            @Override
            public int compare(LabWork labWork1, LabWork labWork2) {
                return Float.compare(labWork1.getMinimalPoint(), labWork2.getMinimalPoint());
            }
        });

        // Очищаем и заполняем заново
        collection.clear();
        collection.addAll(list);
    }

    /**
     * Возвращает информацию о коллекции.
     * @return строковое представление коллекции
     */
    @Override
    public String toString() {
        return "CollectionLabWork {\n" +
                "  Type: " + collection.getClass().getSimpleName() + "\n" +
                "  Initialization Date: " + getInitializationDate() + "\n" +
                "  Size: " + collection.size() + "\n" +
                "}";
    }
}
