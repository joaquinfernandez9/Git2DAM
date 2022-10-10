package dao.retrofit.cards;

import lombok.Data;

@Data
public class CardPricesItem{
	private String coolstuffinc_price;
	private String tcgplayer_price;
	private String amazon_price;
	private String ebay_price;
	private String cardmarket_price;

	public CardPricesItem(String coolstuffincPrice, String tcgplayerPrice,
						  String amazonPrice, String ebayPrice, String cardmarketPrice) {
		this.coolstuffinc_price = coolstuffincPrice;
		this.tcgplayer_price = tcgplayerPrice;
		this.amazon_price = amazonPrice;
		this.ebay_price = ebayPrice;
		this.cardmarket_price = cardmarketPrice;
	}


	@Override
	public String toString() {
		return "Coolstuffinc price='" + coolstuffinc_price +','+ '\n' +
				"Tcgplayer price='" + tcgplayer_price +','+ '\n' +
				"Amazon price='" + amazon_price +','+ '\n' +
				"Ebay price='" + ebay_price +','+ '\n' +
				"Cardmarket price='" + cardmarket_price;
	}
}
