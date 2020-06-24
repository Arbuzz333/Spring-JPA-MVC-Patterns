package avakhidov.factories.entity.ingredient;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;



public class IngredientDequeCircle {

    private Deque<SupplementIngredient> deque;

    public IngredientDequeCircle(
            List<SupplementIngredient> ingredientList) {
        deque = new ArrayDeque<>(ingredientList);
    }

    public SupplementIngredient getIngredient() {
        SupplementIngredient last = deque.pollLast();
        deque.addFirst(last);

        return last;
    }
}
