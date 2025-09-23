package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class EmployeTest {

    @Spy
    public Employe emp1;

    @Test
    public void employe0to10() {
        emp1.setSalaireDeBase(2000d);
        emp1.setAnnee(0);
        doReturn(2000)
        .when(emp1).salaire();
    }
}