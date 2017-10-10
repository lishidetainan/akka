package com.wu.akka;

import java.util.Arrays;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

public class AkkaTest {

	public static void main(String[] args) {
		List<String> urls = Arrays.asList("https://baidu.com","http://www.csdn.com");
		ActorSystem systemWork = ActorSystem.create("work");
		ActorRef workactorRef = systemWork.actorOf(Props.create(WorkActor.class), "work");
		Inbox inboxWork = Inbox.create(systemWork);
		// 发送任务
		inboxWork.send(workactorRef, urls);
	}

}
