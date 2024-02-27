package services;

import mapping.dtos.ToyDTO;
import model.ToyType;

import java.util.List;
import java.util.Map;

public interface ToyStorelnt {
    List<ToyDTO> addToy(ToyDTO toyStoreDTO) throws Exception;
    List<ToyDTO> listToys();
    ToyDTO search(String name) throws Exception;
    Map.Entry<ToyType,Integer> maxToy();
    Map.Entry<ToyType,Integer> minToy();
    List<ToyDTO> increase(ToyDTO toyStoreDTO, int amount) throws Exception;
    List<ToyDTO> decrease(ToyDTO toyStoreDTO, int amount) throws Exception;
    Map<ToyType,Integer> showByType() throws Exception;
    Map<ToyType,Integer> sort();
    List<ToyDTO> showToysAbove(double value) throws Exception;
    Boolean verifyExist(String name);
    Integer totalToys() ;
    Integer totalPriceAllToys();
}
