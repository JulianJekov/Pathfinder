package org.softuni.pathfinder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUtil {
    public static String getVideoUrl(String fullVideoUrl) {

        String regex = "(?<=v=)[\\w-]{11}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullVideoUrl);

        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
