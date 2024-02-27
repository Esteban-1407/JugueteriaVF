package mapping.mappers;

import mapping.dtos.ToyDTO;

public class ToyMapper {
    public static model.Toy mapFrom(ToyDTO toyStoreDTO){
        return new model.Toy(toyStoreDTO.name(),toyStoreDTO.stock(),toyStoreDTO.price(),toyStoreDTO.type());
    }

    public static ToyDTO mapFrom(model.Toy toy){return  new ToyDTO(toy.getName(),toy.getType(),toy.getPrice(),toy.getStock()  );}}

