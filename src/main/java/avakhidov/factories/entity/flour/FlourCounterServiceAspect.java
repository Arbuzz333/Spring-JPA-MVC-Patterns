package avakhidov.factories.entity.flour;

import avakhidov.factories.annotations.FlourCounter;
import avakhidov.factories.bakery.BakeryConditionBun;
import avakhidov.factories.entity.Product;
import avakhidov.factories.entity.dough.ParameterPrepareDough;
import avakhidov.factories.enums.KindFlour;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


@Aspect
@Configuration
public class FlourCounterServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(BakeryConditionBun.class);

    //ToDo put to DB
        private Map<KindFlour, Double> flourDoubleMap = new TreeMap<>(Comparator.comparing(KindFlour::getTitle));

    @AfterReturning("execution(* avakhidov.factories.entity.bun.BunLiteFactory.getBunLite(..))")
    private void countFlour(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        Product<ParameterPrepareDough<? extends Flour>> product = (Product<ParameterPrepareDough<? extends Flour>>) args[2];

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        FlourCounter[] annotationsByType = method.getAnnotationsByType(FlourCounter.class);
        List<KindFlour> kindFlours = Arrays.stream(annotationsByType).map(FlourCounter::kindFlour).collect(Collectors.toList());
        KindFlour kind = product.getMainIngredient().getFlour().getKind();
        if (kindFlours.contains(kind)) {
            OperatorOperatorCounterFlour v = OperatorOperatorCounterFlour.valueOf(kind.name());
            double quantityFlour = v.countFlour();
            logger.info("Kind of flour: {}. Weight flour: {}", kind.name(), quantityFlour);
            flourDoubleMap.merge(kind, quantityFlour, Double::sum);
        }
    }

    public Map<KindFlour, Double> getFlourDoubleMap() {
        return flourDoubleMap;
    }

    public enum OperatorOperatorCounterFlour implements OperatorCounterFlourInterface {

        BUCKWHEAT {
            @Override
            public double countFlour() {
                return coefficient_buckwheat * weight;
            }
        },

        CORN {
            @Override
            public double countFlour() {
                return coefficient_corn * weight;
            }
        },

        WHEAT;

        static double weight;
        static double coefficient_wheat;
        static double coefficient_corn;
        static double coefficient_buckwheat;

        @Component
        static class ReportOperatorOperatorCounterFlour {

            private final ReportOperatorCounterFlourService report;

            private ReportOperatorOperatorCounterFlour(ReportOperatorCounterFlourService report) {
                this.report = report;
            }

            @PostConstruct
            private void postConstruct() {
                for (OperatorOperatorCounterFlour flour : EnumSet.allOf(OperatorOperatorCounterFlour.class)) {
                    flour.setDataPrepareService(report);
                }
            }
        }

        private void setDataPrepareService(ReportOperatorCounterFlourService report) {
            weight = report.getWeight();
            coefficient_buckwheat = report.getCoefficientBuckwheat();
            coefficient_corn = report.getCoefficientCorn();
            coefficient_wheat = report.getCoefficientWheat();
        }
    }
}
