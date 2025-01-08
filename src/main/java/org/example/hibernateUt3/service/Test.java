package org.example.hibernateUt3.service;

import org.example.hibernateUt3.dao.*;
import org.example.hibernateUt3.model.*;
import org.example.hibernateUt3.model.client.Client;
import org.example.hibernateUt3.model.client.CorporateClient;
import org.example.hibernateUt3.model.client.PrivateClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Test test = new Test();

        //---------------- REQUIRED DAOs ----------------//

        CostumeDao costumeDao = new CostumeDao();
        EntertainerDao entertainerDao = new EntertainerDao();
        HostDao hostDao = new HostDao();
        ClientDao clientDao = new ClientDao();
        EventDao eventDao = new EventDao();
        HiringDao hiringDao = new HiringDao();

        while (true) {
            System.out.println("\n---------------- Menu ----------------" );
            System.out.println("1. Create a Costume");
            System.out.println("2. Create an Entertainer");
            System.out.println("3. Create a Host");
            System.out.println("4. Create a Private Client");
            System.out.println("5. Create a Corporate Client");
            System.out.println("6. Create an Event");
            System.out.println("7. Assign an Entertainer to an Event");
            System.out.println("8. Create Hiring");
            System.out.println("9. Show all Events");
            System.out.println("10. Show all Entertainers");
            System.out.println("11. Show all Hosts");
            System.out.println("12. Show all Clients");
            System.out.println("13. Show all Entertainers in an Event");
            System.out.println("14. Show all Events of a Host");
            System.out.println("15. Show the two most expensive Costumes");
            System.out.println("16. Show clients who hired an event in a province");
            System.out.println("17. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    test.createCostume(costumeDao);
                    break;
                case 2:
                    test.createEntertainer(entertainerDao,costumeDao);
                    break;
                case 3:
                    test.createHost(hostDao);
                    break;
                case 4:
                    test.createPrivClient(clientDao);
                    break;
                case 5:
                    test.createCorpClient(clientDao);
                    break;
                case 6:
                    test.createEvent(eventDao,hostDao);
                    break;
                case 7:
                    test.assignEnertainer(entertainerDao, eventDao);
                    break;
                case 8:
                    test.createHiring(hiringDao, clientDao, eventDao);
                    break;
                case 9:
                    test.getAllEvents(eventDao);
                    break;
                case 10:
                    test.getAllEntertainers(entertainerDao);
                    break;
                case 11:
                    test.getAllHosts(hostDao);
                    break;
                case 12:
                    test.getAllClients(clientDao);
                    break;
                case 13:
                    test.getEventEntertainers(eventDao);
                    break;
                case 14:
                    test.getHostEvents(hostDao);
                    break;
                case 15:
                    test.getTwoMostExpensiveCustomes(costumeDao);
                    break;
                case 16:
                    test.getEventCityClients(hiringDao);
                    break;
                case 17:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //---------------- Methods for Creating Basic Entities ----------------//

    // Costume creation method
    private void createCostume(CostumeDao costumeDao) {
        try {
            Costume costume = new Costume();

            System.out.print("Enter the character: ");
            String character = scanner.nextLine();
            costume.setCharacter(character);

            System.out.print("Enter the price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            costume.setPrice(price);

            Costume costumeEntity = costumeDao.create(costume);
            log.info(costumeEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create a costume.");
        }
    }

    // Entertainer creation method
    private void createEntertainer(EntertainerDao entertainerDao, CostumeDao costumeDao) {

        try{
            Entertainer entertainer = new Entertainer();

            System.out.print("Enter the National ID: ");
            String nationalId = scanner.nextLine();
            entertainer.setNationalId(nationalId);

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            entertainer.setName(name);

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();
            entertainer.setSurname(surname);

            System.out.println("Enter the costume character: ");
            String costumeCharacter=scanner.nextLine();
            Costume costume=costumeDao.getByCharacter(costumeCharacter);
            entertainer.setCostume(costume);

            Entertainer entertainerEntity = entertainerDao.create(entertainer);
            log.info(entertainerEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create an entertainer");
        }
    }

    // Host creation method
    private void createHost(HostDao hostDao) {

        try {
            Host host = new Host();

            System.out.print("Enter the National ID: ");
            String nationalId = scanner.nextLine();
            host.setNationalId(nationalId);

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            host.setName(name);

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();
            host.setSurname(surname);

            System.out.print("Enter the year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            host.setYear(year);

            Host hostEntity = hostDao.create(host);
            log.info(hostEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create a host");
        }
    }

    // PrivateClient creation method
    private void createPrivClient(ClientDao clientDao) {

        try {
            PrivateClient privateClient = new PrivateClient();

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            privateClient.setName(name);

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();
            privateClient.setSurname(surname);

            System.out.print("Enter the National ID: ");
            String nationalId = scanner.nextLine();
            privateClient.setNationalId(nationalId);

            System.out.print("Enter the province: ");
            String province = scanner.nextLine();
            privateClient.setProvince(province);

            System.out.print("Enter the municipality: ");
            String municipality = scanner.nextLine();
            privateClient.setMunicipality(municipality);

            System.out.print("Enter the street: ");
            String street = scanner.nextLine();
            privateClient.setStreet(street);

            PrivateClient privClientEntity = (PrivateClient) clientDao.create(privateClient);
            log.info(privClientEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create a private client");
        }
    }

    // CorporateClient creation method
    private void createCorpClient(ClientDao clientDao) {

        try {
            CorporateClient corporateClient = new CorporateClient();

            System.out.print("Enter the company name: ");
            String name = scanner.nextLine();
            corporateClient.setName(name);

            System.out.print("Enter the contact email: ");
            String contact = scanner.nextLine();
            corporateClient.setContact(contact);

            System.out.print("Enter the VAT: ");
            String vat = scanner.nextLine();
            corporateClient.setVAT(vat);

            System.out.print("Enter the province: ");
            String province = scanner.nextLine();
            corporateClient.setProvince(province);

            System.out.print("Enter the municipality: ");
            String municipality = scanner.nextLine();
            corporateClient.setMunicipality(municipality);

            System.out.print("Enter the street: ");
            String street = scanner.nextLine();
            corporateClient.setStreet(street);

            CorporateClient corpClientEntity = (CorporateClient) clientDao.create(corporateClient);
            log.info(corpClientEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create a corporate client");
        }
    }

    // Event creation method
    private void createEvent(EventDao eventDao, HostDao hostDao) {
        try{
        Event event = new Event();

        System.out.print("Enter the event name: ");
        String name = scanner.nextLine();
        event.setName(name);

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month: ");
        int month = scanner.nextInt();
        System.out.print("Enter the day: ");
        int day = scanner.nextInt();
        System.out.print("Enter the hour: ");
        int hour = scanner.nextInt();
        System.out.print("Enter the minute: ");
        int minute = scanner.nextInt();scanner.nextLine();
        event.setTimetable(LocalDateTime.of(year, month, day, hour, minute));

        System.out.print("Enter the event description: ");
        String description = scanner.nextLine();
        event.setDescription(description);

        System.out.print("Enter the event price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        event.setPrice(price);

        System.out.print("Enter the event Host nationalID:  ");
        String hostNationalId= scanner.nextLine();
        Host host = hostDao.getByNationalId(hostNationalId);
        event.setEventHost(host);

        Event eventEntity = eventDao.create(event);
        log.info(eventEntity.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to create an event");
        }
    }


    //---------------- Methods for Associating Entities ----------------//

    // Assign an Entertainer to an Event
    public void assignEnertainer(EntertainerDao entertainerDao, EventDao eventDao){

        try {
            System.out.print("Enter the Entertainer National ID for Event: ");
            String entertainerNationalId = scanner.nextLine();
            System.out.print("Enter the Event ID: ");
            Long eventId = scanner.nextLong();
            Event event = eventDao.getById(eventId);
            Entertainer entertToAssign = entertainerDao.getByNationalId(entertainerNationalId);

            event.addEntertainer(entertToAssign);
            eventDao.update(event);
            System.out.println("Event entertainer :" + event.getEventEntertainers());
        }catch (Exception e) {
            log.error("An error has ocurred while trying to assign an entertainer to an event");
        }
    }

    // Create a hiring involving an Event and a Client
    private void createHiring(HiringDao hiringDao, ClientDao clientDao, EventDao eventDao) {
        try {
            Hiring hiring = new Hiring();
            hiringDao.create(hiring);

            System.out.print("Enter the Client ID: ");
            Long clientId = scanner.nextLong();
            System.out.print("Enter the Event ID for Client: ");
            Long eventId = scanner.nextLong();
            System.out.print("Enter the Event City: ");
            String eventCity = scanner.next();

            Client client = clientDao.getById(clientId);
            Event event = eventDao.getById(eventId);

            hiring.setClient(client);
            hiring.setEvent(event);
            hiring.setEventCity(eventCity);

            hiringDao.update(hiring);
        }catch (Exception e) {
            log.error("An error has ocurred while trying to create a hiring");
        }
    }


    //---------------- Methods for entity queries ----------------//

    // Display all Events
    private void getAllEvents(EventDao eventDao){
        try {
            List<Event> eventList = eventDao.getAll();
            log.info(eventList.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to return all events from database.");
        }
    }

    // Display all Entertainers
    private void getAllEntertainers(EntertainerDao entertainerDao){
        try {
            List<Entertainer> entertainerList = entertainerDao.getAll();
            log.info(entertainerList.toString());
        }catch (Exception e) {
            log.error("An error has ocurred while trying to return all entertainers from database.");
        }
    }

    // Display all Hosts
    private void getAllHosts(HostDao hostDao){
        try {
            List<Host> hostList = hostDao.getAll();
            log.info(hostList.toString());
        }catch (Exception e) {
            log.error("An error has ocurred while trying to return all hosts from database.");
        }
    }

    // Display all Clients
    private void getAllClients(ClientDao clientDao){
        try {
            List<Client> clientList = clientDao.getAll();
            log.info(clientList.toString());
        }catch (Exception e) {
        log.error("An error has ocurred while trying to return all clients from database.");
    }
    }

    // Display all Entertainers of an Event
    private void getEventEntertainers(EventDao eventDao){
        try{
            System.out.println("Enter the Event name: ");
            String eventName = scanner.nextLine();
            Set<Entertainer> entertainers = eventDao.getEventEntertainers(eventName);
            log.info(entertainers.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to return all event entertainers from database.");
        }
    }

    // Display all Events of a Host
    private void getHostEvents(HostDao hostDao){
        try{
            System.out.println("Enter the Host national ID: ");
            String hostName = scanner.nextLine();
            Set<Event> events = hostDao.getHostEventsById(hostName);
            log.info(events.toString());
        } catch (Exception e) {
            log.error("An error has ocurred while trying to return all host events from database.");
        }
    }

    // Display the two most expensive Costumes
    private void getTwoMostExpensiveCustomes(CostumeDao costumeDao){
        try {
            List<Costume> costumes = costumeDao.getTwoMostExpesiveCostumes();
            Costume costume1 = costumes.get(0);
            Costume costume2 = costumes.get(1);
            log.info(costume1.toString() + "\n" + costume2.toString());
        }catch (Exception e) {
            log.error("An error has ocurred while trying to return the two most expensive customes from database.");
        }
    }

    // Display all Clients who hired an event in a specific province
    private void getEventCityClients(HiringDao hiringDao){
        try {
            System.out.println("Enter the province name: ");
            String provinceName = scanner.nextLine();
            List<Client> clientList = hiringDao.getByProvince(provinceName);
            log.info(clientList.toString());
        }catch (Exception e) {
            log.error("An error has ocurred while trying to return all clients by event city from database.");
        }
    }
}
