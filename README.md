# Cinema Seat Booking - Task 2

Level 4, Semester 1 - Java Sections

In this task I used a `HashMap` where the key is my own class (`Seat`) and the value is the customer's name. The main lesson is that Java can only find your own objects as keys if you override `equals()` and `hashCode()`.

## What the program does

- Books some seats and prints them
- Checks who booked a seat and whether a seat is taken
- Refuses to book a seat that is already taken or broken
- Moves a customer to another seat
- Prints a seating chart (`X` booked, `#` broken, `.` free)

## How to run

```bash
javac Task_CinemaBooking.java
java Task_CinemaBooking
```

## What I noticed

Before writing `equals()` and `hashCode()`, the program could not find seats that were already booked, so the same seat could be booked again. After adding them it worked as expected.

My answers to the task questions are at the bottom of the code file.
