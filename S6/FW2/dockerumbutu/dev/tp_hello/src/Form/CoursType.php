<?php

namespace App\Form;

use App\Entity\Cours;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CoursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('semestre')
            ->add('name')
            ->add('description')
            ->add('ects')
            ->add('heureTp')
            ->add('heureTd')
            ->add('heureCm')
            ->add('markdown', CheckBoxType::class, array('label' => 'Markdown', 'required' => false, 'mapped' => false))
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cours::class,
        ]);
    }
}
