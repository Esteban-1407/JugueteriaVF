package model;

import java.util.Arrays;

public enum ToyType {

        FEMALE(0),
        MALE(1),
        UNISEX(2);

        private final int value;

        ToyType(int value) {
            this.value = value;
        }

        public static ToyType getTypeByValue(int value){
            return Arrays.stream(ToyType.values()).filter(e->e.value==value).findFirst().orElseThrow();
        }
}
