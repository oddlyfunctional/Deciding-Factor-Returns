package decisionTree.exceptions;

public abstract class CutException extends Exception {
    private float expected;
    private float got;

    public CutException(float expected, float got) {
        this.expected = expected;
        this.got = got;
    }

}
