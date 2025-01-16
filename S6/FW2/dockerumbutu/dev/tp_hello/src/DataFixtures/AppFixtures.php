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
        $semestreArray = [];
        for ($i = 1; $i < 11; $i++) {
            $semestre = (new Semestre())
            ->setSemestre($i)
            ->setFormation("formation {$i}");
            $semestreArray[] = $semestre;
        }

        for ($i = 0; $i < 10; $i++) {
            $cours = (new Cours())
                ->setDescription("desc cours{$i}")
                ->setName("Cours{$i}")
                ->setSemestre($i)
            ->setEcts(random_int(2,5))
            ->setHeureCm(random_int(10,16))
            ->setHeureTd(random_int(14,18))
            ->setHeureTp(random_int(8,16))
            ->setSemestre($semestreArray[random_int(1,10)]);
            $manager->persist($cours);
        }

        $manager->flush();
    }
}
