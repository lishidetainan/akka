package com.wu.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MasterActor extends UntypedActor {

	@Override
	public void onReceive(Object mess) throws Exception {
		System.out.println("获取的网页的长度是===" + mess);
	}
	
	/*发送消息*/
	public static void send(Object mess){
		ActorSystem systemWork = ActorSystem.create("master");
		ActorRef masteractorRef = systemWork.actorOf(Props.create(MasterActor.class), "master");
		Inbox inboxWork = Inbox.create(systemWork);
		inboxWork.send(masteractorRef, mess);
	}
}
