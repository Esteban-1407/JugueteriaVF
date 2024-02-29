package services;

import Utils.Constants;
import Utils.FileUtils;
import customExceptions.ToyStoreException;
import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.ToyType;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.*;

public class ToyStorelmpl implements ToyStorelnt {
    private List<model.Toy> toyStore;
    public void setToyStore(List<model.Toy> toyStore) {
        this.toyStore = toyStore;
    }
    public ToyStorelmpl(){
        toyStore = FileUtils.getToys(new File(Constants.PATH_TOYS));
    }

    @Override
    public List<ToyDTO> addToy(ToyDTO toyStoreDTO) throws Exception {
        if(!verifyExist(toyStoreDTO.name())){
            toyStore.add(ToyMapper.mapFrom(toyStoreDTO));
            FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);
            return toyStore.stream().map(ToyMapper::mapFrom).toList();
        }
        throw new Exception("This toy is in the list");
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyStore.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public ToyDTO search(String name) throws ToyStoreException.ToyNotFoundException {
        if(verifyExist(name)){
            List<ToyDTO> list = toyStore.stream().filter(toyList-> Objects.equals(toyList.getName(), name))
                    .findFirst().stream().map(ToyMapper::mapFrom).toList();
            return list.getFirst();
        }
        throw new ToyStoreException.ToyNotFoundException("Could not find");
    }

    @Override
    public Map.Entry<ToyType, Integer> maxToy() {
        return sort().entrySet().stream().reduce((first,second)-> second).orElse(null);
    }

    @Override
    public Map.Entry<ToyType, Integer> minToy() {
        return sort().entrySet().stream().findFirst().orElse(null);

    }

    @Override
    public List<ToyDTO> increase(ToyDTO toyStoreDTO, int amount) throws ToyStoreException.InvalidQuantityException {
        if(verifyExist(toyStoreDTO.name())){
            toyStore.stream().filter(toy1 -> Objects.equals(toy1.getName(), toyStoreDTO.name()))
                    .peek(toy -> toy.setStock(toy.getStock() + amount))
                    .findFirst();
            FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);
            return toyStore.stream().map(ToyMapper::mapFrom).toList();
        } throw new ToyStoreException.InvalidQuantityException("The toy is not in the list");
    }

    @Override
    public List<ToyDTO> decrease(ToyDTO toyStoreDTO, int amount) throws ToyStoreException.InvalidQuantityException {
        Optional<model.Toy> toyOptional = toyStore.stream()
                .filter(toy -> Objects.equals(toy.getName(), toyStoreDTO.name()))
                .findFirst();

        if (toyOptional.isPresent()) {
            model.Toy toy = toyOptional.get();
            int currentStock = toy.getStock();
            if (currentStock < amount) {
                throw new ToyStoreException.InvalidQuantityException("No es posible realizar la disminución ya que la cantidad supera el stock disponible");
            }
            toy.setStock(currentStock - amount);
            FileUtils.saveToys(new File(Constants.PATH_TOYS), toyStore);
            return listToys(); // Devuelve la lista de juguetes actualizada
        } else {
            throw new ToyStoreException.InvalidQuantityException("El juguete no está en la lista");
        }
    }


    @Override
    public Map<ToyType, Integer> showByType() {
        if (toyStore != null) {
            Map<ToyType, Integer> showByType = new TreeMap<>();
            for (model.Toy toy : toyStore) {
                ToyType type = toy.getType();
                showByType.put(type, showByType.getOrDefault(type, 0) + 1);
            }
            return showByType;
        }
        return null;
    }


    @Override
    public Map<ToyType, Integer> sort()  {
            if(!toyStore.isEmpty()) {
                return showByType().entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> entry.getValue(),
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new));
            } return  null;
        }

    @Override
    public List<ToyDTO> showToysAbove(double value) throws ToyStoreException.InvalidPriceException {
        if(!toyStore.isEmpty()) {
            List<ToyDTO> storeDTOList = toyStore.stream()
                    .filter(toy -> toy.getPrice() >= value)
                    .toList().stream().map(ToyMapper::mapFrom).toList();
            if (storeDTOList.isEmpty()) {
                System.out.println("There is not toys above that price");
            }
            return storeDTOList;
        } throw new ToyStoreException.InvalidPriceException("The list is empty");
    }

    @Override
    public Boolean verifyExist(String name) {
        return toyStore.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }

    @Override
    public Integer totalToys() {
        Integer totalCount = 0;
        for (model.Toy toy : toyStore){
            totalCount += toy.getStock();
        }
        return totalCount;
    }

    @Override
    public Integer totalPriceAllToys() {
        Integer totalPrice = 0;
        for (model.Toy toy:toyStore){
            totalPrice += toy.getPrice();
        }
        return totalPrice;
    }
    }


