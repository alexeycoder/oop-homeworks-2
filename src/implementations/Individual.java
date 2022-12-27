package implementations;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import abstractions.FamilyMember;
import enums.Gender;
import generics.MutableSet;
import generics.PairSet;

/** Индивид. Базовый элемент самоописываемой генеалогической структуры. */
public class Individual implements FamilyMember<Individual> {

	private String name;
	private final Gender gender;
	private final Integer birthYear;
	private Integer deathYear; // the only null-allowed field
	private final PairSet<Individual> parents;
	private final MutableSet<Individual> children;

	public Individual(String name, Gender gender, Integer birthYear) {
		this(name, gender, birthYear, null);
	}

	public Individual(String name, Gender gender, Integer birthYear, Integer deathYear) {
		this.name = Objects.requireNonNullElse(name, "");
		this.gender = Objects.requireNonNull(gender);
		this.birthYear = Objects.requireNonNull(birthYear);
		this.deathYear = deathYear;
		this.parents = new PairSet<Individual>();
		this.children = new MutableSet<Individual>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = Objects.requireNonNullElse(name, "");
	}

	@Override
	public Integer getBirthYear() {
		return birthYear;
	}

	@Override
	public Optional<Integer> getDeathYear() {
		return Optional.ofNullable(deathYear);
	}

	@Override
	public void setDeathYear(Integer year) {
		deathYear = year;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public Set<Individual> getParents() {
		return parents;
	}

	@Override
	public Set<Individual> getChildren() {
		return children;
	}

	@Override
	public boolean canMadeChild() {
		return true;
	}

	@Override
	public boolean canMadeChild(Individual secondParent) {
		return secondParent == null || secondParent == this || secondParent.gender != this.gender;
	}

	@Override
	public Individual madeChild(String name, Gender gender, Integer birthYear) {
		return madeChild(name, gender, birthYear, null);
	}

	@Override
	public Individual madeChild(String name, Gender gender, Integer birthYear, Individual secondParent) {
		var child = new Individual(name, gender, birthYear);
		child.registerParent(this);
		if (secondParent != null && secondParent != this) {
			if (!canMadeChild(secondParent)) {
				throw new IllegalArgumentException("Not allowed secondParent.");
			}
			child.registerParent(secondParent);
		}
		children.add(child);
		return child;
	}

	@Override
	public boolean canRegisterParent(Individual parent) {
		if (parent == null) {
			return false;
		}
		if (parents.size() == 0) {
			return true;
		}
		if (parents.size() >= PairSet.MAX_SIZE) {
			return false;
		}
		if (parents.contains(parent)) {
			return false;
		}
		var existingParent = parents.stream().findFirst().get();
		if (parent.gender == existingParent.gender) {
			return false;
		}
		return true;
	}

	@Override
	public void registerParent(Individual parent) {
		if (parent == null) {
			throw new NullPointerException();
		}
		if (parents.size() >= PairSet.MAX_SIZE) {
			throw new IllegalStateException(String.format(
					"The Individual [%s] already has the maximum allowed number of blood parents.",
					this.toString()));
		}
		if (!canRegisterParent(parent)) {
			throw new IllegalArgumentException(
					"Not allowed parent to register: either already registered or of same gender.");
		}
		parents.add(parent);
	}

	@Override
	public String toString() {
		String str = String.format("%s, %s, %s", name, gender == Gender.MALE ? "Муж." : "Жен.", birthYear.toString());
		if (deathYear != null) {
			str += "\u2014" + deathYear.toString();
		}
		return str;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Individual that = (Individual) o;
		return name.equals(that.name) && gender == that.gender && birthYear.equals(that.birthYear)
				&& Objects.equals(deathYear, that.deathYear) && parents.equals(that.parents)
				&& children.equals(that.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, gender, birthYear, deathYear);
		// return Objects.hash(name, gender, birthYear, deathYear, parents, children);
	}
}
