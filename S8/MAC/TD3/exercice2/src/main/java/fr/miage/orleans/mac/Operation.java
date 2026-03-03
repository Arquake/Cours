package fr.miage.orleans.mac;

public interface Operation {

    CustomBooleans apply(CustomBooleans b1, CustomBooleans b2);
    CustomBooleans apply(Operation o1, CustomBooleans b2);
    CustomBooleans apply(CustomBooleans b1, Operation o2);
}
