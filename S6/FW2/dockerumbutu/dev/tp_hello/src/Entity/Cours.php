<?php

namespace App\Entity;

use App\Repository\CoursRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CoursRepository::class)]
class Cours
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $semestre = null;

    #[ORM\Column(length: 255)]
    private ?string $name = null;

    #[ORM\Column(type: Types::TEXT)]
    private ?string $description = null;

    #[ORM\Column]
    private ?float $ects = null;

    #[ORM\Column]
    private ?float $heureTp = null;

    #[ORM\Column]
    private ?float $heureTd = null;

    #[ORM\Column]
    private ?float $heureCm = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSemestre(): ?int
    {
        return $this->semestre;
    }

    public function setSemestre(int $semestre): static
    {
        $this->semestre = $semestre;

        return $this;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): static
    {
        $this->name = $name;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): static
    {
        $this->description = $description;

        return $this;
    }

    public function getEcts(): ?float
    {
        return $this->ects;
    }

    public function setEcts(float $ects): static
    {
        $this->ects = $ects;

        return $this;
    }

    public function getHeureTp(): ?float
    {
        return $this->heureTp;
    }

    public function setHeureTp(float $heureTp): static
    {
        $this->heureTp = $heureTp;

        return $this;
    }

    public function getHeureTd(): ?float
    {
        return $this->heureTd;
    }

    public function setHeureTd(float $heureTd): static
    {
        $this->heureTd = $heureTd;

        return $this;
    }

    public function getHeureCm(): ?float
    {
        return $this->heureCm;
    }

    public function setHeureCm(float $heureCm): static
    {
        $this->heureCm = $heureCm;

        return $this;
    }
}
