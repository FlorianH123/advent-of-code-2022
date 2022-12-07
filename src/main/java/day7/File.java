package day7;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class File extends DirEntry {
	private long fileSize;

	public File(final String name, final long fileSize) {
		super(name);
		this.fileSize = fileSize;
	}

	@Override
	public long getSize() {
		return this.fileSize;
	}
}
