package dk.acto.blackdragon.service.impl;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.service.ModelFactory;
import io.vavr.collection.List;

import java.util.Arrays;

public class ModelFactoryImpl implements ModelFactory<Model> {
    @Override
    public List<Model> parse(String string) {
        String[] lines = string.split("\n");
        java.util.List<Model> models = Arrays.stream(lines)
                .skip(1)
                .map(line -> {
                    String[] values = line.split(",");
                    return Model.builder()
                            .id(Long.parseLong(values[0].trim()))
                            .weight(Double.parseDouble(values[1].trim()))
                            .cost(Integer.parseInt(values[2].trim()))
                            .inventory(Long.parseLong(values[3].trim()))
                            .build();
                }).toList();
        return List.ofAll(models);
    }
}
