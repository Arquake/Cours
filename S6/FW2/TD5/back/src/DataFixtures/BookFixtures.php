<?php

namespace App\DataFixtures;

use App\Entity\Book;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Faker\Factory;

class BookFixtures extends Fixture
{
    public function load(ObjectManager $manager): void
    {
        $faker = Factory::create();

        for ($i = 0; $i < 20; $i++) {
            $book = new Book();
            $book->setTitle($faker->sentence(3))
                ->setPublisher($faker->company)
                ->setYear($faker->numberBetween(1800, 2025))
                ->setBackcover($faker->paragraph(5))
                ->setIsbn($faker->isbn13());

            $manager->persist($book);
        }

        $manager->flush();
    }
}
