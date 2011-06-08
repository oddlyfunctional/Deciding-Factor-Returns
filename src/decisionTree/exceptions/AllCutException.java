package decisionTree.exceptions;

public class AllCutException extends CutException {

    public AllCutException(float expected, float got) {
        super(expected, got);
    }

}
