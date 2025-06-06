<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250206090930 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE cours (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, semestre_id INTEGER NOT NULL, enseignant_id INTEGER NOT NULL, name VARCHAR(255) NOT NULL, description CLOB NOT NULL, ects DOUBLE PRECISION NOT NULL, heure_tp DOUBLE PRECISION NOT NULL, heure_td DOUBLE PRECISION NOT NULL, heure_cm DOUBLE PRECISION NOT NULL, date_creation DATETIME NOT NULL, date_modification DATETIME NOT NULL, CONSTRAINT FK_FDCA8C9C5577AFDB FOREIGN KEY (semestre_id) REFERENCES semestre (id) NOT DEFERRABLE INITIALLY IMMEDIATE, CONSTRAINT FK_FDCA8C9CE455FCC0 FOREIGN KEY (enseignant_id) REFERENCES user (id) NOT DEFERRABLE INITIALLY IMMEDIATE)');
        $this->addSql('CREATE INDEX IDX_FDCA8C9C5577AFDB ON cours (semestre_id)');
        $this->addSql('CREATE INDEX IDX_FDCA8C9CE455FCC0 ON cours (enseignant_id)');
        $this->addSql('CREATE TABLE semestre (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, formation VARCHAR(255) NOT NULL, semestre INTEGER NOT NULL)');
        $this->addSql('CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(180) NOT NULL, roles CLOB NOT NULL --(DC2Type:json)
        , password VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_IDENTIFIER_NOM ON user (nom)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE cours');
        $this->addSql('DROP TABLE semestre');
        $this->addSql('DROP TABLE user');
    }
}
