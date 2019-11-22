package avakhidov.factories.grill.energy;

public interface Energy {

    Calorific getEnergy();

    StateOfAggregation getAggregation();

     class Calorific {

         private double calorificValue;

         public Calorific(double calorificValue) {
             this.calorificValue = calorificValue;
         }

         public double getCalorificValue() {
             return calorificValue;
         }
     }

     enum StateOfAggregation {

         GASEOUS,
         LIQUID,
         SOLID
     }
}
