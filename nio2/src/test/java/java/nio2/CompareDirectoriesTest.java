package java.nio2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CompareDirectoriesTest {

    private static final String TEST_COMPARE_DIRECTORIES_DIR = "testCompareDirectoriesDir";

    @Test
    public void testCompareDirectories() throws IOException {

        Path testDir = Files.createTempDirectory(TEST_COMPARE_DIRECTORIES_DIR);

        try {
            File oldDir = new File(testDir.toFile(), "oldDir");
            File newDir = new File(testDir.toFile(), "newDir");

            Files.createDirectory(oldDir.toPath());
            Files.createDirectory(newDir.toPath());

            Files.createFile(new File(oldDir, "1").toPath());
            Files.createFile(new File(oldDir, "2").toPath());
            Files.createFile(new File(oldDir, "3").toPath());
            Files.createFile(new File(oldDir, "4").toPath());

            Files.createFile(new File(newDir, "3").toPath());
            Files.createFile(new File(newDir, "4").toPath());
            Files.createFile(new File(newDir, "5").toPath());
            Files.createFile(new File(newDir, "6").toPath());

            List[] results = CompareDirectories.compareDirectories(oldDir.toPath(), newDir.toPath());
            assertNotNull(results);
            assertEquals(2, results.length);

            Path[] expectedRemovedFiles = new Path[]{
                    new File("1").toPath(),
                    new File("2").toPath(),
            };
            Path[] expectedAddedFiles = new Path[]{
                    new File("5").toPath(),
                    new File("6").toPath(),
            };
            checkResults(expectedRemovedFiles, results[0]);
            checkResults(expectedAddedFiles, results[1]);
        } finally {
            // Be REALLY careful with this if you like the contents of your
            // file system
            DeleteTestFiles deleteTestFiles = new DeleteTestFiles();
            Files.walkFileTree(testDir, deleteTestFiles);
        }
    }

    private void checkResults(Path[] expected, List<Path> actual) {
        assertEquals(expected.length, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(expected)));
    }

    /**
     * File visitor to recursively delete the test file directory. Be careful about making changes to this.
     */
    public static class DeleteTestFiles extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            // TODO delete file
            return FileVisitResult.CONTINUE;
        }

        // Print each directory visited.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            // TODO delete directory
            return FileVisitResult.CONTINUE;
        }

    }

}
