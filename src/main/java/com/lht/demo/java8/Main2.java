package com.lht.demo.java8;

public class Main2 {
   public static void main( String[] args ) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new );
        System.out.println( defaulable.notRequired() );

        defaulable = DefaulableFactory.create( OverridableImpl::new );
        System.out.println( defaulable.notRequired() );
    }
}
