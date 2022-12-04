package util;

public class Pair<L, R> {
    protected final L left;
    protected final R right;

    protected Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }

    public static <L,R> Pair<L,R> of(final L left, final R right) {
        return new Pair<>(left, right);
    }
}
