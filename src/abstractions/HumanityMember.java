package abstractions;

import java.util.Optional;

import enums.Gender;

public interface HumanityMember {
	
	Integer getBirthYear();

	Optional<Integer> getDeathYear();

	void setDeathYear(Integer year);

	Gender getGender();
}
