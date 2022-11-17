package dao;

import config.Config;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.Faction;
import model.Factions;

import java.io.File;
import java.util.List;

public class DaoFaction {

    public Factions loadToDatabase() {
        Factions factionList;
        try {
            File file = new File(Config.getInstance().getProperty("xmlFactionsPath"));
            JAXBContext jaxbContext = JAXBContext.newInstance(Faction.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            factionList = (Factions) jaxbUnmarshaller
                    .unmarshal(file);
//            JdbcTemplate jdbcTemplate = new JdbcTemplate();

        } catch (JAXBException e) {
            e.printStackTrace();
            factionList = null;
        }
        return factionList;
    }


}
