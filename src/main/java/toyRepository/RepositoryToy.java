package services;

import model.toy;

import java.util.List;

public interface RepositoryToy<T>{
    List<T> list();
    T byId(int id);


    void save(toy toy);

    void delete(int id);

    List<T>showByTYpe();


    void update(toy toy);
}
