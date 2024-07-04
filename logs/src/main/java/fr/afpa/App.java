package fr.afpa;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        System.out.println("\n----- Exercice de programmation objet - classe \"Employee\" -----");

        Employee employee1 = new Employee("11ABC22", "Dupont", "Martin", 2500, "1995-03-28");
        Employee employee2 = new Employee("22ABC33", "Eboue", "Fabrice", 10000, "1995-03-28");
        Employee employee3 = new Employee("33ABC44", "Dujardin", "Jean", 15000, "1995-03-28");
        Employee employee4 = new Employee("44ABC55", "Random", "Guy", 9999, "1999-09-29");


        employee1.setRegistrationNumber("999999");

        ArrayList<Employee> listEmployees = new ArrayList<>();
        listEmployees.add(employee1);
        listEmployees.add(employee2);
        listEmployees.add(employee3);
        listEmployees.add(employee4);

        for (Employee employee : listEmployees) {
            logger.info(employee);
        }

    }
}
