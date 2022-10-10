package dao.retrofit.cards;

import lombok.Data;

@Data
public class CardImagesItem{
	private String image_url;
	private String image_url_small;
	private int id;


	@Override
	public String toString() {
		return "CardImagesItem{" +','+ '\n' +
				"image_url='" + image_url + ','+'\n' +
				"image_url_small='" + image_url_small +','+ '\n' +
				"id=" + id +
				'}';
	}
}
