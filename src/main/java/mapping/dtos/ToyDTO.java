package mapping.dtos;

import model.ToyType;

public record ToyDTO(String name, ToyType type, Integer price, Integer stock) {
}
