package com.sp.allinone.persistance.model;

/**
 * Created by i82298 on 1/22/2017.
 */
public enum Module {
    ACCOUNT(1, "Account", "Account"),
    Library(2, "Library", "Library"),
    MGMT(3, "Managemant", "Management");
    int id;
    String name;
    String label;

    private Module(int id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

}
