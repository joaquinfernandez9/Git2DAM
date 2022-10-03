package domain.modelo.cards;

import lombok.Data;

import java.util.List;

@Data
public class DataItem {
    private String name;
    private int id;
    private int level;
    private String atk;
    private int def;
    private String type;
    private String race;
    private String attribute;
    private String desc;
    private String archetype;
    private List<CardSetsItem> card_sets;
    private List<CardPricesItem> card_prices;
    private List<CardImagesItem> card_images;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name= " + name + ','+
                "ID= " + id +','+
                "Level= " + level +','+'\n'+
                "ATK= " + atk +','+
                "DEF= " + def +','+'\n'+
                "Type= " + type + ','+
                "Race= " + race + ','+
                "Attribute= " + attribute + ','+'\n'+
                "Desc= " + desc + '\n' +
                "Archetype= "+ archetype+','+'\n'+
                "Sets item= "+ card_sets +','+'\n'+
                "Prices= "+ card_prices+','+'\n'+
                "Card Images= "+ card_images+'\n'+
                '}';
    }
}