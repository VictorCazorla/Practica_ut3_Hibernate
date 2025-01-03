package org.example.hibernateUt3.service;

import org.example.hibernateUt3.dao.*;
import org.example.hibernateUt3.model.*;
import org.example.hibernateUt3.model.client.Client;
import org.example.hibernateUt3.model.client.CorporateClient;
import org.example.hibernateUt3.model.client.PrivateClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);
        App app = new App();
        ClientDao clientDao = new ClientDao();
        CostumeDao costumeDao = new CostumeDao();
        EntertainerDao entertainerDao = new EntertainerDao();
        HostDao hostDao = new HostDao();
        EventDao eventDao = new EventDao();
        HiringDao hiringDao =new HiringDao();

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Create Costume");
            System.out.println("2. Create Entertainer");
            System.out.println("3. Create Host");
            System.out.println("4. Create Event");
            System.out.println("5. Create Client");
            System.out.println("6. Create Hiring");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline

            switch (choice) {
                case 1:
                    app.createCostume(costumeDao);
                    break;
                case 2:
                    app.createEntertainer(costumeDao, entertainerDao);
                    break;
                case 3:
                    app.createHost(hostDao);
                    break;
                case 4:
                    app.createEvent(eventDao, hostDao);
                    break;
                case 5:
                    app.createClient(clientDao);
                    break;
                case 6:
                    app.createHiring(clientDao,eventDao,hiringDao,hostDao);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }*/
    }

    /*public void createCostume(CostumeDao costumeDao) {
        Scanner scanner = new Scanner(System.in);
        Costume costume = new Costume();

        System.out.println("Enter the character name:");
        String character = scanner.nextLine();
        costume.setCharacter(character);

        System.out.println("Enter the costume price:");
        String priceStr = scanner.nextLine();
        double price = Double.parseDouble(priceStr);
        costume.setPrice(price);

        if (costumeDao.getByCharacter(costume.getCharacter()) == null) {
            costume = costumeDao.create(costume);
            System.out.println("Created Costume: " + costume);
        } else {
            System.out.println("The costume already exists");
        }
    }

    public void createEntertainer(CostumeDao costumeDao, EntertainerDao entertainerDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the national ID:");
        String nationalId = scanner.nextLine();

        // Verificar si el entertainer ya existe
        Entertainer existingEntertainer = entertainerDao.getByNationalId(nationalId);
        if (existingEntertainer != null) {
            System.out.println("The entertainer already exists: " + existingEntertainer);
            return;
        }

        System.out.println("Enter the entertainer name:");
        String name = scanner.nextLine();

        System.out.println("Enter the character name for the costume:");
        String character = scanner.nextLine();
        Costume costume = costumeDao.getByCharacter(character);
        if (costume == null) {
            System.out.println("Enter the costume price:");
            String priceStr = scanner.nextLine();
            double price = Double.parseDouble(priceStr);
            costume = new Costume(character, price);
            costume = costumeDao.create(costume);
        }

        Entertainer entertainer = new Entertainer(nationalId, name, costume);
        entertainer = entertainerDao.create(entertainer);
        System.out.println("Created Entertainer: " + entertainer);
    }

    public void createHost(HostDao hostDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the national ID:");
        String nationalId = scanner.nextLine();

        // Verificar si el host ya existe
        Host existingHost = hostDao.getByNationalId(nationalId);
        if (existingHost != null) {
            System.out.println("The host already exists: " + existingHost);
            return;
        }

        System.out.println("Enter the host name:");
        String name = scanner.nextLine();

        System.out.println("Enter the host surname:");
        String surname = scanner.nextLine();

        System.out.println("Enter the host year:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Host host = new Host(nationalId, name, surname, year);
        host = hostDao.create(host);
        System.out.println("Created Host: " + host);
    }

    public void createEvent(EventDao eventDao, HostDao hostDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the event name:");
        String name = scanner.nextLine();

        // Verificar si el evento ya existe
        Event existingEvent = eventDao.getByName(name);
        if (existingEvent != null) {
            System.out.println("The event already exists: " + existingEvent);
            return;
        }

        System.out.println("Enter the event year (e.g., 2023):");
        int year = scanner.nextInt();

        System.out.println("Enter the event month (1-12):");
        int month = scanner.nextInt();

        System.out.println("Enter the event day of month (1-31):");
        int dayOfMonth = scanner.nextInt();

        System.out.println("Enter the event hour (0-23):");
        int hour = scanner.nextInt();

        System.out.println("Enter the event minute (0-59):");
        int minute = scanner.nextInt();
        scanner.nextLine();

        LocalDateTime timetable = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Enter the event price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the event description:");
        String description = scanner.nextLine();

        // Asignar Host al evento
        System.out.println("Enter the host national ID:");
        String hostNationalId = scanner.nextLine();
        Host host = hostDao.getByNationalId(hostNationalId);
        if (host == null) {
            System.out.println("Host not found. Please create the host first.");
            return;
        }

        Event event = new Event(name, timetable, price, description, host);
        event = eventDao.create(event);
        System.out.println("Created Event: " + event);
    }

    public void createClient(ClientDao clientDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the type of client (1 for Corporate, 2 for Private):");
        int clientType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the province:");
        String province = scanner.nextLine();

        System.out.println("Enter the municipality:");
        String municipality = scanner.nextLine();

        System.out.println("Enter the street:");
        String street = scanner.nextLine();

        Client existingClient = null;

        if (clientType == 1) {
            System.out.println("Enter the company name:");
            String name = scanner.nextLine();

            System.out.println("Enter the company contact:");
            String contact = scanner.nextLine();

            System.out.println("Enter the company VAT number:");
            String VAT = scanner.nextLine();

            existingClient = clientDao.getByVAT(VAT);
            if (existingClient != null) {
                System.out.println("The client already exists: " + existingClient);
                return;
            }

            Client corporateClient = new CorporateClient(province, municipality, street, name, contact, VAT);
            corporateClient = clientDao.create(corporateClient);
            System.out.println("Created Corporate Client: " + corporateClient);

        } else if (clientType == 2) {
            System.out.println("Enter the client's first name:");
            String name = scanner.nextLine();

            System.out.println("Enter the client's surname:");
            String surname = scanner.nextLine();

            System.out.println("Enter the client's national ID:");
            String nationalId = scanner.nextLine();

            existingClient = clientDao.getByNationalId(nationalId);
            if (existingClient != null) {
                System.out.println("The client already exists: " + existingClient);
                return;
            }

            Client privateClient = new PrivateClient(province, municipality, street, name, surname, nationalId);
            privateClient = clientDao.create(privateClient);
            System.out.println("Created Private Client: " + privateClient);

        } else {
            System.out.println("Invalid client type selected.");
            return;
        }
    }

    public void createHiring(ClientDao clientDao, EventDao eventDao, HiringDao hiringDao, HostDao hostDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the client province:");
        String province = scanner.nextLine();

        System.out.println("Enter the client municipality:");
        String municipality = scanner.nextLine();

        System.out.println("Enter the client street:");
        String street = scanner.nextLine();

        System.out.println("Select the type of client (1 for Corporate, 2 for Private):");
        int clientType = scanner.nextInt();
        scanner.nextLine();

        Client client;
        if (clientType == 1) {
            System.out.println("Enter the company name:");
            String name = scanner.nextLine();

            System.out.println("Enter the company contact:");
            String contact = scanner.nextLine();

            System.out.println("Enter the company VAT number:");
            String VAT = scanner.nextLine();

            client = clientDao.getByVAT(VAT);
            if (client == null) {
                client = new CorporateClient(province, municipality, street, name, contact, VAT);
                client = clientDao.create(client);
            }
        } else if (clientType == 2) {
            System.out.println("Enter the client's first name:");
            String name = scanner.nextLine();

            System.out.println("Enter the client's surname:");
            String surname = scanner.nextLine();

            System.out.println("Enter the client's national ID:");
            String nationalId = scanner.nextLine();

            client = clientDao.getByNationalId(nationalId);
            if (client == null) {
                client = new PrivateClient(province, municipality, street, name, surname, nationalId);
                client = clientDao.create(client);
            }
        } else {
            System.out.println("Invalid client type selected.");
            return;
        }

        System.out.println("Enter the event name:");
        String eventName = scanner.nextLine();

        System.out.println("Enter the event year (e.g., 2023):");
        int year = scanner.nextInt();

        System.out.println("Enter the event month (1-12):");
        int month = scanner.nextInt();

        System.out.println("Enter the event day of month (1-31):");
        int dayOfMonth = scanner.nextInt();

        System.out.println("Enter the event hour (0-23):");
        int hour = scanner.nextInt();

        System.out.println("Enter the event minute (0-59):");
        int minute = scanner.nextInt();
        scanner.nextLine();

        LocalDateTime timetable = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Enter the event price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the event description:");
        String description = scanner.nextLine();

        Event event = eventDao.getByName(eventName);

        if (event == null) {

            System.out.println("Enter the host national ID:");
            String hostNationalId = scanner.nextLine();

            Host host = hostDao.getByNationalId(hostNationalId);
            if (host == null) {

                System.out.println("Enter the host name:");
                String name = scanner.nextLine();

                System.out.println("Enter the host surname:");
                String surname = scanner.nextLine();

                System.out.println("Enter the host year:");
                int hostyear = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                host = new Host(hostNationalId, name, surname, year);
                host = hostDao.create(host);
                System.out.println("Created Host: " + host);
            }

            event = new Event(eventName, timetable, price, description, host);
            event = eventDao.create(event);
        }

        System.out.println("Enter the event city:");
        String eventCity = scanner.nextLine();

        Hiring hiring = new Hiring(client, event, eventCity);
        hiring = hiringDao.create(hiring);
        System.out.println("Created Hiring: " + hiring);
    }*/
}


