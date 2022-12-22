package abstractions;

import java.time.LocalDate;
import java.util.HashSet;

import enums.Gender;
import generics.PairSet;

public interface HumanityMember {
	LocalDate getBirthDate();

	LocalDate getDeathDate();

	void setDeathDate(LocalDate date);

	Gender getGender();


}
