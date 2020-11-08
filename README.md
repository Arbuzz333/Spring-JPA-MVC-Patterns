# Patterns
* BakeryHouseFSM - конечный автомат
* BakeryConditionBun - компонент + BakeryHouseFSM
* Oven<'T'> - состояние (фабричный метод)
* Recipe<'T'> - абстрактная фабрика
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
* AbstractDoughUtil - Цепочка обязанностей
* CommandOrders - Команда
* PancakeVisitor - Visitor
* BakeryCommandExecutor - Снимок + Команда
* MeatServiceFactoryBean - пример реализации FactoryBean и InitializingBean
   *если использовать xml кофигурацию, то генерирует bean, заменяя метод @Bean в java кофигурации*
* Default method injection from Spring
* Spring pattern post construct - @MainRunner
* Spring @TestConfiguration and @ContextHierarchy - ProcessMortgageAgreementTest, ProcessCarLoanAgreementTest
* Annotation @SelfAutowired - Spring  bean itself injection 
* Annotation @PostInitialize
* InitMethodInterfaceRegistryBeanFactoryPostProcessor - scanning InitMethod in interface 
* ContractConfig - spring autowiring в fields имеет более высокий приоритет над конструктором 
   </n> *в fields бины с дженериками инжектятся по принципу List<? extend Number>. В такие бины </n>
   List<? extend Number> будут инжектится только бины SomeObject< Number > независимо от extends* 