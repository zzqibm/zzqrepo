package com.ibm.dbm.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.dbm.bean.server.Card;
import com.ibm.dbm.bean.server.CardType;
import com.ibm.dbm.bean.server.Server;
import com.ibm.dbm.mock.Mock;
import com.ibm.dbm.util.ServerHelper;

@Path("servers")
public class ServerResource {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Server getServer(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("hmcUser") String hmcUser, @QueryParam("hmcIp") String hmcIp, @QueryParam("mock") boolean mock) {
		Server server = new Server();
		server.setId(id);
		server.setName(name);
		server.setHmcIp(hmcIp);
		server.setHmcUser(hmcUser);
		
		ServerHelper helper = new ServerHelper();
		server = mock ? Mock.getServer(server) : helper.getServer(server);
		
		return server;
	}
	
	@GET
	@Path("{id}/cards")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<CardType, List<Card>> getCards(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("hmcUser") String hmcUser, @QueryParam("hmcIp") String hmcIp, @QueryParam("mock") boolean mock) {
		Server server = new Server();
		server.setId(id);
		server.setName(name);
		server.setHmcIp(hmcIp);
		server.setHmcUser(hmcUser);
		
		ServerHelper helper = new ServerHelper();
		List<Card> ethCards = mock ? Mock.getEthCards() : helper.getEthCards(server);
		List<Card> hbaCards = mock ? Mock.getHbaCards() : helper.getHbaCards(server);
		
		Map<CardType, List<Card>> cards = new HashMap<CardType, List<Card>>();
		cards.put(CardType.Ethernet, ethCards);
		cards.put(CardType.HBA, hbaCards);
		
		return cards;
	}
	
	@GET
	@Path("{id}/cards/ethernet")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getEtherCards(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("hmcUser") String hmcUser, @QueryParam("hmcIp") String hmcIp, @QueryParam("mock") boolean mock) {
		Server server = new Server();
		server.setId(id);
		server.setName(name);
		server.setHmcIp(hmcIp);
		server.setHmcUser(hmcUser);
		
		ServerHelper helper = new ServerHelper();
		List<Card> ethCards = mock ? Mock.getEthCards() : helper.getEthCards(server);
		
		return ethCards;
	}
	
	@GET
	@Path("{id}/cards/hba")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getHbaCards(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("hmcUser") String hmcUser, @QueryParam("hmcIp") String hmcIp, @QueryParam("mock") boolean mock) {
		Server server = new Server();
		server.setId(id);
		server.setName(name);
		server.setHmcIp(hmcIp);
		server.setHmcUser(hmcUser);
		
		ServerHelper helper = new ServerHelper();
		List<Card> hbaCards = mock ? Mock.getHbaCards() : helper.getHbaCards(server);
		
		return hbaCards;
	}
	
}
