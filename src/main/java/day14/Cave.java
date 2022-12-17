package day14;

public record Cave(int minX, int minY, int maxX, int maxY, char[][] layout) {

	public void print() {
		for (int i = 0; i < layout.length; i++) {
			char[] chars = layout[i];
			System.out.print(i + " ");
			for (final char c : chars) {
				System.out.print(c + " ");
			}
			System.out.println();
		}

		System.out.println();
	}
}
