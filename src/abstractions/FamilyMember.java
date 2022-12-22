package abstractions;

import java.util.HashSet;

import generics.PairSet;

public interface FamilyMember {
	PairSet<FamilyMember> getParents();

	HashSet<FamilyMember> getChildren();

	void addChild(HumanityMember child);
}
