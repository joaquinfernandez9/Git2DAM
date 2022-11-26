package main;

import config.ConfigDatabase;
import config.DatabaseConnection;
import dao.DaoFaction;
import dao.DaoWeapons;
import model.Battle;
import model.Faction;
import model.Weapon;

import java.time.LocalDate;

public class JDBC {

    public static void main(String[] args) {

        ConfigDatabase config = new ConfigDatabase();
        DatabaseConnection con = new DatabaseConnection(config);
        DaoFaction daoFaction = new DaoFaction(con);
        DaoWeapons daoWeapons =new DaoWeapons(con);
        //get all factions
//        daoFaction.getAllFactionsXML().getFaction().forEach(System.out::println);

        //no recibe lista, funciona con el get all de arriba
//        daoFaction.saveFactionsToDatabase();

        //tambien hay que comprobar que el spy exista
        Faction fac1 = daoFaction.getAllFactions().get(0);
        Faction fac2 = new Faction("Marygeoise", "mi padre", "marte", 2, LocalDate.now());
        Battle battle = new Battle(1, "casablanca", fac1.getName(), fac2.getName(),
                "galaxia", LocalDate.parse("2019-05-22"), 1);
        daoFaction.saveBattle(battle, fac1, fac2);

        Weapon weapon = new Weapon("Patata atomica", 420.69);
//        daoWeapons.notSave(weapon);

        //id nuevoPrecio
        Weapon update = new Weapon(6, 30);
        daoWeapons.update(update);

        con.closePool();


    }
}
