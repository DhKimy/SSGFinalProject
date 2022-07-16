package com.ll.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Util {
    public static void saveToFile(String path, List<WiseSaying> wiseSayings) {
        // 파일 삭제
        new File(path).delete();

        try {

            RandomAccessFile stream = new RandomAccessFile(path, "rw");
            FileChannel channel = stream.getChannel();

            StringBuilder sb = new StringBuilder();
            for(WiseSaying wiseSaying : wiseSayings) {
                if(!sb.isEmpty()) sb.append(",\n");
                sb.append(wiseSaying.toJson());
            }

            byte[] strBytes = sb.toString().getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);
            stream.close();
            channel.close();

        } catch (IOException e) {

        }
    }


    public static List<WiseSaying> readFromFile(String path) {
        try (RandomAccessFile reader = new RandomAccessFile(path, "r")) {
            String body = "";

            String line = null;
            while ((line = reader.readLine()) != null) {
                body += new String(line.getBytes("iso-8859-1"), "utf-8") + "\n";
            }

            return jsonToList(body.trim());
        } catch (IOException e) {
        }

        return new ArrayList<>();
    }

    private static List<WiseSaying> jsonToList(String json) {
        if (json.isEmpty()) {
            return null;
        }

        final String[] jsonBits = json
                .replaceAll("\\{", "")
                .replaceAll("\n", "")
                .replaceAll(" ", "")
                .split("\\},");

        List<WiseSaying> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"id\":(\\d+),\"content\":\"([가-힣]+)\",\"author\":\"([가-힣]+)\"");
        for(String bits : jsonBits) {
            Matcher matcher = pattern.matcher(bits);
            if(matcher.find())
                list.add(new WiseSaying(Integer.valueOf(matcher.group(1)), matcher.group(2), matcher.group(3)));
        }

        return list;
    }

    public static Map<String, Object> jsonToMap(String json) {
        if (json.isEmpty()) {
            return null;
        }

        final String[] jsonBits = json
                .replaceAll("\\{", "")
                .replaceAll("\\}", "")
                .split(",");

        final List<Object> bits = Stream.of(jsonBits)
                .map(String::trim)
                .flatMap(bit -> Arrays.stream(bit.split(":")))
                .map(String::trim)
                .map(s -> s.startsWith("\"") ? s.substring(1, s.length() - 1) : Integer.parseInt(s))
                .collect(Collectors.toList());

        Map<String, Object> map = IntStream
                .range(0, bits.size() / 2)
                .mapToObj(i -> Pair.of((String) bits.get(i * 2), bits.get(i * 2 + 1)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (key1, key2) -> key1, LinkedHashMap::new));

        return map;
    }

}

class Pair {
    // Return a map entry (key-value pair) from the specified values
    public static <T, U> Map.Entry<T, U> of(T first, U second) {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
}