import java.util.function.Function;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double getConversionFactor();
    
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // Default implementation does nothing, allowing all units to support all operations by default
    }
}
