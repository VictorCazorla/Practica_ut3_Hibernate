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

        //---------------- REQUIRED DAOS ----------------//

        CostumeDao costumeDao = new CostumeDao();
        EntertainerDao entertainerDao = new EntertainerDao();
        HostDao hostDao = new HostDao();
        ClientDao clientDao = new ClientDao();
        EventDao eventDao = new EventDao();
        HiringDao hiringDao = new HiringDao();

        while (true) {
            System.out.println("\n---------------- MENU ----------------");
            System.out.println("1. Create a Costume");
            System.out.println("2. Create an Entertainer");
            System.out.println("3. Create a Host");
            System.out.println("4. Create a Private Client");
            System.out.println("5. Create a Corporate Client");
            System.out.println("6. Create an Event");
            System.out.println("7. Assign a Costume to an Entertainer");
            System.out.println("8. Assign an Entertainer to an Event");
            System.out.println("9. Assign a Host to an Event");
            System.out.println("10. Assign an Event to a Client");
            System.out.println("11. Show all Events");
            System.out.println("12. Show all Entertainers");
            System.out.println("13. Show all Hosts");
            System.out.println("14. Show all Clients");
            System.out.println("15. Show all Entertainers in an Event");
            System.out.println("16. Show all Events of a Host");
            System.out.println("17. Show the two most expensive Costumes");
            System.out.println("18. Show clients who hired an event in a province");
            System.out.println("19. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    test.createCostume(costumeDao);
                    break;
                case 2:
                    test.createEntertainer(entertainerDao);
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
                    test.createEvent(eventDao);
                    break;
                case 7:
                    System.out.print("Enter the Entertainer ID: ");
                    Long entertainerId = scanner.nextLong();
                    System.out.print("Enter the Costume ID: ");
                    Long costumeId = scanner.nextLong();
                    test.assignCostume(entertainerDao, costumeDao, entertainerId, costumeId);
                    break;
                case 8:
                    System.out.print("Enter the Entertainer ID for Event: ");
                    Long entertainerId2 = scanner.nextLong();
                    System.out.print("Enter the Event ID: ");
                    Long eventId = scanner.nextLong();
                    test.assignEnertainer(entertainerDao, eventDao, entertainerId2, eventId);
                    break;
                case 9:
                    System.out.print("Enter the Host ID: ");
                    Long hostId = scanner.nextLong();
                    System.out.print("Enter the Event ID for Host: ");
                    Long eventId2 = scanner.nextLong();
                    test.assignHost(hostDao, eventDao, hostId, eventId2);
                    break;
                case 10:
                    System.out.print("Enter the Client ID: ");
                    Long clientId = scanner.nextLong();
                    System.out.print("Enter the Event ID for Client: ");
                    Long eventId3 = scanner.nextLong();
                    System.out.print("Enter the Event City: ");
                    String eventCity = scanner.next();
                    test.createHiring(hiringDao, clientDao, eventDao, clientId, eventId3, eventCity);
                    break;
                case 11:
                    test.getAllEvents(eventDao);
                    break;
                case 12:
                    test.getAllEntertainers(entertainerDao);
                    break;
                case 13:
                    test.getAllHosts(hostDao);
                    break;
                case 14:
                    test.getAllClients(clientDao);
                    break;
                case 15:
                    System.out.println("Enter the Event name: ");
                    String eventName = scanner.nextLine();
                    test.getEventEntertainers(eventName, eventDao);
                    break;
                case 16:
                    System.out.println("Enter the Host national ID: ");
                    String hostName = scanner.nextLine();
                    test.getHostEvents(hostName, hostDao);
                    break;
                case 17:
                    test.getTwoMostExpensiveCustomes(costumeDao);
                    break;
                case 18:
                    System.out.println("Enter the province name: ");
                    String provinceName = scanner.nextLine();
                    test.getEventProvinceClients(provinceName, hiringDao);
                    break;
                case 19:
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

    }

    // Entertainer creation method
    private void createEntertainer(EntertainerDao entertainerDao) {

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

        Entertainer entertainerEntity = entertainerDao.create(entertainer);
        log.info(entertainerEntity.toString());

    }

    // Host creation method
    private void createHost(HostDao hostDao) {

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
        int year = scanner.nextInt();scanner.nextLine();
        host.setYear(year);

        Host hostEntity = hostDao.create(host);
        log.info(hostEntity.toString());

    }

    // PrivateClient creation method
    private void createPrivClient(ClientDao clientDao) {

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

    }

    // CorporateClient creation method
    private void createCorpClient(ClientDao clientDao) {

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

    }

    // Event creation method
    private void createEvent(EventDao eventDao) {
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

        Event eventEntity = eventDao.create(event);
        log.info(eventEntity.toString());

    }


    //---------------- Methods for Associating Entities ----------------//

    // Assign a Costume to an Entertainer
    public void assignCostume(EntertainerDao entertainerDao, CostumeDao costumeDao, Long entertainerId, Long costumeId) {
        Entertainer entertainer = entertainerDao.getById(entertainerId);
        Costume costumeToAssign = costumeDao.getById(costumeId);

        entertainer.setCostume(costumeToAssign);
        entertainerDao.update(entertainer);
    }

    // Assign an Entertainer to an Event
    public void assignEnertainer(EntertainerDao entertainerDao, EventDao eventDao, Long entertainerId, Long eventId){
        Entertainer entertToAssign= entertainerDao.getById(entertainerId);
        Event event=eventDao.getById(eventId);

        event.addEntertainer(entertToAssign);
        eventDao.update(event);
    }

    // Assign a Host to an Event
    public void assignHost(HostDao hostDao, EventDao eventDao, Long hostId, Long eventId){
        Host hostToAssign=hostDao.getById(hostId);
        Event event =eventDao.getById(eventId);

        event.setEventHost(hostToAssign);
        eventDao.update(event);
    }

    // Create a hiring involving an Event and a Client
    private void createHiring(HiringDao hiringDao, ClientDao clientDao, EventDao eventDao, Long clientId, Long eventId, String eventCity) {
        Hiring hiring = new Hiring();
        hiringDao.create(hiring);

        Client client = clientDao.getById(clientId);
        Event event = eventDao.getById(eventId);

        hiring.setClient(client);
        hiring.setEvent(event);
        hiring.setEventCity(eventCity);

        hiringDao.update(hiring);
    }


    //---------------- Methods for entity queries ----------------//

    // Display all Events
    private void getAllEvents(EventDao eventDao){
        List<Event> eventList = eventDao.getAll();
        log.info(eventList.toString());
    }

    // Display all Entertainers
    private void getAllEntertainers(EntertainerDao entertainerDao){
        List<Entertainer> entertainerList = entertainerDao.getAll();
        log.info(entertainerList.toString());
    }

    // Display all Hosts
    private void getAllHosts(HostDao hostDao){
        List<Host> hostList = hostDao.getAll();
        log.info(hostList.toString());
    }

    // Display all Clients
    private void getAllClients(ClientDao clientDao){
        List<Client> clientList = clientDao.getAll();
        log.info(clientList.toString());
    }

    // Display all Entertainers of an Event
    private void getEventEntertainers(String eventName, EventDao eventDao){
        Set<Entertainer> entertainers = eventDao.getEventEntertainers(eventName);
        log.info(entertainers.toString());
    }

    // Display all Events of a Host
    private void getHostEvents(String hostName, HostDao hostDao){
        Set<Event> events = hostDao.getHostEventsById(hostName);
        log.info(events.toString());
    }

    // Display the two most expensive Costumes
    private void getTwoMostExpensiveCustomes(CostumeDao costumeDao){
        List<Costume> costumes = costumeDao.getTwoMostExpesiveCostumes();
        Costume costume1 = costumes.get(0);
        Costume costume2 = costumes.get(1);
        log.info(costume1.toString() + "\n" + costume2.toString());
    }

    // Display all Clients who hired an event in a specific province
    private void getEventProvinceClients(String provinceName, HiringDao hiringDao){
        List<Client> clientList = hiringDao.getByProvince(provinceName);
        log.info(clientList.toString());
    }
}
