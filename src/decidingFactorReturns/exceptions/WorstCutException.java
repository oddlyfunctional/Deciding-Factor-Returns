package decidingFactorReturns.exceptions;

public class WorstCutException extends CutException {

    public WorstCutException(float expected, float got) {
        super(expected, got);
    }

}
