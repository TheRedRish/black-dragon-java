package dk.acto.blackdragon.service.impl;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.model.Stats;
import dk.acto.blackdragon.service.ModelTransformer;
import io.vavr.collection.List;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ModelTransformerImpl implements ModelTransformer<Model, Stats> {
    @Override
    public Stats transform(List<Model> model) {
        BigInteger evenIds = BigInteger.valueOf(model.filter(m -> m.getId() % 2 == 0).size());
        BigInteger oddIds = BigInteger.valueOf(model.filter(m -> m.getId() % 2 != 0).size());

        double meanCostSum = 0;
        for (Model m : model) {
            meanCostSum += m.getCost() / (double) 100; // Convert from cents to dollars
        }
        BigDecimal meanCost = BigDecimal.valueOf(meanCostSum / model.size()).setScale(0, RoundingMode.HALF_UP);

        double weightedInventorySum = 0;
        for (Model m : model) {
            weightedInventorySum += m.getInventory() * m.getWeight();
        }
        BigDecimal weightedInventory = BigDecimal.valueOf(weightedInventorySum).setScale(2, RoundingMode.HALF_UP);

        Number totalInventorySum = model.map(Model::getInventory).sum();
        BigInteger totalInventory = BigInteger.valueOf(totalInventorySum.intValue());

        Stats result = Stats.builder()
                .evenIds(evenIds)
                .oddIds(oddIds)
                .meanCost(meanCost)
                .weightedInventory(weightedInventory)
                .totalInventory(totalInventory)
                .build();
        return result;
    }

//    assertEquals(result.getEvenIds(), BigInteger.valueOf(6));
//    assertEquals(result.getOddIds(), BigInteger.valueOf(7));
//    assertEquals(result.getMeanCost(), BigDecimal.valueOf(11));
//    assertEquals(result.getWeightedInventory(), BigDecimal.valueOf(2436800, 2));
//    assertEquals(result.getTotalInventory(), BigInteger.valueOf(48197));
}
