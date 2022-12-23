import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import enums.Gender;
import implementations.Individual;

/** Примеры утилитных методов для исследования генеалогии индивидуума */
public class IndividualUtils {

	public static Optional<Individual> getMother(Individual individual) {
		return individual.getParents().stream().filter(IndividualUtils::isFemale).findFirst();
	}

	public static Optional<Individual> getFather(Individual individual) {
		return individual.getParents().stream().filter(IndividualUtils::isMale).findFirst();
	}

	public static List<Individual> getGrandmothers(Individual individual) {
		return individual.getParents().stream()
				.flatMap(ind -> ind.getParents().stream())
				.filter(IndividualUtils::isFemale).distinct().toList();
	}

	public static List<Individual> getGrandfathers(Individual individual) {
		return individual.getParents().stream()
				.flatMap(ind -> ind.getParents().stream())
				.filter(IndividualUtils::isMale).distinct().toList();
	}

	public static List<Individual> getBrothers(Individual individual) {
		return individual.getParents().stream()
				.flatMap(ind -> ind.getChildren().stream())
				.filter(IndividualUtils::isMale).distinct()
				.filter(Predicate.not(individual::equals))
				.toList();
	}

	public static List<Individual> getSisters(Individual individual) {
		return individual.getParents().stream()
				.flatMap(ind -> ind.getChildren().stream())
				.filter(IndividualUtils::isFemale).distinct()
				.filter(Predicate.not(individual::equals))
				.toList();
	}

	public static List<Individual> getGrandsons(Individual individual) {
		return individual.getChildren().stream()
				.flatMap(ind -> ind.getChildren().stream())
				.filter(IndividualUtils::isMale).distinct().toList();
	}

	public static List<Individual> getGranddaughters(Individual individual) {
		return individual.getChildren().stream()
				.flatMap(ind -> ind.getChildren().stream())
				.filter(IndividualUtils::isFemale).distinct().toList();
	}

	private static boolean isMale(Individual individual) {
		return individual.getGender() == Gender.MALE;
	}

	private static boolean isFemale(Individual individual) {
		return individual.getGender() == Gender.FEMALE;
	}
}
