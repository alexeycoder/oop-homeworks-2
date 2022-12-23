import enums.Gender;
import implementations.Individual;

/** Тестовое генеалогическое древо Пушкиных */
public class TestFamily {
	public static Individual getFamilyRoot() {

		var root = new Individual("Пушкин Александр Сергеевич", Gender.MALE, 1799, 1837);

		var father = new Individual("Пушкин Сергей Львович", Gender.MALE, 1770, 1848);
		father.registerParent(new Individual("Пушкин Лев Александрович", Gender.MALE, 1723, 1790));
		father.registerParent(new Individual("Чичерина Ольга Васильевна", Gender.FEMALE, 1737, 1802));

		var mother = new Individual("Ганнибал Надежда Осиповна", Gender.FEMALE, 1775, 1836);
		mother.registerParent(new Individual("Ганнибал Осип Абрамович", Gender.MALE, 1744, 1807));
		mother.registerParent(new Individual("Пушкина Мария Алексеевна", Gender.FEMALE, 1745, 1819));

		mother.madeChild("Пушкин Лев Сергеевич", Gender.MALE, 1805, father).setDeathYear(1852);
		mother.madeChild("Павлищева Ольга Сергеевна", Gender.FEMALE, 1797, father).setDeathYear(1865);

		root.registerParent(mother);
		root.registerParent(father);

		var wife = new Individual("Гончарова Наталья Николаевна", Gender.FEMALE, 1812);
		wife.setDeathYear(1863);

		var child1 = root.madeChild("Гартунг Мария Александровна", Gender.FEMALE, 1832, wife);
		child1.setDeathYear(1919);

		var child2 = root.madeChild("Пушкин Александр Александрович", Gender.MALE, 1833, wife);
		child2.setDeathYear(1914);

		var child3 = root.madeChild("Пушкин Григорий Александрович", Gender.MALE, 1835, wife);
		child3.setDeathYear(1905);

		var child4 = root.madeChild("Меренберг Наталья Александровна", Gender.FEMALE, 1836, wife);
		child4.setDeathYear(1913);
		var child4husband = new Individual("Нассауский Николай Вильгельм", Gender.MALE, 1832, 1905);

		child4.madeChild("Меренберг София Николаевна", Gender.FEMALE, 1868, child4husband).setDeathYear(1927);
		child4.madeChild("Меренберг Георг-Николай", Gender.MALE, 1871, child4husband).setDeathYear(1948);
		child4.madeChild("Меренберг Александра Николаевна ", Gender.FEMALE, 1869, child4husband).setDeathYear(1950);

		return root;
	}
}
