package avakhidov.factories.entity.cutlet;

import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.ingredient.Sesame;
import avakhidov.factories.entity.meat.PorkMeat;
import avakhidov.factories.service.BuildParameterPrepareDough;

public class PorkCutlet extends Cutlet<PorkMeat> {

    public PorkCutlet(PorkMeat meat, boolean recipeReady, double weight) {
        super(meat, recipeReady, weight);
    }

    public static class Builder extends Product.Builder<PorkMeat> {

        private boolean recipeReady = false;
        private BuildParameterPrepareDough parameterDough;
        private Sesame sesame;
        private double weightBun;


        public Builder withRecipeReady(boolean recipeReady) {
            this.recipeReady = recipeReady;
            return this;
        }

        public Builder withParameterDough(BuildParameterPrepareDough parameterDough) {
            this.parameterDough = parameterDough;
            return this;
        }

        public Builder withSesame(Sesame sesame) {
            this.sesame = sesame;
            return this;
        }

        public Builder withWeightBun(double weightBun) {
            this.weightBun = weightBun;
            return this;
        }

        public PorkCutlet build() {
            PorkCutlet porkCutlet = new PorkCutlet(mainIngredient, recipeReady, weight);
            porkCutlet.createSesameBun(parameterDough, recipeReady, sesame, weightBun);
            return porkCutlet;
        }
    }
}
