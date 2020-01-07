/**
 * 
 */
package io.arun.camel.camelrabbitmqclient.service;

/**
 * @author arun
 *
 */
public class MyService {

	public static String REPLY_MSG;
	
	public void serverReply(String reply) {
		REPLY_MSG = reply;
		System.out.println("Reply Msg:"+reply);
	}
	
}
