package nio;

import com.google.common.base.Charsets;
import com.google.common.collect.FluentIterable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.List;

public class ZipDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path javaHome = Paths.get(System.getProperty("java.home"));
        Path zipFile = javaHome.getParent().resolve("src.zip");
        if (!Files.exists(zipFile)) {
            System.err.println(zipFile + " does not exist.");
            return;
        }

        String sourceName = "java/lang/String.java";
        Path tempDir = Files.createTempDirectory("corejava");
        Path targetPath = tempDir.resolve(sourceName);
        Files.createDirectories(targetPath.getParent());
        try (FileSystem zipfs = FileSystems.newFileSystem(zipFile, null)) {
            Files.copy(zipfs.getPath(sourceName), targetPath);
            List<String> lines = FluentIterable.from(com.google.common.io.Files.readLines(targetPath.toFile(), Charsets.UTF_8)).limit(40).toList();
            for (String line: lines) {
                System.out.println(line);
            }
            System.out.println("\nFiles that don't end in .java:");
            Files.walkFileTree(zipfs.getPath("/"), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(Files.isRegularFile(file) && !file.toString().endsWith(".java")) {
                        System.out.println(file);
                    }
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
        }

        Path zipPath = tempDir.resolve("myfile.zip");
        URI uri = new URI("jar", zipPath.toUri().toString(), null);
        // Constructs the URI jar:file://myfile.zip
        try (FileSystem zipfs = FileSystems.newFileSystem(uri,
                Collections.singletonMap("create", "true"))) {
            // To add files, copy them into the ZIP file system
            Path filePath = targetPath;
            Files.copy(filePath, zipfs.getPath("/").resolve("String.java"));
        }
        System.out.println("Made zip file at " + zipPath);
    }
}
