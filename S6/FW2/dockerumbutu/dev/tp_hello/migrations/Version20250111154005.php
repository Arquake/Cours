<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250111154005 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cours ADD COLUMN ects DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE cours ADD COLUMN heure_tp DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE cours ADD COLUMN heure_td DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE cours ADD COLUMN heure_cm DOUBLE PRECISION NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TEMPORARY TABLE __temp__cours AS SELECT id, semestre, name, description FROM cours');
        $this->addSql('DROP TABLE cours');
        $this->addSql('CREATE TABLE cours (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, semestre INTEGER NOT NULL, name VARCHAR(255) NOT NULL, description CLOB NOT NULL)');
        $this->addSql('INSERT INTO cours (id, semestre, name, description) SELECT id, semestre, name, description FROM __temp__cours');
        $this->addSql('DROP TABLE __temp__cours');
    }
}
