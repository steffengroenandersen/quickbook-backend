package com.quickbook.quickbookbackend.config;

import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.repository.GuestRepository;
import com.quickbook.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeveloperData implements ApplicationRunner {

    GuestRepository guestRepository;

    public DeveloperData(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("RUNNING PROGRAM......................");
        System.out.println("INSERTING DEVELOPER DATA......................");

        // Add admins
        Guest admin1 = new Guest("steffen", "steffen@localhost.com", "pass", "Steffen", "Andersen");
        admin1.addRole(Role.ADMIN);
        guestRepository.save(admin1);

        Guest admin2 = new Guest("maria", "maria@localhost.com", "pass", "Maria", "Andersen");
        admin2.addRole(Role.ADMIN);
        guestRepository.save(admin2);

        Guest admin3 = new Guest("louise", "louise@localhost.com", "pass", "Louise", "Andersen");
        admin3.addRole(Role.ADMIN);
        guestRepository.save(admin3);

        Guest admin4 = new Guest("mikkel", "mikkel@localhost.com", "pass", "Mikkel", "Andersen");
        admin4.addRole(Role.ADMIN);
        guestRepository.save(admin4);

        Guest admin5 = new Guest("anders", "anders@localhost.com", "pass", "Anders", "Andersen");
        admin5.addRole(Role.ADMIN);
        guestRepository.save(admin5);

        // Add Guests

        Guest guest1 = new Guest("peter", "peter@localhost.com", "pass", "Peter", "Andersen");
        guest1.addRole(Role.USER);
        guestRepository.save(guest1);


        Guest guest2 = new Guest("marianna", "marianna@localhost.com", "pass", "Marianna", "Garcia");
        guest2.addRole(Role.USER);
        guestRepository.save(guest2);

        Guest guest3 = new Guest("john", "john@localhost.com", "pass", "John", "Johnson");
        guest3.addRole(Role.USER);
        guestRepository.save(guest3);

        Guest guest4 = new Guest("susan", "susan@localhost.com", "pass", "Susan", "Williams");
        guest4.addRole(Role.USER);
        guestRepository.save(guest4);

        Guest guest5 = new Guest("alex", "alex@localhost.com", "pass", "Alex", "Davis");
        guest5.addRole(Role.USER);
        guestRepository.save(guest5);

        Guest guest6 = new Guest("emma", "emma@localhost.com", "pass", "Emma", "Clark");
        guest6.addRole(Role.USER);
        guestRepository.save(guest6);

        Guest guest7 = new Guest("william", "william@localhost.com", "pass", "William", "Moore");
        guest7.addRole(Role.USER);
        guestRepository.save(guest7);

        Guest guest8 = new Guest("olivia", "olivia@localhost.com", "pass", "Olivia", "Smith");
        guest8.addRole(Role.USER);
        guestRepository.save(guest8);

        Guest guest9 = new Guest("james", "james@localhost.com", "pass", "James", "Martin");
        guest9.addRole(Role.USER);
        guestRepository.save(guest9);

        Guest guest10 = new Guest("ella", "ella@localhost.com", "pass", "Ella", "White");
        guest10.addRole(Role.USER);
        guestRepository.save(guest10);
    }
}
