package dk.acto.blackdragon.service.impl;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.service.ModelFactory;
import io.vavr.collection.List;

import java.util.Arrays;

public class ModelFactoryImpl implements ModelFactory<Model> {
    @Override
    public List<Model> parse(String string) {
        String[] lines = string.split("\n");

        List<Model> models = List.empty();

        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            Model model = Model.builder()
                    .id(Long.parseLong(values[0].trim()))
                    .weight(Double.parseDouble(values[1].trim()))
                    .cost(Integer.parseInt(values[2].trim()))
                    .inventory(Long.parseLong(values[3].trim()))
                    .build();

            models = models.append(model);
        }
        return models;
    }
}
