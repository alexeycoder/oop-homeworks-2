# Задание 2

Реализовать, с учетом ооп подхода, приложение для проведения исследований с генеалогическим древом.
*Идея:* Описать некоторое количество компонент, например, модель человека, компонента хранения связей и отношений между людьми: родитель, ребёнок -- классика, но можно подумать и про отношение, брат, свекровь, сестра и т. д.
Обеспечить переход от использования явных классов в сторону использования абстрактных типов.

## Описание типов

* `PairSet<T>` &mdash; обобщённый тип Множества, расширяющий стандартный абстрактный класс `AbstractSet<T>`. Реализует Множество, допускающее не более двух элементов.\
Используется в качестве хранилища ссылок на родителей индивида.

* `interface SocietyMember` &mdash; интерфейс, описывающий свойства Индивида как социальной сущности (в данной реализации: Имя).

* `interface HumanityMember` &mdash; интерфейс, описывающий естественные свойства Индивида как человеческого существа (Пол, Год рождения, Год смерти).

* `interface FamilyMember` &mdash; интерфейс, расширяющий упомянутые две абстракции `SocietyMember` и `HumanityMember` дополнительными свойствами и методами, позволяющими работать с индивидуумом в разрезе генеалогии, т.е. устанавливать связи с другими индивидами как с прямыми предками и прямыми потомками.

* `class Individual` &mdash; конкретная реализация абстракции `FamilyMember`.

Предложенная простейшая реализация Индивида позволяет "путешествовать" по всему генеалогическому дереву, как в нисходящем направлении (от индивида к его потомкам), так и в восходящем направлении (от индивида к предкам), поскольку использование методов `madeChild()` и `registerParent()` абстракции `FamilyMember` при построении дерева гарантирует что все сущности связаны между собой и достижимы от одного индивида к другому (по аналогии со связным списком).

* Класс `IndividualUtils` предоставляет набор утилитных методов для исследования генеалогии индивида.

## Пример исследования (в качестве тестовых данных использована часть генеалогического древа Пушкина А.С.)

