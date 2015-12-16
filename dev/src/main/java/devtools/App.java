package devtools;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 网络授时
 *
 */

/**
 * Java 版本网络授时 设置windows系统时间
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {


        //1.cn.pool.ntp.org

        NTPUDPClient timeClient = new NTPUDPClient();
        String timeServerUrl = "2.asia.pool.ntp.org";
        InetAddress timeServerAddress = InetAddress.getByName(timeServerUrl);
        TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
        TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(dateFormat.format(timeStamp.getDate()));

        Process datePro = Runtime.getRuntime().exec("cmd /c date " + dateFormat.format(timeStamp.getDate()));
        if (datePro.waitFor() == 0){
            System.out.println("设置日期成功");
        }else{
            System.out.println("设置日期失败");
        }


        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(timeFormat.format(timeInfo.getMessage().getTransmitTimeStamp().getDate()));
        Process timePro = Runtime.getRuntime().exec("cmd /c time " + timeFormat.format(timeStamp.getDate()));
        if (timePro.waitFor() == 0){
            System.out.println("设置时间成功");
        }else{
            System.out.println("设置时间失败");
        }
    }
}
