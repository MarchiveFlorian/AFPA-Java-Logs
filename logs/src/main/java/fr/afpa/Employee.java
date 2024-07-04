package fr.afpa;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Objectif :
L’objectif de ce TP est de concevoir un programme en console basé sur une approche objet et
permettant de gérer (vraiment très basiquement) des salariés d’une entreprise.

Vous allez écrire une classe représentant les salariés d'une entreprise.
Vous pourrez vous aider du PDF disponible sur pour obtenir des informations sur la façon de faire.
*/

class Employee {
	private static final Logger logger = LogManager.getLogger(Employee.class);
	/**
	 * Matricule de l'employé
	 */
	private String registrationNumber;

	// TODO compléter les attributs comme présenté dans le PDF
	
	private String lastName;
	private String firstName;
	private double salary;
	private final int socialRate = 30;
	private LocalDate birthDay;
	
	// TODO compléter le constructeur de la classe
	public Employee(String registrationNumber, String lastName, String firstName, double salary, String birthDay) {
		this.registrationNumber = registrationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.salary = salary;
		this.birthDay = LocalDate.parse(birthDay);
	}
	
	// TODO implémenter les setters et getters de la classe (permet d'accéder aux
	// attributs privés)
	
	// Getters
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public int getSocialRate() {
		return socialRate;
	}
	
	public LocalDate getBirthDay(){
		return birthDay;
	}
	
	// Setters
	public void setRegistrationNumber(String registrationNumber) {
		if (!checkRegistrationNumber(registrationNumber)) {
			IllegalArgumentException e = new IllegalArgumentException("Le matricule n'est pas correctement formate.");
            logger.error(e.getMessage(), e);
            throw e;
		}
		this.registrationNumber = registrationNumber;
	}
	
	public void setLastName(String lastName) {
		if (!checkName(lastName)) {
			IllegalArgumentException e = new IllegalArgumentException("Le nom ne doit pas etre vide et ne doit pas contenir de chiffres.");
            logger.error(e.getMessage(), e);
            throw e;
		}
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		if (!checkName(firstName)) {
			IllegalArgumentException e = new IllegalArgumentException("Le prénom ne doit pas etre vide et ne doit pas contenir de chiffres.");
            logger.error(e.getMessage(), e);
            throw e;
		}
		this.firstName = firstName;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setBirthDay(String birthDay) throws Exception {
		if (!checkDateFormat(birthDay)) {
			Exception e = new Exception("Le format de la date est incorrect. Utilisez le format AAAA-MM-JJ.");
            logger.error(e.getMessage(), e);
            throw e;
		}
		this.birthDay = LocalDate.parse(birthDay);
	}

	// TODO implémenter la méthode "toString()" qui renvoie une chaîne de caractère
	// qui représente un objet de la classe employé
	// plus d'information sur la méthode "toString()" ->
	// https://codegym.cc/fr/groups/posts/fr.986.mthode-java-tostring

	// Méthode pour vérifier le format du matricule
	private boolean checkRegistrationNumber(String registrationNumber) {
		return registrationNumber.matches("\\d{2}[A-Za-z]{3}\\d{2}");
	}

	// Méthode pour vérifier le format du nom et du prénom
	private boolean checkName(String name) {
		return name != null && !name.isEmpty() && name.matches("^[A-Za-z\\s]+$");
	}

	// Méthode pour vérifier le format de la date
	private boolean checkDateFormat(String date) {
		return date.matches("\\d{4}-\\d{2}-\\d{2}");
	}
	
	public double toNetSalary(){
		return this.salary - this.salary * (this.socialRate/100.0);
	}
	
	public long daysBeforeBirthDay(){
		LocalDate currentDate = LocalDate.now();
		LocalDate nextBirthDay = this.birthDay.withYear(currentDate.getYear()); //.withYear permet de set l'année de birthyear sur l'année actuelle -> prochain anniversaire

		//Si l'anniversaire de cette année est déjà passé, on prend l'année prochaine
		if (nextBirthDay.isBefore(currentDate) || nextBirthDay.isEqual(currentDate)){
			nextBirthDay = nextBirthDay.plusYears(1); //.plusYears rajoute une valeur à l'année, pareil avec .plusMonths ou .plusDays 
		}

		return ChronoUnit.DAYS.between(currentDate, nextBirthDay);
	}

	@Override
	public String toString() {
		return "Employee [registrationNumber=" + registrationNumber + 
						"\n lastName=" + lastName + 
						"\n firstName=" + firstName + 
						"\n salary=" + salary +  
						"\n netSalary=" + toNetSalary() + 
						"\n socialRate=" + socialRate + 
						"\n birthDay=" + birthDay + 
						"\n nextBirthDay in " + daysBeforeBirthDay() + " days " + "]";
	}
}
