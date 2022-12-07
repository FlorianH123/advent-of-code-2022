package day7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DirEntry {
	protected String name;
	protected Directory parent;

	public DirEntry(final String name) {
		this.name = name;
	}

	public boolean isDirectory() {
		return this instanceof Directory;
	}

	public abstract long getSize();
}
