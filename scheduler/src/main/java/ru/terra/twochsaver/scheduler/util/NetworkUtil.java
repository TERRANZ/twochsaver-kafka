package ru.terra.twochsaver.scheduler.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import ru.terra.twochsaver.scheduler.exception.UrlNotFoundException;
import ru.terra.twochsaver.shared.dto.TwochFileDto;
import ru.terra.twochsaver.shared.dto.TwochThreadDto;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class NetworkUtil {
    public static final String BASE_URL = "https://2ch.hk";
    public static final String MAKABA_URL = BASE_URL + "/makaba";

    public static List<TwochFileDto> loadFilesForThread(final Integer thread, final String board) throws IOException, UrlNotFoundException {
        final HttpsURLConnection conn = (HttpsURLConnection) new URL(buildUrl(thread, board)).openConnection();
        conn.setConnectTimeout(10000);

        final ObjectMapper mapper = new ObjectMapper();
        if (conn.getResponseCode() != 200) {
            throw new UrlNotFoundException();
        }
        final String json = readStreamToJsonString(conn.getInputStream());
        try {
            final TwochThreadDto[] readedThread = mapper.readValue(json, TwochThreadDto[].class);


            final List<TwochFileDto> result = new ArrayList<>();

            Arrays.stream(readedThread).distinct().forEach(thr -> {
                result.addAll(thr.getFiles());
            });

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String buildUrl(final Integer thread, final String board) {
        return MAKABA_URL + "/mobile.fcgi?task=get_thread&board="
                + board
                + "&thread="
                + thread.toString()
                + "&num="
                + thread.toString();
    }

    public static String readStreamToJsonString(final InputStream input) throws IOException {
        try (final BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
}
