package com.formation.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.entity.LigneDeCommande;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestFind {

	public static void main(String[] args) {
		
		int entreeTest = 10;
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("eboutique");
		MongoCollection<Document> coll = db.getCollection("commandes");
		ObjectMapper mapper = new ObjectMapper();
		/*
		Document ligneARecuperer = Document.parse("{_id:" + entreeTest +"}");
		//Document ligneARecuperer = Document.parse("{_id:{$gt:2,$lt:10}}");
		//Document ligneARecuperer = new Document().append("_id", entreeTest);
		MongoCursor<Document> cursor = coll.find(ligneARecuperer).iterator();
		Document doc = null;
		while (cursor.hasNext())
		{
			doc = cursor.next();
			System.out.println(doc.toJson());
		}
		String docAConvertir = doc.toJson().replace("_id", "id");
		System.out.println(docAConvertir);
		
		LigneDeCommande ligne1 = null;
		try {
			ligne1 = mapper.readValue(docAConvertir, LigneDeCommande.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ligne1);
		*/
		
		//****************************************************** test liste d'éléments
		//Document ligneARecuperer2 = Document.parse("{$match:{'category':\"Sport\"}}");
		//Document ligneARecuperer2 = new Document().append("category", "Concert");
		//Document ligneARecuperer2 = Document.parse("{\"price\":{$gt:" + 8 + ",$lt:"+ 10 +"}}");
		/*Document ligneARecuperer2 = Document.parse("{_id:" + 2 + "}");
		List<Document> operations = new ArrayList<>();
		operations.add(ligneARecuperer2);
		
		AggregateIterable<Document> doc2 = coll.aggregate(operations);
		//doc2.forEach();
		List<LigneDeCommande> laListeDeCommande = new ArrayList<>() ;
		
		for (Document lc : doc2)
		{
			try {
				//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				String docAConvertir = lc.toJson().replace("_id", "id");
				LigneDeCommande ligne = mapper.readValue(docAConvertir, LigneDeCommande.class);
				laListeDeCommande.add(ligne);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (LigneDeCommande lc : laListeDeCommande)
			System.out.println(lc); */
		
		
		//************************************************************************
		/*Document ligneARecuperer2 = Document.parse("{$match:{price:{$gt:" + 15 + ",$lt:"+ 25 +"}}}");
		// Document ligneARecuperer2 = new Document().append("category",
		// "Concert");
		List<Document> operations = new ArrayList<>();
		operations.add(ligneARecuperer2);

		AggregateIterable<Document> doc2 = coll.aggregate(operations);
		// doc2.forEach();
		List<LigneDeCommande> laListeDeCommande = new ArrayList<>();

		for (Document lc : doc2) {
			try {
				// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				// false);
				String docAConvertir = lc.toJson().replace("_id", "id");
				LigneDeCommande ligne = mapper.readValue(docAConvertir, LigneDeCommande.class);
				laListeDeCommande.add(ligne);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (LigneDeCommande lc : laListeDeCommande)
			System.out.println(lc);*/
		
		//******************************************************************
		//Document ligneARecuperer = Document.parse("{sort({_id:-1}).limit(1)}"); 
		//(Document.parse("find().sort({_id:-1}).limit(1)")).iterator();
		MongoCursor<Document> cursor = coll.find().sort(new Document().parse("{_id:-1}")).limit(1).iterator();
		Document doc = null;
		while (cursor.hasNext())
		{
			doc = cursor.next();
			System.out.println(doc.toJson());
		}
		String docAConvertir = doc.toJson().replace("_id", "id");
		System.out.println(docAConvertir);
		
		LigneDeCommande ligne1 = null;
		try {
			ligne1 = mapper.readValue(docAConvertir, LigneDeCommande.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ligne1.getId());
	}

}
