package domain.modelo;

import dao.retrofit.cards.CardImagesItem;
import dao.retrofit.cards.CardPricesItem;
import dao.retrofit.cards.CardSetsItem;
import lombok.Data;

import java.util.List;

@Data
public class Carta {
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
    private List<ListaPreciosCarta> preciosCartas;
    private List<ListaImgCarta> listaImgCartas;

    public Carta(String name, int id, int level, String atk,
                 int def, String type, String race, String attribute,
                 String desc, String archetype,
                 List<ListaPreciosCarta> preciosCartas,
                 List<ListaImgCarta> listaImgCartas) {
        this.name = name;
        this.id = id;
        this.level = level;
        this.atk = atk;
        this.def = def;
        this.type = type;
        this.race = race;
        this.attribute = attribute;
        this.desc = desc;
        this.archetype = archetype;
        this.preciosCartas = preciosCartas;
        this.listaImgCartas = listaImgCartas;
    }
}
