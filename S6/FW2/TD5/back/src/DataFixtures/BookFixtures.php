<?php

namespace App\DataFixtures;

use App\Entity\Book;
use App\Entity\Author;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Faker\Factory;

class BookFixtures extends Fixture
{
    public function load(ObjectManager $manager): void
    {
        $faker = Factory::create();

        $authors = [];

        for ($i=0; $i < 20; $i++) { 
            $author = new Author();
            $author->setFirstName($faker->name())
            ->setLastName($faker->name());
            $manager->persist($author);
            $authors[] = $author;
        }

        for ($i = 0; $i < 60; $i++) {
            $book = new Book();
            $book->setTitle($faker->sentence(3))
                ->setPublisher($faker->company)
                ->setYear($faker->numberBetween(1800, 2025))
                ->setBackcover($faker->paragraph(5))
                ->setIsbn($faker->isbn13())
                ->addAuthor($authors[rand(0,count($authors)-1)]);

            $manager->persist($book);
        }

        $manager->flush();
    }
}
