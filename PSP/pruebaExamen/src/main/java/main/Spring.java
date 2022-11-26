package main;

import config.ConfigDatabase;
import config.DatabaseConnection;
import dao.DaoFaction;
import model.BnameSname;
import model.Spy;
import java.util.List;

public class Spring {
    public static void main(String[] args) {
        ConfigDatabase config = new ConfigDatabase();
        DatabaseConnection con = new DatabaseConnection(config);
        DaoFaction daoFaction = new DaoFaction(con);
//        DaoBattles daoBattles = new DaoBattles(con, daoFaction);

//        LocalDate date = LocalDate.of(2022, 11, 22);
//        List<QueryOne> list = daoWeapons.queryOne(date);
//        System.out.println(list);

//        Spy spy = new Spy(3, "manolo lama", "perro");
//        List<BnameSname> list1 = daoFaction.getBattles(spy);
//        System.out.println(list1);

        //update faction
//        daoFaction.update("Marygeoise", "Tamagocchi");
    }
}
