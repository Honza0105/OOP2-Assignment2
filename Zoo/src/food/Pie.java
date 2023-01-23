package food;

import util.IFood;

public class Pie implements IFood {
    @Override
    public int getFillingValue() {
        return 3;
    }
}
