# Patterns
* BakeryHouseFSM - конечный автомат
* BakeryConditionBun - компонент + BakeryHouseFSM
* Oven<T> - состояние (фабричный метод)
* Recipe<T> - абстрактная фабрика
* BakeryHouseFSMSingletonEnum - SingleTone Enum
* PreheatedBunOvenSingleton - Thread-safe Singleton class. The instance is lazily initialized.
* HoldOvenBunSingleton - Double check locking Singleton.
* OvenToGrill - adapter
* BuilderPorkCutlet and BuilderBuckwheatDough - inner extended builder
* OuterBuilderPorkCutlet outer extended builder
* HyperMarket - Компоновщик
* SupplementIngredientDecorator - Decorator
* BunLiteFactory - Flyweight
* KitchenFreezer - Заместитель