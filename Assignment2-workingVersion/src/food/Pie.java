package food;

import util.IFood;

/**
 * Example class of IFood.
 */
public class Pie implements IFood {
    @Override
    public int getFillingValue() {
        return 3;
    }
}
