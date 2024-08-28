package com.elearn.app;

import com.elearn.app.config.AppConstants;
import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;
import com.elearn.app.repositories.RoleRepo;
import com.elearn.app.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class StartLearnBackApplication implements CommandLineRunner {



    public static void main(String[] args)
    {
       SpringApplication.run(StartLearnBackApplication.class, args);
    }


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;



    @Override
    public void run(String... args) throws Exception {





        Role role1 = new Role();
        role1.setRoleName(AppConstants.ROLE_ADMIN);
        role1.setRoleId(UUID.randomUUID().toString());


        Role role2 = new Role();
        role2.setRoleName(AppConstants.ROLE_GUST);
        role2.setRoleId(UUID.randomUUID().toString());


        //creating admin role if not exists
        roleRepo.findByRoleName(AppConstants.ROLE_ADMIN).ifPresentOrElse(
                role -> {
                    System.out.println(role.getRoleName() + " already in database");
                    role1.setRoleId(role.getRoleId());
                }
                ,
                () -> {
                    roleRepo.save(role1);
                }
        );

        //creating admin role if not exists
        roleRepo.findByRoleName(AppConstants.ROLE_GUST).ifPresentOrElse(
                role -> {
                    role2.setRoleId(role.getRoleId());
                    System.out.println(role.getRoleName() + " already in database");
                }
                ,
                () -> {
                    roleRepo.save(role2);
                }
        );


        User user = new User();

        user.setUserId(UUID.randomUUID().toString());
        user.setName("Dheeraj");
        user.setEmail("abc@gmail.com");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setActive(true);
        user.setCreateAt(new Date());
        user.setEmailVarified(true);
        user.setAbout("this user is very nice user");

        user.assignRole(role1);
        user.assignRole(role2);

        userRepo.findByEmail("abc@gmail.com").ifPresentOrElse(user1 -> {
            System.out.println(user.getEmail());
        }, () -> {
            userRepo.save(user);
            System.out.println("user created");
        });

        User user1 = new User();

        user1.setUserId(UUID.randomUUID().toString());
        user1.setName("Abhishek");
        user1.setEmail("abhishek@gmail.com");
        user1.setPassword(passwordEncoder.encode("abc"));
        user1.setActive(true);
        user1.setCreateAt(new Date());
        user1.setEmailVarified(true);
        user1.setAbout("This is CEO and Managing Direct of Nasa");
        user1.assignRole(role2);

        userRepo.findByEmail("abhishek@gmail.com").ifPresentOrElse(user2 -> {
            System.out.println("abhishek is there in database");
        }, () -> {
            userRepo.save(user1);
            System.out.println("abhishek created");
        });


        User user2 = new User();

        user2.setUserId(UUID.randomUUID().toString());
        user2.setName("Ayush");
        user2.setEmail("ayush@gmail.com");
        user2.setPassword(passwordEncoder.encode("abc"));
        user2.setActive(true);
        user2.setCreateAt(new Date());
        user2.setEmailVarified(true);
        user2.setAbout("This is CEO and Managing Direct of Nasa");
        user2.assignRole(role1);

        userRepo.findByEmail("ayush@gmail.com").ifPresentOrElse(user3 -> {
            System.out.println("ayush is there in database");
        }, () -> {
            userRepo.save(user2);
            System.out.println("ayush created");
        });

        /*
        User user3 = new User();

        user3.setUserId(UUID.randomUUID().toString());
        user3.setName("Arun");
        user3.setEmail("arun@gmail.com");
        user3.setPassword(passwordEncoder.encode("abc"));
        user3.setActive(true);
        user3.setCreateAt(new Date());
        user3.setEmailVarified(true);
        user3.setAbout("This is CEO and Managing Direct of Nasa");
        user3.assignRole(role1);
        System.out.println(role1.getRoleId()+": "+role1.getRoleName());

        userRepo.findByEmail("arun@gmail.com").ifPresentOrElse(user4 -> {
            System.out.println("arun is there in database");
        }, () -> {
            System.out.println(user3.getUserId());
            userRepo.save(user3);
            System.out.println("arun created");
        });


*/


    }
}
