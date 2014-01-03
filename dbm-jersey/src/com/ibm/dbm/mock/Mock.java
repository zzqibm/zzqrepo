package com.ibm.dbm.mock;

import java.util.ArrayList;
import java.util.List;

import com.ibm.dbm.bean.Image;
import com.ibm.dbm.bean.server.Card;
import com.ibm.dbm.bean.server.CardStatus;
import com.ibm.dbm.bean.server.CardType;
import com.ibm.dbm.bean.server.Server;
import com.ibm.dbm.bean.server.Value;

public class Mock {
	
	public static Server getServer(Server server) {
		Server mockServer = server;
		
		if (mockServer == null) {
			mockServer = new Server();
			mockServer.setId(1451);
			mockServer.setName("Mock Server 1");
			mockServer.setHmcIp("172.16.51.23");
			mockServer.setHmcUser("hscroot");
		}
		
		mockServer.setAvailableCPU(new Value("5", "core"));
		mockServer.setAvailableMemory(new Value("64.3", "GB"));
		mockServer.setEthernetCards(getEthCards());
		mockServer.setHbaCards(getHbaCards());
		
		return mockServer;
	}

	public static List<Card> getEthCards() {
		List<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card("0", "Eth0", "Ethernet 0", "1", "VIOS", CardType.Ethernet, CardStatus.USERD));
		cards.add(new Card("1", "Eth1", "Ethernet 1", "6", "LPAR-6", CardType.Ethernet, CardStatus.USERD));
		cards.add(new Card("2", "Eth2", "Ethernet 2", "7", "LPAR-7", CardType.Ethernet, CardStatus.USERD));
		cards.add(new Card("3", "Eth3", "Ethernet 3", "", "", CardType.Ethernet, CardStatus.FREE));
		
		return cards;
	}
	
	public static List<Card> getHbaCards() {
		List<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card("0", "fcs0", "Fibre Channel Card 0", "1", "VIOS", CardType.HBA, CardStatus.USERD));
		cards.add(new Card("1", "fcs1", "Fibre Channel Card 1", "", "", CardType.HBA, CardStatus.FREE));
		
		return cards;
	}
	
	public static List<Image> getImages() {
		List<Image> images = new ArrayList<Image>();
		images.add(new Image("SUSE - Single Nic - 2.4"));
		images.add(new Image("SUSE - Double Nic - 2.4"));
		images.add(new Image("RHEL - Single Nic - 5.3"));
		images.add(new Image("RHEL - Double Nic - 5.3"));
		images.add(new Image("AIX - Single Nic - 7.1"));
		images.add(new Image("AIX - Double Nic - 7.1"));
		
		return images;
	}
	
}
