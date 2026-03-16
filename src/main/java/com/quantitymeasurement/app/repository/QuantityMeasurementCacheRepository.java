package com.quantitymeasurement.app.repository;

import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private static final String FILE_NAME = "quantity_cache.dat";

    private QuantityMeasurementCacheRepository() {
        loadFromDisk();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        cache.add(entity);

        saveToDisk(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(cache);
    }

    /* =========================================================
       FILE PERSISTENCE
       ========================================================= */

    private void saveToDisk(QuantityMeasurementEntity entity) {

        try {

            File file = new File(FILE_NAME);

            boolean append = file.exists();

            FileOutputStream fos = new FileOutputStream(file, true);

            ObjectOutputStream oos;

            if (append) {
                oos = new AppendableObjectOutputStream(fos);
            } else {
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(entity);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromDisk() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {

            while (true) {

                QuantityMeasurementEntity entity =
                        (QuantityMeasurementEntity) ois.readObject();

                cache.add(entity);
            }

        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}