package nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(System.getProperty("java.home"));
        System.out.printf("Directories inside %s:\n", path);
        for (String f: path.toFile().list()) {
            System.out.println(f);
        }

        // Copy directory tree
        
        final Path source = path;
        final Path target = Files.createTempDirectory("corejava").resolve("jre");
        Files.walkFileTree(source, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path q = target.resolve(source.relativize(dir));
                System.out.printf("Creating %s\n", q);
                Files.createDirectory(q);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path q = target.resolve(source.relativize(file));
                System.out.printf("Copying %s to %s\n", file, q);
                Files.copy(file, q);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        
        // Delete the copy
                
        Path root = target;
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                System.out.printf("Deleting %s\n", file);                
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir,
                    IOException ex) throws IOException {
                if (ex != null) throw ex;
                Files.delete(dir);
                System.out.printf("Removing %s\n", dir);
                return FileVisitResult.CONTINUE;
            }
        });        
    }
}