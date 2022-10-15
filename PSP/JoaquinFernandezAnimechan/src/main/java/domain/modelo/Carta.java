package domain.modelo;

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


    //no tengo ni idea de este, es la primera vez que me sale
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
                "Prices= "+ preciosCartas+','+'\n'+
                "Card Images= "+ listaImgCartas+'\n'+
                '}';
    }
}
