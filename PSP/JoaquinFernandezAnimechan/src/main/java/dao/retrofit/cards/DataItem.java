package dao.retrofit.cards;

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



}