import java.util.*;

class Task_CinemaBooking {

    // Seat class (HashMap key)
    static class Seat {
        final char row;
        final int number;

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        // equals()
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat)) return false;
            Seat other = (Seat) o;
            return row == other.row && number == other.number;
        }

        // hashCode()
        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {

        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A: Book seats
        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

        // Part B: Who booked C3
        System.out.println("Who booked C3:      " + bookings.get(new Seat('C', 3)));

        // Part C: Check A2 and E6
        System.out.println("Is A2 taken:        " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken:        " + bookings.containsKey(new Seat('E', 6)));

        // Part D: Youssef wants B5
        String owner = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (owner == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + owner);
        }

        // Part E: Ali moves from A1 to D2
        bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), "Ali");
        System.out.println("After Ali moved:    " + bookings);

        // Part F: Broken seats
        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));
        if (broken.contains(new Seat('E', 1))) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(new Seat('E', 1), "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        // Part G: Number of booked seats
        System.out.println("Seats booked:       " + bookings.size());

        // Seating chart
        System.out.println();
        printChart(bookings, broken);
    }

    // Print chart
    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}

/*
 QUESTIONS

 Q1. Why did we have to override hashCode() and equals()?
 Answer: Because the key is a class we made ourselves. Java doesn't know that
 two B5 objects mean the same seat, it only sees two different objects. So we
 tell it how to compare them with equals(), and hashCode() helps the HashMap
 know where to look.

 Q2. Delete your hashCode() but keep equals(). What happens? Why?
 Answer: It goes wrong again, like C3 gives null. Even though equals() says the
 seats are the same, they get different hash codes, so the map looks in the
 wrong place and never finds them.

 Q3. Why are row and number final? What could go wrong otherwise?
 Answer: So nobody can change a seat after creating it. If we changed the number
 of a booked seat, its hash code would change but the map would still keep it in
 the old place. We would lose it, and someone else could book the same seat.

 Q4. Change equals(Object o) to equals(Seat o). What happens?
     Then also remove @Override. What happens now?
 Answer: First it doesn't compile, because @Override notices we are not really
 overriding anything. After removing @Override it compiles, but the results are
 wrong. It's just a new overloaded method, and the HashMap still uses the old
 equals(Object).

 BONUS. record Seat(char row, int number) {}
 Answer: It works fine, because a record writes equals(), hashCode() and
 toString() for us. The only difference I noticed is the printing, we get
 Seat[row=A, number=1] instead of A1.
*/
