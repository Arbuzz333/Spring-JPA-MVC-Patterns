package avakhidov.factories.config;

import avakhidov.factories.entity.ingredient.IngredientDequeCircle;
import avakhidov.factories.entity.ingredient.ScopeSupplementIngredient;
import avakhidov.factories.entity.ingredient.SupplementIngredient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;




@Configuration
public class AppContextIngredient {

    private final List<SupplementIngredient> ingredientList;

    public AppContextIngredient(
            List<SupplementIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public IngredientDequeCircle ingredientDequeCircle() {
        return new IngredientDequeCircle(ingredientList);
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public ScopeSupplementIngredient scopeSupplementIngredient() {
        IngredientDequeCircle generator = ingredientDequeCircle();

        return new ScopeSupplementIngredient() {
            @Override
            public SupplementIngredient getScopeIngredient() {
                return generator.getIngredient();
            }
        };
    }
}
