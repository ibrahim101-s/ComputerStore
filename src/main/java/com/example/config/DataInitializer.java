package com.example.config;

import com.example.entity.ComputerEntity;
import com.example.entity.UserEntity;
import com.example.repository.ComputerRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ComputerRepository computerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByUsername("u1")) {
            UserEntity defaultUser = UserEntity.builder()
                    .name("İstifadəçi 1")
                    .username("u1")
                    .password(passwordEncoder.encode("p1"))
                    .phone("050-111-2233")
                    .build();
            userRepository.save(defaultUser);
        }


        if (computerRepository.count() == 0) {
            String defaultImg = "https://pngimg.com/uploads/laptop/laptop_PNG5929.png";

            List<ComputerEntity> initialComputers = List.of(
                    ComputerEntity.builder().brand("Acer").model("Nitro 5").price(1850.0).description("Intel i7, 16GB RAM, RTX 3060").imageUrl(defaultImg).ram("16GB").cpu("Intel i7").storage("512GB SSD").os("Windows 11").gpu("RTX 3060").username("admin").phone("050-111-2233").isNew("Bəli").quantity(5).build(),
                    ComputerEntity.builder().brand("HP").model("Victus 16").price(1650.0).description("AMD Ryzen 7, 16GB RAM, RTX 3050").imageUrl(defaultImg).ram("16GB").cpu("Ryzen 7").storage("512GB SSD").os("Windows 11").gpu("RTX 3050").username("admin").phone("050-111-2233").isNew("Bəli").quantity(8).build(),
                    ComputerEntity.builder().brand("Asus").model("TUF Gaming").price(2100.0).description("Intel i7 13-cü nəsil, RTX 4050").imageUrl(defaultImg).ram("16GB").cpu("Intel i7").storage("1TB SSD").os("FreeDOS").gpu("RTX 4050").username("admin").phone("050-111-2233").isNew("Bəli").quantity(4).build(),
                    ComputerEntity.builder().brand("Dell").model("G15 5530").price(1950.0).description("Intel i5, 16GB RAM, RTX 3050 Ti").imageUrl(defaultImg).ram("16GB").cpu("Intel i5").storage("512GB SSD").os("Windows 11").gpu("RTX 3050 Ti").username("admin").phone("050-111-2233").isNew("Bəli").quantity(3).build(),
                    ComputerEntity.builder().brand("Lenovo").model("Legion 5").price(2450.0).description("Ryzen 7, 32GB RAM, RTX 4060").imageUrl(defaultImg).ram("32GB").cpu("Ryzen 7").storage("1TB SSD").os("Windows 11").gpu("RTX 4060").username("admin").phone("050-111-2233").isNew("Xeyr").quantity(6).build(),
                    ComputerEntity.builder().brand("LG").model("Gram 17").price(2300.0).description("Intel i7, Ultrabook, 16GB RAM").imageUrl(defaultImg).ram("16GB").cpu("Intel i7").storage("1TB SSD").os("Windows 11").gpu("Iris Xe").username("admin").phone("050-111-2233").isNew("Bəli").quantity(2).build(),
                    ComputerEntity.builder().brand("Casper").model("Nirvana X600").price(1200.0).description("Intel i5, 8GB RAM, Intel UHD").imageUrl(defaultImg).ram("8GB").cpu("Intel i5").storage("256GB SSD").os("Windows 11").gpu("Intel UHD").username("admin").phone("050-111-2233").isNew("Bəli").quantity(10).build(),
                    ComputerEntity.builder().brand("Fujitsu").model("Lifebook U9311").price(1400.0).description("Intel i5, Biznes Noutbuk, 16GB").imageUrl(defaultImg).ram("16GB").cpu("Intel i5").storage("512GB SSD").os("Windows 10 Pro").gpu("Iris Xe").username("admin").phone("050-111-2233").isNew("Xeyr").quantity(3).build(),
                    ComputerEntity.builder().brand("Nexus").model("ProBook X").price(1100.0).description("Intel i3, 8GB RAM, Günlük istifadə").imageUrl(defaultImg).ram("8GB").cpu("Intel i3").storage("256GB SSD").os("Windows 11").gpu("Intel HD").username("admin").phone("050-111-2233").isNew("Bəli").quantity(7).build(),
                    ComputerEntity.builder().brand("Samsung").model("Galaxy Book3").price(2200.0).description("Intel i7 13-cü nəsil, AMOLED").imageUrl(defaultImg).ram("16GB").cpu("Intel i7").storage("512GB SSD").os("Windows 11").gpu("Iris Xe").username("admin").phone("050-111-2233").isNew("Bəli").quantity(5).build(),
                    ComputerEntity.builder().brand("Toshiba").model("Dynabook Portégé").price(1350.0).description("Intel i5, 16GB RAM, Yüngül").imageUrl(defaultImg).ram("16GB").cpu("Intel i5").storage("512GB SSD").os("Windows 10").gpu("Intel HD").username("admin").phone("050-111-2233").isNew("Bəli").quantity(4).build(),
                    ComputerEntity.builder().brand("Sony").model("VAIO SX14").price(2600.0).description("Intel i7, Carbon Fiber, Premium").imageUrl(defaultImg).ram("32GB").cpu("Intel i7").storage("1TB SSD").os("Windows 11 Pro").gpu("Iris Xe").username("admin").phone("050-111-2233").isNew("Bəli").quantity(2).build()
            );

            computerRepository.saveAll(initialComputers);
        }
    }
}