package ceng.bim208;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

class Sum {


    // Compute a checksum for the given path

    static long sum(Path p) {

        final long maxSize = 2147483648L / 8;

        java.util.zip.CRC32 crc32 = new CRC32();

        try {
            byte bytes[];
            if (Files.size(p) < maxSize) {
                bytes = Files.readAllBytes(p);
                crc32.update(bytes, 0, bytes.length);
            } else {

                File file = new File(p.toString());

                try (FileChannel in = new FileInputStream(file).getChannel()) {

                    ByteBuffer bytebuf = ByteBuffer.allocateDirect(128);

                    crc32 = new CRC32();

                    int bytesCount;
                    while ((bytesCount = in.read(bytebuf)) > 0) {
                        bytebuf.flip();
                        crc32.update(bytebuf);  // Write data from ByteBuffer to file
                        bytebuf.clear();     // For the next read
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return crc32.getValue();
    }
}