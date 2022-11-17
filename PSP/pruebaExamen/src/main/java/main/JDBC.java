package main;

import dao.DaoFaction;

public class JDBC {
    public static void main(String[] args) {

        DaoFaction daoFaction = new DaoFaction();
        //get all factions
        daoFaction.loadToDatabase().getFaction().forEach(System.out::println);


    }
}
