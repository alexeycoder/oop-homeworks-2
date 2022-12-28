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

	// grandparents

	public static List<Individual> getGrandparents(Individual individual) {
		return individual.getParents().stream()
				.flatMap(p -> p.getParents().stream())
				.distinct().toList();
	}

	public static List<Individual> getGrandmothers(Individual individual) {
		return getGrandparents(individual).stream()
				.filter(IndividualUtils::isFemale).toList();
	}

	public static List<Individual> getGrandfathers(Individual individual) {
		return getGrandparents(individual).stream()
				.filter(IndividualUtils::isMale).toList();
	}

	// siblings

	public static List<Individual> getSiblings(Individual individual) {
		return individual.getParents().stream()
				.flatMap(p -> p.getChildren().stream())
				.filter(Predicate.not(individual::equals))
				.distinct().toList();
	}

	public static List<Individual> getBrothers(Individual individual) {
		return getSiblings(individual).stream()
				.filter(IndividualUtils::isMale).toList();
	}

	public static List<Individual> getSisters(Individual individual) {
		return getSiblings(individual).stream()
				.filter(IndividualUtils::isFemale).toList();
	}

	// grandchildren

	public static List<Individual> getGrandchildren(Individual individual) {
		return individual.getChildren().stream()
				.flatMap(c -> c.getChildren().stream())
				.distinct().toList();
	}

	public static List<Individual> getGrandsons(Individual individual) {
		return getGrandchildren(individual).stream()
				.filter(IndividualUtils::isMale).toList();
	}

	public static List<Individual> getGranddaughters(Individual individual) {
		return getGrandchildren(individual).stream()
				.filter(IndividualUtils::isFemale).toList();
	}

	// piblings

	public static List<Individual> getPiblings(Individual individual) {
		var parents = individual.getParents();
		var parentsSiblings = parents.stream()
				.flatMap(p -> p.getParents().stream())
				.flatMap(pp -> pp.getChildren().stream())
				.filter(s -> !parents.contains(s))
				.distinct().toList();
		return parentsSiblings;
	}

	public static List<Individual> getUncles(Individual individual) {
		return getPiblings(individual).stream().filter(IndividualUtils::isMale).toList();
	}

	public static List<Individual> getAunts(Individual individual) {
		return getPiblings(individual).stream().filter(IndividualUtils::isFemale).toList();
	}

	// aux

	private static boolean isMale(Individual individual) {
		return individual.getGender() == Gender.MALE;
	}

	private static boolean isFemale(Individual individual) {
		return individual.getGender() == Gender.FEMALE;
	}
}
