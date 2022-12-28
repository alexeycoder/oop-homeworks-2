import java.util.List;
import java.util.stream.Collectors;

import implementations.Individual;

public class App {
    private static void printSeparator() {
        System.out.println("=".repeat(40));
    }

    public static void printIndividuals(List<Individual> individuals) {
        if (individuals == null || individuals.size() == 0) {
            System.out.println("Не найдено.");
        } else {
            System.out.println(individuals.stream().map(Individual::toString).collect(Collectors.joining("\n")));
        }
    }

    private static void investigate(Individual root) {
        System.out.print("\nОПОРНЫЙ ИНДИВИД: ");
        System.out.println(root);

        System.out.println();
        System.out.println("БРАТЬЯ:");
        printIndividuals(IndividualUtils.getBrothers(root));

        System.out.println();
        System.out.println("СЁСТРЫ:");
        printIndividuals(IndividualUtils.getSisters(root));

        System.out.println();
        System.out.println("БАБУШКИ:");
        printIndividuals(IndividualUtils.getGrandmothers(root));

        System.out.println();
        System.out.println("ДЕДУШКИ:");
        printIndividuals(IndividualUtils.getGrandfathers(root));

        System.out.println();
        System.out.println("ПРАВНУКИ:");
        printIndividuals(IndividualUtils.getGrandsons(root));

        System.out.println();
        System.out.println("ПРАВНУЧКИ:");
        printIndividuals(IndividualUtils.getGranddaughters(root));

        System.out.println();
        System.out.println("ДЯДИ:");
        printIndividuals(IndividualUtils.getUncles(root));

        System.out.println();
        System.out.println("ТЁТИ:");
        printIndividuals(IndividualUtils.getAunts(root));
        System.out.println();
    }

    public static void main(String[] args) {
        var root = TestFamily.getFamilyRoot();

        printSeparator();
        System.out.println("Исследование по генеалогическому дереву");
        printSeparator();

        investigate(root);

        printSeparator();

        investigate(
                root.getChildren().stream().filter(i -> i.getName().contains("Меренберг Наталья")).findFirst().get());
    }
}
