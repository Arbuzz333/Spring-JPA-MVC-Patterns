package avakhidov.factories.enums.dough;

import avakhidov.factories.enums.GrindingFlour;

public abstract class AbstractDoughUtil {

    private AbstractDoughUtil next;

    public AbstractDoughUtil setNext(AbstractDoughUtil next) {
        this.next = next;
        return next;
    }

    public abstract KindDough findByGrinding(GrindingFlour grinding);

    protected KindDough findKindDoughByNext(GrindingFlour grinding) {
        if (next == null) {
            return KindDough.CHOUX_PASTRY;
        }
        return next.findByGrinding(grinding);
    }

}
