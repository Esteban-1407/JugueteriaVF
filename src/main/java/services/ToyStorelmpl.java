package services;

import config.DatabaseConnection;
import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.toy;
import toyRepository.RepositoryToy;
import toyRepository.ToyRepositoryJDBClmpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ToyStorelmpl implements ToyStorelnt {
    Connection conn = DatabaseConnection.getInstance();
    private RepositoryToy<toy> toyRepository;

    public ToyStorelmpl() throws SQLException {
        this.toyRepository = new  ToyRepositoryJDBClmpl();
    }
    @Override
    public void addToy(ToyDTO toyDTO) {
        toyRepository.save(ToyMapper.mapFromDTO(toyDTO));
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyRepository.list()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();


    }

    @Override
    public List<ToyDTO> showByType() {
        return  toyRepository.showByTYpe()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }


    @Override
    public ToyDTO search(Integer id) throws SQLException {
       return ToyMapper.mapFromModel(toyRepository.byId(id));
   }
    @Override
    public int getTotalStock() {
        return toyRepository.getTotalStock();
    }

    @Override
    public double getTotalValue() {
        return toyRepository.getTotalValue();
    }

    @Override
    public String getTypeWithMostToys() {
        return toyRepository.TypeWithMostToys();
    }

    @Override
    public String getTypeWithLeastToys() {
        return toyRepository.TypeWithLeastToys();
    }



    @Override
    public List<ToyDTO> getToysWithValueGreaterThan(int value) {
        List<toy> toys = toyRepository.ToysWithAnValue(value);
        return toys.stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }

    @Override
    public List<ToyDTO> orderByStockQuantity() {
        List<toy> toys = toyRepository.orderByStockQuantity();
        return toys.stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }

    @Override
    public void updateStock(int toyId, int quantityChange) {
        toyRepository.updateStock(toyId, quantityChange);
    }



   }


