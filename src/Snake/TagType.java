package Snake;

public enum TagType {
    EMPTY_TAG(0), BRICK_TAG(1), FOOD_1_TAG(3);

    private final int value;

    TagType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
