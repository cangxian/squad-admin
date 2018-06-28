import com.ibasco.agql.protocols.valve.source.query.SourceRconAuthStatus;
import com.ibasco.agql.protocols.valve.source.query.client.SourceRconClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @author: liangguoqiang
 * @description: ${description}
 * @create: 2018-06-28 23:05
 **/
public class RconTest {

    @Test
    public void t() throws Exception{
        InetSocketAddress address = new InetSocketAddress("squad.cxlgq.com", 21114);
        SourceRconClient sourceRconClient = new SourceRconClient();
        SourceRconAuthStatus authStatus = sourceRconClient.authenticate(address, "cangxian").join();
        if (!authStatus.isAuthenticated()) {
            System.out.println(authStatus.getReason());
        } else {
            CompletableFuture<String> resultFuture = sourceRconClient.execute(address, "").whenComplete(this::handleResponse);
            String res = resultFuture.get();
            System.out.println(res);
        }
    }

    private void handleResponse(String response, Throwable error) {
        if (error != null) {
            System.out.println(error.getMessage());
            return;
        }
    }
}
