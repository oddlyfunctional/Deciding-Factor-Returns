package decidingFactorReturns.exceptions;

public class BestCutException extends CutException{

    public BestCutException(float expected, float got) {
        super(expected, got);
    }

}
