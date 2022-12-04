package util;

public class Pair<L, R> {
    private final L lhs;
    private final R rhs;

    private Pair(final L lhs, final R rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public L left() {
        return lhs;
    }

    public R right() {
        return rhs;
    }

    public static <L,R> Pair<L,R> of(final L lhs, final R rhs) {
        return new Pair<>(lhs, rhs);
    }
}
