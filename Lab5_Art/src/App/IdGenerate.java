package App;

import App.Exception.IdException;

import App.Collection.LabWork;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для генерации уникальных ID.
 */
public class IdGenerate {
    private static final Set<Integer> idSet = new HashSet<>();
    /**
     * Значение минимального доступного ID.
     */
    private static int nextFreeID = 1;

    /**
     * Генерирует уникальный ID.
     * @return сгенерированный ID
     */
    public static int generateID() {
        while (idSet.contains(nextFreeID)) {
            nextFreeID++;
        }
        idSet.add(nextFreeID);
        return nextFreeID;
    }

    /**
     * Сбрасывает генератор ID.
     */
    public static void resetId() {
        nextFreeID = 1;
        idSet.clear();
    }

    /**
     * Освобождает ID.
     * @param id ID для освобождения
     * @throws IdException если ID не был занят
     */
    public static void releaseId(Integer id) {
        if(!idSet.remove(id)) {
            throw new IdException();
        }
        nextFreeID = Math.min(nextFreeID, id);
    }

    /**
     * Проверяет уникальность ID в коллекции.
     * @param coll коллекция объектов для проверки
     * @throws IdException если обнаружены дубликаты ID
     */
    public static void validateId(TreeSet<LabWork> coll) {
        int counter = 0;
        for (LabWork labWork : coll) {
            idSet.add(labWork.getId());
            counter++;

            if (idSet.size() != counter) {
                throw new IdException();
            }
        }
    }
}
