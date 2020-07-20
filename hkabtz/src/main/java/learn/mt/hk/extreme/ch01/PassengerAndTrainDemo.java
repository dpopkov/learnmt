package learn.mt.hk.extreme.ch01;

import net.jcip.annotations.Immutable;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Demonstrates lambda and method references usage. */
@Immutable
public class PassengerAndTrainDemo {
    @SuppressWarnings({"Convert2MethodRef", "SpellCheckingInspection"})
    public static void main(String[] args) {
        Train train = Train.create(Train::new);
        List<Train> trains = Stream.of(train).collect(Collectors.toList());

        trains.forEach(t -> Train.paintBlue(t));
        trains.forEach(Train::paintBlue);

        trains.forEach(t -> t.repair());
        trains.forEach(Train::repair);

        Passenger p = new Passenger();
        trains.forEach(p::onboard);

        Stream.of("Maxi", "Connie", "Bangie", "Efi")
                .filter(n -> n.matches(".*[eE].*"))
                .sorted()
                .mapToInt(String::length)
                .forEach(System.out::println);
    }

    static class Passenger {
        public void onboard(Train train) {
            System.out.println("Onboard " + train);
        }
    }

    static class Train {
        public static Train create(Supplier<Train> supplier) {
            return supplier.get();
        }

        public static void paintBlue(Train train) {
            System.out.println("Painted blue " + train);
        }

        public void repair() {
            System.out.println("Repaired " + this);
        }
    }
}
