package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


class EmployeTest {

    @Spy
    public static Employe emp1;

    @Spy
    public Categorie categorie;

    @BeforeAll
    static void setup() {
        emp1 = spy(new Employe(0,2000d));
    }

    @Test
    void valCatEmploye0to10() {
        emp1.setAnnee(0);

        categorie = spy(new Categorie());

        //doReturn(1).when(categorie).valCategorie(0);
        //doReturn(1).when(categorie).valCategorie(10);

        assertEquals(1, categorie.valCategorie(emp1.getAnnee()));

        emp1.setAnnee(10);

        assertEquals(1, categorie.valCategorie(emp1.getAnnee()));
    }

    @Test
    void valCatEmploye11to20() {
        emp1.setAnnee(11);

        categorie = spy(new Categorie());

        //doReturn(2).when(categorie).valCategorie(11);
        //doReturn(2).when(categorie).valCategorie(20);

        assertEquals(2, categorie.valCategorie(emp1.getAnnee()));

        emp1.setAnnee(20);

        assertEquals(2, categorie.valCategorie(emp1.getAnnee()));
    }

    @Test
    void valCatEmployerOver20() {
        emp1.setAnnee(21);

        categorie = spy(new Categorie());

        //doReturn(3).when(categorie).valCategorie(21);

        assertEquals(3, categorie.valCategorie(emp1.getAnnee()));
    }

    @Test
    void employe0to10() {
        emp1.setAnnee(0);

        //doReturn(2000d).when(emp1).salaire();
        //doReturn(2000d).when(emp1).salaire();

        assertEquals(2000d, emp1.salaire());

        emp1.setAnnee(10);

        assertEquals(2000d, emp1.salaire());
    }

    @Test
    void employe11to20() {
        emp1.setAnnee(11);

        //doReturn(2200d).when(emp1).salaire();
        //doReturn(2200d).when(emp1).salaire();

        assertEquals(2200d, emp1.salaire());

        emp1.setAnnee(20);

        assertEquals(2200d, emp1.salaire());
    }

    @Test
    void employerOver20() {
        emp1.setAnnee(21);

        //doReturn(2400d).when(emp1).salaire();

        assertEquals(2400d, emp1.salaire());
    }
}