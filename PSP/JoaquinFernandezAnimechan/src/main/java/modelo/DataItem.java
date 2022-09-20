package modelo;

import lombok.Data;

import java.util.List;

@Data
public class DataItem{
	private List<CardPricesItem> cardPrices;
	private int def;
	private String race;
	private List<CardSetsItem> cardSets;
	private int level;
	private String type;
	private String archetype;
	private String name;
	private int atk;
	private List<CardImagesItem> cardImages;
	private int id;
	private String attribute;
	private String desc;

	public String getName(){
		return name;
	}
}