package food;

import util.IFood;

/**
 * Example class of IFood.
 */
public class Steak implements IFood {

    @Override
    public int getFillingValue() {
        return 10;
    }
}
