package com.quickbook.quickbookbackend.config;

import com.quickbook.quickbookbackend.entity.User;
import com.quickbook.quickbookbackend.repository.UserRepository;
import com.quickbook.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeveloperData implements ApplicationRunner {

    UserRepository userRepository;

    public DeveloperData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("RUNNING PROGRAM......................");
        System.out.println("INSERTING DEVELOPER DATA......................");

        // Add admins
        User admin1 = new User("steffen", "steffen@localhost.com", "pass", "Steffen", "Andersen");
        admin1.addRole(Role.ADMIN);
        userRepository.save(admin1);

        User admin2 = new User("maria", "maria@localhost.com", "pass", "Maria", "Andersen");
        admin2.addRole(Role.ADMIN);
        userRepository.save(admin2);

        User admin3 = new User("louise", "louise@localhost.com", "pass", "Louise", "Andersen");
        admin3.addRole(Role.ADMIN);
        userRepository.save(admin3);

        User admin4 = new User("mikkel", "mikkel@localhost.com", "pass", "Mikkel", "Andersen");
        admin4.addRole(Role.ADMIN);
        userRepository.save(admin4);

        User admin5 = new User("anders", "anders@localhost.com", "pass", "Anders", "Andersen");
        admin5.addRole(Role.ADMIN);
        userRepository.save(admin5);

        // Add users

        User user1 = new User("peter", "peter@localhost.com", "pass", "Peter", "Andersen");
        user1.addRole(Role.USER);
        userRepository.save(user1);


        User user2 = new User("marianna", "marianna@localhost.com", "pass", "Marianna", "Garcia");
        user2.addRole(Role.USER);
        userRepository.save(user2);

        User user3 = new User("john", "john@localhost.com", "pass", "John", "Johnson");
        user3.addRole(Role.USER);
        userRepository.save(user3);

        User user4 = new User("susan", "susan@localhost.com", "pass", "Susan", "Williams");
        user4.addRole(Role.USER);
        userRepository.save(user4);

        User user5 = new User("alex", "alex@localhost.com", "pass", "Alex", "Davis");
        user5.addRole(Role.USER);
        userRepository.save(user5);

        User user6 = new User("emma", "emma@localhost.com", "pass", "Emma", "Clark");
        user6.addRole(Role.USER);
        userRepository.save(user6);

        User user7 = new User("william", "william@localhost.com", "pass", "William", "Moore");
        user7.addRole(Role.USER);
        userRepository.save(user7);

        User user8 = new User("olivia", "olivia@localhost.com", "pass", "Olivia", "Smith");
        user8.addRole(Role.USER);
        userRepository.save(user8);

        User user9 = new User("james", "james@localhost.com", "pass", "James", "Martin");
        user9.addRole(Role.USER);
        userRepository.save(user9);

        User user10 = new User("ella", "ella@localhost.com", "pass", "Ella", "White");
        user10.addRole(Role.USER);
        userRepository.save(user10);
    }
}
