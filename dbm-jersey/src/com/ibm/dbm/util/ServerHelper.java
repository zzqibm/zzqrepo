package com.ibm.dbm.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.dbm.bean.server.Card;
import com.ibm.dbm.bean.server.CardStatus;
import com.ibm.dbm.bean.server.CardType;
import com.ibm.dbm.bean.server.HbaCard;
import com.ibm.dbm.bean.server.Server;
import com.ibm.dbm.bean.server.Value;


public class ServerHelper {
	PropertiesSingleton ps = PropertiesSingleton.getInstance();
	String logFormDir        = ps.getPropertyValue(Constants.LOG_FROM_DIR);
	String logToDir          = ps.getPropertyValue(Constants.LOG_TO_DIR);
	String wasServerUsername = ps.getPropertyValue(Constants.WAS_SERVER_USERNAME);
	String wasServerPassword = ps.getPropertyValue(Constants.WAS_SERVER_PASSWORD);
	String wasServerIp       = ps.getPropertyValue(Constants.WAS_SERVER_IP);
	
	public Server getServer(Server server) {
		String space = " ";
		
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		int result = -1;
		
		String command = Constants.SH_CHECK_AVAIL_CPU_MEM + space + server.getName() + space + server.getHmcUser() + space + server.getHmcIp();
		System.out.println(command);
		rsi.setCommand(command);
		
		try {
			result = rsi.exec();
			String cpuMemory = rsi.getReturnMsg().trim();
			String[] cm = cpuMemory.split("-");
			String cpu = cm[0];
			int memory = 0;
			try{
				memory = (Integer.parseInt(cm[1]) / 1024);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			server.setAvailableCPU(new Value(cpu, "core"));
			server.setAvailableMemory(new Value(memory, "GB"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--------check_avail_sys_proc_mem.sh Result:"+ result + " [serverId:" + server.getId() + ",serverName:" + server.getName() +"]");
		
		server.setEthernetCards(getEthCards(server));
		server.setHbaCards(getHbaCards(server));
		
		return server;
	}
	
	public List<Card> getEthCards(Server server){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = sdf.format(new Date());
		
		String space = " ";
		String logFileSuffix = server.getId() + "_" + uuid;
		
		//ls all eth card.
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		int result = -1;
		
		String command = Constants.SH_LS_IO_ETH + space + server.getName() + space + server.getHmcUser() + space + server.getHmcIp() +" >& "+ logFormDir +"IO_ETH_LS_"+ logFileSuffix;
		System.out.println(command);
		rsi.setCommand(command);
		
		try {
			result = rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--------ls_io_eth.sh Result:"+ result + " [serverId:" + server.getId() + ",serverName:" + server.getName() +"]");
		
		String getFileCommand = "dbmftplog.sh "+ wasServerIp + space + logFormDir + space + logToDir +" IO_ETH_LS_"+ logFileSuffix + space + wasServerUsername + space + wasServerPassword;
		System.out.println(getFileCommand);
		rsi.setCommand(getFileCommand);
		try {
			rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String eth = FileUtil.readFileByLines(logToDir +"IO_ETH_LS_"+ logFileSuffix);
		
		List<Card> cardList = getCardList(eth, server.getId(), CardType.Ethernet);
		cardList = refreshEthernetCardState(server, cardList);
		
		return cardList;
	}
	
	
	public List<Card> getHbaCards(Server server) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = sdf.format(new Date());
		
		String space = " ";
		String logFileSuffix = server.getId() + "_" + uuid;
		
		//ls all hba card.
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		int result = -1;
		
		String command = Constants.SH_LS_IO_HBA + space + server.getName() + space + server.getHmcUser() + space + server.getHmcIp() +" >& "+ logFormDir +"IO_HBA_LS_"+ logFileSuffix;
		System.out.println(command);
		rsi.setCommand(command);
		
		try {
			result = rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--------ls_io_hba.sh Result:"+ result + " [serverId:" + server.getId() + ",serverName:" + server.getName() +"]");
		
		String getFileCommand = "dbmftplog.sh "+ wasServerIp + space + logFormDir + space + logToDir +" IO_HBA_LS_"+ logFileSuffix + space + wasServerUsername + space + wasServerPassword;
		System.out.println(getFileCommand);
		rsi.setCommand(getFileCommand);
		try {
			rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String hba = FileUtil.readFileByLines(logToDir +"IO_HBA_LS_"+ logFileSuffix);
		
		List<Card> cardList = getCardList(hba, server.getId(), CardType.HBA);
		cardList = refreshHBACardState(server, cardList);
		
		return cardList;
	}
	
	
	private List<Card> getCardList(String msg, int serverId, CardType type){
		List<Card> cardObjs = new ArrayList<Card>();
		
		if(msg != null && msg.trim().length() > 0){
			msg = msg.substring(0, msg.lastIndexOf("~#@&"));
			System.out.println("cards information=" + msg);
			
			String[] cards = msg.split("~#@&");
			for(int i = 0; i < cards.length; i++){
				String card = cards[i];
				
				String[] drcs = card.split(",");
				if(drcs.length == 5){
					String drcIndex    = drcs[0];
					String drcName     = drcs[1];
					String description = drcs[2];
					String lparId      = drcs[3];
					String lparName    = drcs[4];
					
					CardStatus status = CardStatus.USERD; //used.
					if("none".equals(lparId)){
						lparId   = "";
						lparName = "";
						status = CardStatus.NA;//not sure have line.
					}
					
					Card cardObj = new Card();
					cardObj.setIndex(drcIndex);
					cardObj.setName(drcName);
					cardObj.setDescription(description);
					cardObj.setLparId(lparId);
					cardObj.setLparName(lparName);
					cardObj.setType(type);
					cardObj.setStatus(status);
					
					cardObjs.add(cardObj);
				}
			}
		}
		
		return cardObjs;
	}
	
	
	private List<Card> refreshEthernetCardState(Server server, List<Card> cardList) {
		//TODO how to get tempIP -> refer to TimedTaskHelper.java:L120
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = sdf.format(new Date());
		String space = " ";
		String logFileSuffix = server.getId() + "_" + uuid;
		
		//check hba state
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		int result = -1;
		String command = Constants.SH_CHECK_IO_ETH_STATE + space + server.getName() + space + server.getHmcUser() + space + server.getHmcUser() + space + tempIP +" >& "+ logFormDir +"IO_ETH_STATE_"+ logFileSuffix;
		System.out.println(command);
		rsi.setCommand(command);
		result = rsi.exec();
		
		System.out.println("-------- check_io_eth_state.sh Result:"+ result + " [serverId:" + server.getId() + ",serverName:" + server.getName() +"]");
		
		String getFileCommand = "dbmftplog.sh "+ wasServerIp + space + logFormDir + space + logToDir +" IO_ETH_STATE_"+ logFileSuffix + space + wasServerUsername + space + wasServerPassword;
		System.out.println(getFileCommand);
		rsi.setCommand(getFileCommand);
		rsi.exec();
		
		String eth = FileUtil.readFileByLines(logToDir +"IO_ETH_STATE_"+ logFileSuffix);
		
		if(eth.indexOf("time4fun") >= 0){
			eth = eth.substring(eth.indexOf("time4fun") + "time4fun~#@&".length());
		}
		
		Map<Integer, Map<String, Object>> cardStates = getCardState(eth, CardType.Ethernet);
		for (Card card : cardList) {
			Map<String, Object> cardState = cardStates.get(card.getIndex());
			if (cardState != null) {
				card.setStatus((CardStatus)cardState.get("status"));
				((HbaCard)card).setWwpn((String)cardState.get("wwpn"));
			}
		}
		*/
		return cardList;
	}
	
	
	private List<Card> refreshHBACardState(Server server, List<Card> cardList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = sdf.format(new Date());
		String space = " ";
		String logFileSuffix = server.getId() + "_" + uuid;
		
		//check hba state
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		int result = -1;
		String command = Constants.SH_CHECK_IO_HBA_STATE + space + server.getName() + space + server.getHmcUser() + space + server.getHmcIp() + " >& "+ logFormDir +"IO_HBA_STATE_"+ logFileSuffix;
		System.out.println(command);
		rsi.setCommand(command);
		try {
			result = rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("-------- check_io_hba_state.sh Result:"+ result + " [serverId:" + server.getId() + ",serverName:" + server.getName() +"]");
		
		String getFileCommand = "dbmftplog.sh "+ wasServerIp + space + logFormDir + space + logToDir +" IO_HBA_STATE_"+ logFileSuffix + space + wasServerUsername + space + wasServerPassword;
		System.out.println(getFileCommand);
		rsi.setCommand(getFileCommand);
		try {
			rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String hba = FileUtil.readFileByLines(logToDir +"IO_HBA_STATE_"+ logFileSuffix);
		
		if(hba.indexOf("time4fun") >= 0){
			hba = hba.substring(hba.indexOf("time4fun") + "time4fun~#@&".length());
		}
		
		Map<Integer, Map<String, Object>> cardStates = getCardState(hba, CardType.HBA);
		for (Card card : cardList) {
			Map<String, Object> cardState = cardStates.get(card.getIndex());
			if (cardState != null) {
				card.setStatus((CardStatus)cardState.get("status"));
				((HbaCard)card).setWwpn((String)cardState.get("wwpn"));
			}
		}
		
		return cardList;
	}
	
	
	/**
	 * 
	 * @param msg
	 * @param type
	 * @return Map<String1, Map<String2, Object>> -> String1: cardId, String2: cardParamName, Object: cardParamValue
	 */
	private Map<Integer, Map<String, Object>> getCardState(String msg, CardType type){
		Map<Integer, Map<String, Object>> cardMap = new HashMap<Integer, Map<String, Object>>();
		
		if(msg != null && msg.trim().length() > 0){
			msg = msg.substring(0, msg.lastIndexOf("~#@&"));
			System.out.println("cards information=" + msg);
			
			String[] cards = msg.split("~#@&");
			for(int i = 0; i < cards.length; i++){
				String card = cards[i];
				int index = Integer.parseInt(card.substring(0, card.indexOf(" ")));
				
				Map<String, Object> cardInfo = new HashMap<String, Object>();
				cardInfo.put("status", CardStatus.FREE);
				
				if(type != null && type.equals(CardType.HBA)){
					card = card.trim();
					String wwpn = card.substring(card.lastIndexOf(" ")+1);
					cardInfo.put("wwpn", wwpn);
				}
				
				cardMap.put(index, cardInfo);
			}
		}
		
		return cardMap;
	}
	
}
