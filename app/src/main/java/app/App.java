package app;
import java.io.InputStream;
import java.io.File;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.nio.file.StandardWatchEventKinds;
import javazoom.jl.player.*;
public class App {
    public static void main(String[] args) {
        
        //ログ保管フォルダ指定
        String userHome = System.getProperty("user.home");
        File logDir = new File(userHome, "AppData/LocalLow/VRChat/VRChat");

        //フォルダの存在チェック
        if (!logDir.exists() || !logDir.isDirectory()){
            System.out.println("ログフォルダが見つかりません" + logDir.getAbsolutePath());
            return;
        }

        //ログファイル抽出
        File[] logFiles = logDir.listFiles((dir, name) -> name.startsWith("output_log") && name.endsWith(".txt"));

        if (logFiles == null || logFiles.length == 0){
            System.out.println("ログファイルがありません。");
            return;
        }

        System.out.println("ログ監視を開始...");
        try(InputStream is = App.class.getResourceAsStream("/joinSound.mp3")){

            Player player = new Player(is);
            player.play();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}