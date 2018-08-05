package com.lht.demo.java8;

import java.util.function.Supplier;

public interface Defaulable {
    default String notRequired() {
        return "Default implementation";
    }

}
 class DefaultableImpl implements Defaulable {
}

 class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}

 interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaulable create( Supplier< Defaulable > supplier ) {
        return supplier.get();
    }
}

