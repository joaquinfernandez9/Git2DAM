package dao;

import config.Configuracion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.modelo.Pelicula;
import domain.modelo.Serie;
import domain.modelo.Usuario;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DataBase {

    private Gson gson;
    private Configuracion configuracion;

    @Inject
    public DataBase(Gson gson, Configuracion configuracion){
        this.gson = gson;
        this.configuracion = configuracion;
    }

    public List<Usuario> loadUsuarios(){
        Type userListType = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarios = new ArrayList<>();
        try (FileReader r  = new FileReader(configuracion.getPathUsuarios())){
            usuarios = gson.fromJson(r, userListType);
        } catch (IOException e){
            log.error(e.getMessage(), e);
        }
        return usuarios;
    }

    public boolean saveUsuarios(List<Usuario> usuarios){
        try (FileWriter w = new FileWriter(configuracion.getPathUsuarios())){
            gson.toJson(usuarios, w);
        } catch (IOException e){
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public List<Pelicula> loadPeliculas(){
        Type userListType = new TypeToken<List<Pelicula>>(){}.getType();
        List<Pelicula> peliculas = new ArrayList<>();
        try (FileReader r  = new FileReader(configuracion.getPathPeliculas())){
            peliculas = gson.fromJson(r, userListType);
        } catch (IOException e){
            log.error(e.getMessage(), e);
        }
        return peliculas;
    }

    public boolean savePeliculas(List<Pelicula> peliculas){
        try (FileWriter w = new FileWriter(configuracion.getPathPeliculas())){
            gson.toJson(peliculas, w);
        } catch (IOException e){
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public List<Serie> loadSeries(){
        Type userListType = new TypeToken<List<Serie>>(){}.getType();
        List<Serie> series = new ArrayList<>();
        try (FileReader r  = new FileReader(configuracion.getPathSeries())){
            series = gson.fromJson(r, userListType);
        } catch (IOException e){
            log.error(e.getMessage(), e);
        }
        return series;
    }

    public boolean saveSeries(List<Serie> series){
        try (FileWriter w = new FileWriter(configuracion.getPathSeries())){
            gson.toJson(series, w);
        } catch (IOException e){
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }





}
