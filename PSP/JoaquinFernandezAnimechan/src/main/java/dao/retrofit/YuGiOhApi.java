package dao.retrofit;

import modelo.ResponseApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YuGiOhApi {

    @GET("v7/cardinfo.php?name=")
    Call<ResponseApi> getCardInfo(@Query("name") String name);

    //https://db.ygoprodeck.com/api/v7/cardinfo.php?name
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?fname  // buscar las cartas que tengan el texto introducido en el nombre
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?id  //no entiendo este del todo, mirar foro
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?type // buscar por tipo (https://yugipedia.com/wiki/Type)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?atk //filtrar por ataque
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?def //filtrar por defensa
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?level //filtrar por rango (https://yugipedia.com/wiki/Level)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?race //filtrar por raza (https://yugipedia.com/wiki/Race)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?attribute // filtrar por atributo (https://yugipedia.com/wiki/Attribute)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?link // flitrar por el valor de link
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?linkmarker // filtrar por el tipo de link (https://yugipedia.com/wiki/Link_Marker)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?scale // pendulum scale value  (https://yugipedia.com/wiki/Pendulum_Scale)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?archetype // filtrar por arquetipo (https://yugipedia.com/wiki/Archetype)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?cardset // filtrar por set (https://yugipedia.com/wiki/Set)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?banlist // filtrar por banlist (https://yugipedia.com/wiki/Banlist)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?format // filtrar por formato (https://yugipedia.com/wiki/Format)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?sort // ordenar por nombre, ataque, defensa, rango, etc
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?misc // filtrar por misc (https://yugipedia.com/wiki/Miscellaneous)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?staple // filtrar por staple (https://yugipedia.com/wiki/Staple) ?????????
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?has_effect // filtrar por si tiene efecto o no (https://yugipedia.com/wiki/Effect) (meter boolean)
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?startdate
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?enddate
    //https://db.ygoprodeck.com/api/v7/cardinfo.php?dateregion



}
