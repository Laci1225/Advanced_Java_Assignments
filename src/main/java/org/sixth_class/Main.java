package org.sixth_class;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
class Main {
    public static <Entity> Entity geneticAlgorithm(
            Integer populationCount, BiFunction<Entity, Entity, List<Entity>> doCrossover, Consumer<Entity> mutateEntity,
            Function<Entity, Double> calculateFitness, Integer crossoverCount, Double mutationProbability,
            Integer generationCount, Supplier<Entity> createRandomEntity, Integer pruneCount) {
        List<Entity> population = new ArrayList<>(IntStream.iterate(0, i -> i + 1).limit(populationCount)
                .mapToObj(i -> createRandomEntity.get()).toList());
        Random random = new Random();
        for (int i = 0; i < generationCount; i++) {
            for (int j = 0; j < crossoverCount; j++) {
                Entity entity1 = population.get(random.nextInt(population.size()));
                Entity entity2 = population.get(random.nextInt(population.size()));
                doCrossover.apply(entity1, entity2);
            }
            if (random.nextInt(0, 100) > mutationProbability) {
                mutateEntity.accept(population.get(random.nextInt(population.size())));
            }
            population.sort(Comparator.comparing(c -> -calculateFitness.apply(c)));
            population = population.subList(0, pruneCount);
            population.addAll(IntStream.iterate(0, k -> k + 1).limit(pruneCount)
                    .mapToObj(k -> createRandomEntity.get())
                    .toList());
        }
        return population.getFirst();
    }

    public static void main(String[] args) {
    }
}