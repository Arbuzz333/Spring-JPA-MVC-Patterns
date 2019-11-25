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

         GASEOUS(47.2),
         LIQUID(42.7),
         SOLID(15.0);

         private double calorificValue;

         StateOfAggregation(double v) {
             calorificValue = v;
         }

         public double getCalorificValue() {
             return calorificValue;
         }
     }
}
