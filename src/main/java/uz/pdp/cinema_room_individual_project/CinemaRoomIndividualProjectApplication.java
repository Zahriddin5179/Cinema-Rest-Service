package uz.pdp.cinema_room_individual_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

@SpringBootApplication
public class CinemaRoomIndividualProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaRoomIndividualProjectApplication.class, args);

//        LocalDate startDate = LocalDate.parse("25.03.2022", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        LocalDate endDate = LocalDate.parse("03.04.2022", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        long daysBetween = DAYS.between(startDate, endDate);
//
//        System.out.println(daysBetween);
        double precision = 2900/971;
        System.out.println("Ticket price: ");
        Scanner scanner = new Scanner(System.in);
        double ticket = scanner.nextDouble();
        double komissiya = ticket*precision/100 + 30;
        System.out.println("Komissiya: " + komissiya);
        Double totalPrice = ticket + komissiya;
        System.out.println("Jami: " + totalPrice + " yechib olindi.");

        double stripe = totalPrice * 0.029 - 30;
        System.out.println("Stripe oladigan pul: " + stripe);

        double qoladigan  = totalPrice - stripe;
        System.out.println("Qoladigan pul: " + qoladigan);

    }

}
