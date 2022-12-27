package com.readingisgood.enums;

public enum Status {
    ACTIVE('A'), PASSIVE('P');

    Character statusChar;
    Status(Character statusChar)
    {
        this.statusChar = statusChar;
    }

    public Character getVal()
    {
        return statusChar;
    }
}
