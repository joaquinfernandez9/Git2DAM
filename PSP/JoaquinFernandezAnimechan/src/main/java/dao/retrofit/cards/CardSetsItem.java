package dao.retrofit.cards;

import lombok.Data;

@Data
public class CardSetsItem{
	private String set_code;
	private String set_rarity;
	private String set_name;
	private String set_rarity_code;
	private String set_price;

	@Override
	public String toString() {
		return "CardSetsItem{" +
				"set_code='" + set_code + '\'' +
				", set_rarity='" + set_rarity + '\'' +
				", set_name='" + set_name + '\'' +
				", set_rarity_code='" + set_rarity_code + '\'' +
				", set_price='" + set_price + '\'' +
				'}';
	}
}
