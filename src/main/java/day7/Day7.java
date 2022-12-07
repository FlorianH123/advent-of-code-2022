package day7;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import util.InputFileUtils;
import util.StringUtils;

public class Day7 {

	public static void main(final String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day7/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.println("Total file size: " + result1);

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.println("Total file size " + result2);
	}

	public static long part1(final List<String> lines) {
		final var commands = parseCommands(lines);
		final var fileSystemRoot = buildFileSystem(commands);
		final var dirs = getDirectories(fileSystemRoot);
		return getDirectoriesWithMaxSize(dirs, 100_000L);
	}

	public static long part2(final List<String> lines) {
		final var commands = parseCommands(lines);
		final var fileSystemRoot = buildFileSystem(commands);
		final var dirs = getDirectories(fileSystemRoot);
		return getSizeOfDirectoryToDelete(fileSystemRoot, dirs, 70_000_000L, 30_000_000L);
	}

	private static List<Command> parseCommands(final List<String> lines) {
		final List<Command> commands = new ArrayList<>();
		Command currentCommand = null;

		for (final String line : lines) {
			if (Command.isCommand(line)) {
				currentCommand = Command.fromString(line);
				commands.add(currentCommand);
			} else {
				assert currentCommand != null;
				currentCommand.getOutput().add(line);
			}
		}

		return commands;
	}

	private static Directory buildFileSystem(final List<Command> commands) {
		Directory root = null;
		Directory current = null;

		for (final var command : commands) {
			final var commandType = command.getType();

			if (root == null) {
				if (!commandType.equals(CommandTypeE.CD)) {
					throw new IllegalArgumentException("Input does not start at root");
				}
				root = new Directory(command.getArguments()
						.orElseThrow(() -> new IllegalArgumentException("ls command has no args")));
				current = root;
				continue;
			}

			switch (commandType) {
				case LS -> {
					for (final String output : command.getOutput()) {
						DirEntry dirEntry;

						if (isDirectory(output)) {
							dirEntry = new Directory(StringUtils.substringAfter(output, "dir "));
						} else {
							final var fileStringSplit = output.split(" ");

							if (fileStringSplit.length != 2) {
								throw new IllegalArgumentException(
										"File string " + output + "can not be parsed to a file!");
							}

							dirEntry = new File(fileStringSplit[1], Long.parseLong(fileStringSplit[0]));
						}

						dirEntry.parent = current;
						current.addChildren(dirEntry);
					}
				}
				case CD -> {
					final var path = Path.of(command.getArguments().orElseThrow(
							() -> new IllegalArgumentException(CommandTypeE.CD.getValue() + " has no arguments")));
					for (final var subPath : path) {
						final var subPathString = subPath.toString();

						if (subPathString.equals("/")) {
							current = root;
						}

						switch (subPathString) {
							case "." :
								break;
							case ".." :
								current = current.parent;
								break;
							default :
								Directory finalCurrent = current;
								current = (Directory) current.getChildren().stream()
										.filter(child -> child.name.equals(subPathString) && child.isDirectory())
										.findFirst().orElseThrow(() -> new IllegalArgumentException(
												subPathString + " is not a child of " + finalCurrent.name));
						}
					}
				}
			}
		}

		return root;
	}

	private static long getDirectoriesWithMaxSize(final List<Directory> directories, final long maxDirectorySize) {
		return directories.stream().map(Directory::getSize).filter(size -> size <= maxDirectorySize).reduce(0L,
				Long::sum);
	}

	private static long getSizeOfDirectoryToDelete(final Directory rootDirectory, final List<Directory> directories,
			final long totalSpace, final long requiredFreeSpace) {
		final var usedSpace = rootDirectory.getSize();
		final var freeSpace = totalSpace - usedSpace;
		return directories.stream().map(Directory::getSize).filter(size -> freeSpace + size >= requiredFreeSpace)
				.min(Comparator.comparingLong(Long::valueOf)).orElseThrow(() -> new IllegalArgumentException(""));
	}

	private static List<Directory> getDirectories(final Directory current) {
		final var directories = new ArrayList<Directory>();

		if (current.isDirectory()) {
			final var subDirectories = current.getChildren().stream().filter(DirEntry::isDirectory)
					.map(dir -> (Directory) dir).toList();
			subDirectories.stream().map(Day7::getDirectories).forEach(directories::addAll);
			directories.add(current);
		}

		return directories;
	}

	private static boolean isDirectory(final String output) {
		return output.startsWith("dir");
	}
}
