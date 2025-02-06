<?php

namespace App\DataFixtures;

use App\Entity\Cours;
use App\Entity\Semestre;
use App\Entity\User;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;



class AppFixtures extends Fixture
{
    private UserPasswordHasherInterface $userPasswordHasher;

    public function __construct(UserPasswordHasherInterface $userPasswordHasher)
    {
        $this->userPasswordHasher = $userPasswordHasher;
    }

    public function load(ObjectManager $manager): void
    {
        $faker = \Faker\Factory::create('fr_FR');

        $semestreArray = [];
        for ($i = 1; $i < 11; $i++) {
            $semestre = (new Semestre())
            ->setSemestre($i)
            ->setFormation("formation {$i}");
            $semestreArray[] = $semestre;
            $manager->persist($semestre);
        }

        $userArray = [];
        $usedLastNames = []; // Store already used last names

        for ($i = 0; $i < 200; $i++) {
            do {
                $lastName = $faker->lastName();
            } while (in_array($lastName, $usedLastNames)); // Ensure uniqueness

            $user = (new User);
            $user
                ->setNom($lastName)
                ->setPrenom($faker->firstName())
                ->setPassword($this->userPasswordHasher->hashPassword($user, "password"));

            $usedLastNames[] = $lastName; // Mark last name as used
            $userArray[] = $user;
            $manager->persist($user);
        }

        for ($i = 0; $i < 120; $i++) {
            $cours = (new Cours())
            ->setDescription($faker->paragraph(3))
            ->setName($faker->sentence(3))
            ->setEcts(random_int(2,5))
            ->setHeureCm($faker->numberBetween(10,16))
            ->setHeureTd($faker->numberBetween(14,18))
            ->setHeureTp($faker->numberBetween(8,16))
            ->setSemestre($semestreArray[$faker->numberBetween(0,9)])
            ->setDateCreation($faker->dateTimeBetween('-54 week', '-24 week'))
            ->setDateModification($faker->dateTimeBetween('-10 week', '0 week'))
            ->setEnseignant($userArray[$faker->numberBetween(0,10)]);
            $manager->persist($cours);
        }

        $manager->flush();
    }
}
