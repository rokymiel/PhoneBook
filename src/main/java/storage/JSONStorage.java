package storage;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class JSONStorage<T> implements Storable<T> {
    private Set<T> items;
    private final Gson gson;
    private final Class<T> type;
    private String dataPath;
    private Optional<Logger> logger;

    public JSONStorage(String dataPath, Class<T> type) throws IOException {
        try (FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Optional.of(Logger.getLogger(JSONStorage.class.getName()));
        } catch (IOException e) {
            logger = Optional.empty();
        }
        this.type = type;
        this.dataPath = dataPath;
        gson = new Gson();
        items = new HashSet<>();
        if (Files.exists(Paths.get(dataPath))) {
            loadData();
        }
    }

    private void loadData() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                items.add(gson.fromJson(inputLine, type));
            }
        } catch (IOException ex) {
            logger.ifPresent(logger -> logger.log(Level.WARNING, "Load data exception", ex));
            throw ex;
        }
        logger.ifPresent(logger -> logger.log(Level.INFO, "Loaded " + items.size() + " items"));
    }

    private void saveChanges() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath))) {
            for (T item : items) {
                bw.write(gson.toJson(item));
                bw.newLine();
            }
        } catch (IOException ex) {
            logger.ifPresent(logger -> logger.log(Level.WARNING, "Saving changes exception", ex));
            throw ex;
        }
        logger.ifPresent(logger -> logger.log(Level.INFO, "Saved " + items.size() + " items"));
    }

    @Override
    public T[] getAll() {
        return items.toArray((T[]) java.lang.reflect.Array.newInstance(type, 0));
    }

    @Override
    public boolean add(T item) throws IOException {
        if (items.add(item)) {
            logger.ifPresent(logger -> logger.log(Level.INFO, "Added new item"));
            saveChanges();
            return true;
        }
        return false;

    }

    @Override
    public boolean tryRemove(T item) throws IOException {
        if (items.remove(item)) {
            saveChanges();
            logger.ifPresent(logger -> logger.log(Level.INFO, "Removed new item"));
            return true;
        }
        return false;
    }

    @Override
    public T[] getAllWhere(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList()).toArray((T[]) java.lang.reflect.Array.newInstance(type, 0));
    }

    @Override
    public <K> Map<K, List<T>> getGrouped(Function<? super T, ? extends K> function) {
        return items.stream().collect(Collectors.groupingBy(function));
    }

}
