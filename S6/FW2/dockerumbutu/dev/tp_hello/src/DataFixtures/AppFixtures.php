<?php

namespace App\DataFixtures;

use App\Entity\Cours;
use App\Entity\Semestre;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;



class AppFixtures extends Fixture
{

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

        for ($i = 0; $i < 10; $i++) {
            $cours = (new Cours())
            ->setDescription($faker->paragraph(3))
            ->setName($faker->sentence(3))
            ->setEcts(random_int(2,5))
            ->setHeureCm($faker->numberBetween(10,16))
            ->setHeureTd($faker->numberBetween(14,18))
            ->setHeureTp($faker->numberBetween(8,16))
            ->setSemestre($semestreArray[$faker->numberBetween(0,9)])
            ->setDateCreation($faker->dateTimeBetween('-54 week', '-24 week'))
            ->setDateModification($faker->dateTimeBetween('-10 week', '0 week'));
            $manager->persist($cours);
        }

        $manager->flush();
    }
}
