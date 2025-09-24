package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


class EmployeTest {

    public Employe emp1;

    @Spy
    public Categorie categorie;

    @Test
    public void employe0to10() {
        emp1 = new Employe(0,2000d);

        categorie = spy(new Categorie());

        doReturn(1).when(categorie).valCategorie(0);

        assertEquals(1, categorie.valCategorie(emp1.getAnnee()));
    }
}