package day4;

import util.Pair;

public class SectionBoundary extends Pair<Integer, Integer> {
    private SectionBoundary(Integer left, Integer right) {
        super(left, right);
    }

    public static SectionBoundary of(final Integer left, final Integer right) {
        return new SectionBoundary(left, right);
    }

    public boolean containsBoundary(final SectionBoundary other) {
        return other.left >= left && other.right <= right;
    }

    public boolean containsSection(final int section) {
        return section >= left && section <= right;
    }

    public boolean hasSectionInBoundary(final SectionBoundary other) {
        return other.containsSection(left) || other.containsSection(right);
    }
}
