package day7;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Directory extends DirEntry {
	private final List<DirEntry> children = new ArrayList<>();

	@Setter(AccessLevel.PRIVATE)
	private long childrenLength = 0;

	public Directory(final String name) {
		this.name = name;
	}

	@Override
	public long getSize() {
		return children.stream().map(DirEntry::getSize).reduce(0L, Long::sum);
	}

	public void addChildren(final DirEntry dirEntry) {
		this.children.add(dirEntry);
		childrenLength++;
	}
}
