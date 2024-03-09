package services;

import mapping.dtos.ToyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ToyStorelnt {
    void addToy(ToyDTO toyDTO);
    List<ToyDTO> listToys();
    List<ToyDTO> showByType();
    ToyDTO search(Integer id) throws SQLException;

    int getTotalStock();

    double getTotalValue();

    String getTypeWithMostToys();

    String getTypeWithLeastToys();



    List<ToyDTO> getToysWithValueGreaterThan(int value);

    List<ToyDTO> orderByStockQuantity();

    void updateStock(int toyId, int quantityChange);
}
