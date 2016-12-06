package com.formation.dao.mongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.dao.AbstractDAO;
import com.formation.entity.LigneDeCommande;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Repository
public class MongoLigneDeCommandeDao extends AbstractDAO implements LigneDeCommandeDao {

	MongoCollection<Document> coll = null;
	ObjectMapper mapper = new ObjectMapper();

	public MongoLigneDeCommandeDao() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("eboutique");
		coll = db.getCollection("commandes");
		setDbCollection(coll);
	}

	@Override
	public LigneDeCommande createLigneDeCommande(LigneDeCommande lignedeCommande) {
		Document doc = new Document("_id",lignedeCommande.getId()).append("category", lignedeCommande.getCategory())
				.append("theme", lignedeCommande.getTheme()).append("product", lignedeCommande.getProduct())
				.append("createYear", lignedeCommande.getCreateYear())
				.append("createMonth", lignedeCommande.getCreateMonth()).append("price", lignedeCommande.getPrice())
				.append("quantity", lignedeCommande.getQuantity());
		coll.insertOne(doc);
		return lignedeCommande;
	}

	@Override
	public LigneDeCommande findById(Long id_commande) {
		// db.commandes.find({_id:2})
		Document ligneARecuperer = Document.parse("{_id:" + id_commande + "}");
		MongoCursor<Document> cursor = coll.find(ligneARecuperer).iterator();
		Document doc = null;
		while (cursor.hasNext()) {
			doc = cursor.next();
			System.out.println(doc.toJson());
		}

		String docAConvertir = doc.toJson().replace("_id", "id");
		System.out.println(docAConvertir);

		// ****************************************** Retransformation en lignes
		// de commande
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

		return ligne1;
	}

	@Override
	public List getVentesParCategory(String category) {
		// ({category:"cinema"})
		Document ligneARecuperer2 = Document.parse("{$match:{'category':" + category + "}}");
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
			System.out.println(lc);

		return laListeDeCommande;
	}

	@Override
	public List getAllLignesDeCommandes() {
		FindIterable<Document> docs = coll.find();
		List<LigneDeCommande> lesCommandes = new ArrayList<>();
		for (Document doc : docs) {
			String docAConvertir = doc.toJson().replace("_id", "id");
			LigneDeCommande ligne;
			try {
				ligne = mapper.readValue(docAConvertir, LigneDeCommande.class);
				lesCommandes.add(ligne);
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
		return lesCommandes;
	}

	@Override
	public List getLignesDeCommandeParTranche(double prixMin, double prixMax) {
		// ({price:{$lt:10,$gt:7}})
		Document ligneARecuperer2 = Document.parse("{$match:{price:{$gt:" + prixMin + ",$lt:" + prixMax + "}}}");
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
			System.out.println(lc);

		return laListeDeCommande;
	}

	@Override
	public List getLignesDeCommandeParTheme(String theme) {
		// ({category:"cinema"})
		Document ligneARecuperer2 = Document.parse("{$match:{'theme':" + theme + "}}");
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
			System.out.println(lc);

		return laListeDeCommande;
	}

	public int getIdMax() {
		/*MongoCursor<Document> cursor = coll.find().sort(new Document().parse("{_id:-1}")).limit(1).iterator();
		Document doc = null;
		while (cursor.hasNext())
		{
			doc = cursor.next();
			System.out.println(doc.toJson());
		}
		String docAConvertir = doc.toJson().replace("_id", "id");
		System.out.println(docAConvertir);
		String[] splittext = docAConvertir.split(":");
		System.out.println(splittext[1]);
		String[] splittext2 = splittext[1].split(",");
		//String docAConvertir = doc.toJson();
		int idMax = Integer.parseInt(splittext2[1].trim());
		System.out.println(idMax);
		
		/*LigneDeCommande ligne1 = null;
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
		System.out.println(ligne1);*/
		long doc = coll.count();
		return (int)doc;
	}
}
