package com.lht.demo.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main3  {
    private enum Status {
        ONE, TWO,THREE
    };

    private static final class User {
        private final String status;
        private final Integer points;

        User(final String status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    public static void main(String[] args) {
        final Collection<User> users = Arrays.asList(
                new User( "1岁", 5 ),
                new User( "2岁", 13 ),
                new User( "3岁", 8 )
        );
        List<User> list=users.stream().filter(user -> user.getPoints()>8).collect(Collectors.toList());
        System.out.println(list);





        final long totalUser = users
                .stream()
                .filter( task -> task.getPoints() > 5 )
                .mapToInt( User::getPoints )//这里如果是map方法只能跟reduce方法进行求和
//                .sum();
        //or
                .reduce(0,Integer::sum);

        System.out.println( "Total points: " + totalUser );

    }
}
