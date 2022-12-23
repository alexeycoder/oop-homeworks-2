package abstractions;

import java.util.HashSet;

import enums.Gender;
import generics.PairSet;

public interface FamilyMember<T extends FamilyMember<T>> extends SocietyMember, HumanityMember {

	/**
	 * Родители индивида.
	 * 
	 * @return Множество прямых родителей.
	 */
	PairSet<T> getParents();

	/**
	 * Дети индивида.
	 * 
	 * @return Множество детей.
	 */
	HashSet<T> getChildren();

	/**
	 * Проверка возможности создания ребёнка с автоматическим образованием связи
	 * с данным индивидуумом.
	 * Предполагается, что в случае создания ребёнка с одним родителем, родитель
	 * может быть любого пола.
	 * 
	 * @return Всегда возвращает true в стандартной подразумеваемой реализации.
	 */
	boolean canMadeChild();

	/**
	 * Проверка возможности создания ребёнка с автоматическим образованием связи
	 * с данным индивидуумом и ещё одним индивидуумом.
	 * 
	 * @param secondParent Второй индивидуум на роль родителя создаваемого дитя.
	 * @return Вернёт true, если родители non-null и разного пола. Иначе false.
	 */
	boolean canMadeChild(T secondParent);

	/**
	 * Создаёт нового индивида, для которого будет установлено родительская
	 * связь по отношению к данному.
	 * 
	 * @param name      Имя
	 * @param gender    Пол
	 * @param birthYear Год рождения
	 * @return Экземпляр созданного индивида.
	 */
	T madeChild(String name, Gender gender, Integer birthYear);

	/**
	 * Создаёт нового индивида, для которого будет установлено родительская
	 * связь по отношению к данному и второму индивиду.
	 * 
	 * @param name         Имя
	 * @param gender       Пол
	 * @param birthYear    Год рождения
	 * @param secondParent Второй родитель. Если передано null, то у ребёнка будет
	 *                     установлен только один родитель -- данный индивид.
	 * @return Экземпляр созданного индивида.
	 */
	T madeChild(String name, Gender gender, Integer birthYear, T secondParent);

	/**
	 * Проверка на допустимость регистрации переданного в параметре индивида
	 * в качестве родителя для данного индивида.
	 * 
	 * @param parent Потенциальный родитель для данного индивида.
	 * @return true, если у данного индивида не установлено ни одного родителя,
	 *         либо уже установлен один родитель и его пол отличается от пола
	 *         проверяемого потенциального родителя.
	 */
	boolean canRegisterParent(T parent);

	/**
	 * Регистрирует индивида в качестве родителя данного индивида.
	 * 
	 * @param parent Регистрируемый в качестве родителя индивид.
	 */
	void registerParent(T parent);
}
