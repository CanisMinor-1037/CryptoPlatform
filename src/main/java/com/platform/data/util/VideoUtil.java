package com.platform.data.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VideoUtil {
    private static final String FFMPEG = "ffmpeg";
    private static final String I = "-i";
    private static final String SS = "-ss";
    private static final String T = "-t";
    private static final String LOGLEVEL = "-loglevel";
    private static final String QUIET = "quiet";
    private static final String INDEX_1 = "Duration: ";
    private static final String INDEX_2 = ", start:";
    private static final int SHARD_TIME = 60000;
    private static final String SHARD_TIME_STRING ="00:01:00";
    private static String basePath = "";
    public static String getBasePath() {
        return basePath;
    }
    public static void setBasePath(String basePath) {
        VideoUtil.basePath = basePath;
    }
    private static int getVideoTime(String fileName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = new ArrayList<>();
        command.add(FFMPEG);
        command.add(I);
        command.add(basePath + fileName);
        Process process = processBuilder.command(command).redirectErrorStream(true).start();
        process.waitFor();
        InputStream inputStream = process.getInputStream();
        StringBuilder info = new StringBuilder();
        while (inputStream.available() > 0) {
            Character c = new Character((char) inputStream.read());
            info.append(c);
        }
        int index1 = info.indexOf(INDEX_1);
        int index2 = info.lastIndexOf(INDEX_2);
        if(index1 != -1 && index2 != -1){
            String time = info.substring(index1 + INDEX_1.length(), index2);
            int hours = Integer.parseInt(time.split(":")[0]);
            int minutes = Integer.parseInt(time.split(":")[1]);
            int seconds = Integer.parseInt(time.split(":")[2].split("\\.")[0]);
            int milliseconds = Integer.parseInt(time.split(":")[2].split("\\.")[1]);
            int result = hours * 3600000 + minutes * 60000 + seconds * 1000 + milliseconds;
            return result;
        }
        return 0;
    }
    private static int getShardNum(int time) {
        return (int)Math.ceil((double) time / SHARD_TIME);
    }
    private static String getShardStartTime(int index) {
        int startTime = index * SHARD_TIME / 1000;
        return String.format("%02d:%02d:%02d", startTime / 3600, startTime % 3600 / 60, startTime % 60);
    }
    public static List<String> spilt(String fileName) throws IOException, InterruptedException {
        List<String> shardNames = new ArrayList<>();
        int time = getVideoTime(fileName);
        if(time == 0){
            return shardNames;
        }
        int shardNum = getShardNum(time);
        Process[] process = new Process[shardNum];
        boolean[] finished = new boolean[shardNum];
        int finishedNum = 0;
        for (int i = 0; i < shardNum; i++) {
            String shardName = "分片"+ i +"."+fileName;
            shardNames.add(shardName);
            ProcessBuilder processBuilder = new ProcessBuilder();
            List<String> command = new ArrayList<>();
            command.add(FFMPEG);
            command.add(LOGLEVEL);
            command.add(QUIET);
            command.add(SS);
            command.add(getShardStartTime(i));
            command.add(T);
            command.add(SHARD_TIME_STRING);
            command.add(I);
            command.add(basePath + fileName);
            command.add(basePath + shardName);
            for (String c:command){
                System.out.print(c+ " ");
            }
            process[i] = processBuilder.command(command).redirectErrorStream(true).start();
        }
        while (finishedNum < shardNum){
            try{
                for (int i = 0; i < shardNum; i++) {
                    process[i].exitValue();
                    if(!finished[i]){
                        finished[i] = true;
                        finishedNum++;
                    }
                }
            }
            catch (Exception e){
                Thread.sleep(100);
            }
        }
        return shardNames;
    }
}
