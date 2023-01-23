package food;

import util.IFood;

public class Steak implements IFood {

    @Override
    public int getFillingValue() {
        return 10;
    }
}
