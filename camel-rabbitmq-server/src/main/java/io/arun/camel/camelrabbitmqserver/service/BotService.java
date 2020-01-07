package io.arun.camel.camelrabbitmqserver.service;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class BotService {

	@Produce(uri = "direct:rabbitMQServer")
	ProducerTemplate producer;
	
	private static final boolean TRACE_MODE = false;
    static String botName = "ET-BOT";
	
	public void msgProducer(String msg) {
		String reply = null;
		try {
			 
            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
 
                if ((msg == null) || (msg.length() < 1))
                    msg = MagicStrings.null_input;
                	reply = "Bye!";
                if (msg.equals("q")) {
                } else if (msg.equals("wq")) {
                    bot.writeQuit();
                    reply = "Bye!";
                } else {
                    String request = msg;
                    if (MagicBooleans.trace_mode)
                        System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    String response = chatSession.multisentenceRespond(request);
                    while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");
                    
                    reply = response;
                    
                }
                System.out.println("Client Msg: "+msg+ " Bot Reply: "+reply);
                producer.sendBody(producer.getDefaultEndpoint(), reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
