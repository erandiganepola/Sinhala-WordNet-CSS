package org.sinhala.wordnet.wordnetDB.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.wordnetDB.config.SpringMongoConfig;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class SynsetMongoDbHandler {

	public void addNounSynset(NounSynset nounSynset) {

		// @SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		MongoSinhalaNoun mongoNounsynset = ssmsc.converttoMongoNoun(nounSynset);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
		Date date = new Date();
		//Date formatteddate = dateFormat.format(date);
		mongoNounsynset.setDate(date);

		mongoOperation.save(mongoNounsynset);

		System.out.println("saved");

	}
	
	public void addVerbSynset(VerbSynset verbSynset) {

		// @SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		MongoSinhalaVerb mongoVerbsynset = ssmsc.converttoMongoVerb(verbSynset);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
		Date date = new Date();
		//Date formatteddate = dateFormat.format(date);
		mongoVerbsynset.setDate(date);

		mongoOperation.save(mongoVerbsynset);

		System.out.println("saved");

	}
	public void addAdjSynset(AdjectiveSynset adjSynset) {

		// @SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		MongoSinhalaAdjective mongoAdjsynset = ssmsc.converttoMongoAdj(adjSynset);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
		Date date = new Date();
		//Date formatteddate = dateFormat.format(date);
		mongoAdjsynset.setDate(date);

		mongoOperation.save(mongoAdjsynset);

		System.out.println("saved");

	}

	public void addRoot(String lemma, String userName) {

		// @SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		SinhalaSynsetMongoSynsetConvertor ssmsc = new SinhalaSynsetMongoSynsetConvertor();
		List<MongoSinhalaWord> wordList = new ArrayList<MongoSinhalaWord>();
		wordList.add(new MongoSinhalaWord(lemma, "0", null));
		MongoSinhalaRoot root = new MongoSinhalaRoot(wordList, "",userName);
		MongoSinhalaRoot anyRoot = findRootByLemma(lemma);
		if(anyRoot == null){
		mongoOperation.save(root);
		}
	}

	public void findAll() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		List<MongoSinhalaSynset> collection = mongoOperation
				.findAll(MongoSinhalaSynset.class);
		for (MongoSinhalaSynset s : collection) {

			System.out.println(s);

		}
	}
	
	public MongoSinhalaNoun findBySynsetMongoId(String findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaNoun foundSynset = null;
		List<MongoSinhalaNoun> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaNoun.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}
	public MongoSinhalaVerb findVerbByMongoSynsetId(String findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaVerb foundSynset = null;
		List<MongoSinhalaVerb> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaVerb.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}
	public MongoSinhalaAdjective findAdjByMongoSynsetId(String findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaAdjective foundSynset = null;
		List<MongoSinhalaAdjective> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaAdjective.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}
	
	public MongoSinhalaNoun findBySynsetId(Long findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("eWNId").is(findId));
		MongoSinhalaNoun foundSynset = null;
		List<MongoSinhalaNoun> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaNoun.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}
	public MongoSinhalaVerb findVerbBySynsetId(Long findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("eWNId").is(findId));
		MongoSinhalaVerb foundSynset = null;
		List<MongoSinhalaVerb> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaVerb.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}
	public MongoSinhalaAdjective findAdjBySynsetId(Long findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("eWNId").is(findId));
		MongoSinhalaAdjective foundSynset = null;
		List<MongoSinhalaAdjective> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaAdjective.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}

	public MongoSinhalaNoun findById(Long findId) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaNoun foundSynset = mongoOperation.findOne(
				searchSynsetQuery1, MongoSinhalaNoun.class);
		return foundSynset;
	}

	public MongoSinhalaSynset findBylemma(String lemma) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").is(
				lemma));
		MongoSinhalaSynset foundSynset = mongoOperation.findOne(
				searchSynsetQuery1, MongoSinhalaSynset.class);
		System.out.println(foundSynset);
		return foundSynset;
	}

	public void findRelatedSynsetById(String findId,
			MongoSinhalaPointerTyps relation) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(findId));
		MongoSinhalaSynset foundSynset = mongoOperation.findOne(
				searchSynsetQuery1, MongoSinhalaSynset.class);
		List<MongoSinhalaSencePointer> sensePointers = foundSynset
				.getSencePointers();
		for (int i = 0; i < sensePointers.size(); i++) {
			MongoSinhalaPointerTyps pointerType = sensePointers.get(i)
					.getPointerType();
			if (pointerType.equals(relation)) {
				findById(sensePointers.get(i).getSynsetId());
			}
		}
		System.out.println(foundSynset);
	}

	public void test() {

		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		String id = "7127";
		try {
			synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		NounSynset castSynset = new NounSynset(synset);
		synsetdb.addNounSynset(castSynset);

	}

	public void update(Long offset) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("eWNId").is(offset));
		MongoSinhalaNoun foundSynset = mongoOperation.findOne(
				searchSynsetQuery1, MongoSinhalaNoun.class);
		for (int i = 0; i < foundSynset.getWords().size(); i++) {
			foundSynset.getWords().get(i).SetLemma("සිංහල පද" + i);
			System.out.println("lemmaaa  -- "
					+ foundSynset.getWords().get(i).getLemma());

		}
		mongoOperation.save(foundSynset);

	}

	public MongoSinhalaRoot findRootByLemma(String lemma) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		MongoSinhalaRoot foundSynset = null;
		List<MongoSinhalaRoot> collection = mongoOperation
				.findAll(MongoSinhalaRoot.class);
		List<MongoSinhalaRoot> returnList = new ArrayList<MongoSinhalaRoot>();

		try {
			for (MongoSinhalaRoot root : collection) {
				if (root.getWords().get(0).getLemma().equals(lemma)) {
					returnList.add(root);
				}
			}
		} catch (NullPointerException e) {
			// just to ignore errors
		}

		if (returnList.size() > 0) {
			foundSynset = returnList.get(returnList.size() - 1);
		}
		return foundSynset;
	}

	public MongoSinhalaRoot findRootByID(String id) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Query searchSynsetQuery1 = new Query(Criteria.where("_id").is(id));
		MongoSinhalaRoot foundSynset = null;
		List<MongoSinhalaRoot> collection = mongoOperation.find(
				searchSynsetQuery1, MongoSinhalaRoot.class);
		if (collection.size() > 0) {
			foundSynset = collection.get(collection.size() - 1);
		}
		return foundSynset;
	}

	public List<MongoSinhalaRoot> findAllRoots() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		List<MongoSinhalaRoot> collection = mongoOperation
				.findAll(MongoSinhalaRoot.class);

		return collection;
	}
	public List<MongoSinhalaNoun> findAllNotEvaluatedNoun() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").ne(true));
		List<MongoSinhalaNoun> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaNoun.class);
		
		return collection;
	}
	public List<MongoSinhalaNoun> findAllEvaluatedNoun() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").is(true));
		List<MongoSinhalaNoun> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaNoun.class);
		
		return collection;
	}
	public List<MongoSinhalaNoun> findAllEditedNoun() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		
		List<MongoSinhalaNoun> collection = mongoOperation
				.findAll(MongoSinhalaNoun.class);
		
		return collection;
	}
	
	public List<MongoSinhalaVerb> findAllNotEvaluatedVerb() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").ne(true));
		List<MongoSinhalaVerb> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaVerb.class);
		
		return collection;
	}
	public List<MongoSinhalaVerb> findAllEvaluatedVerb() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").is(true));
		List<MongoSinhalaVerb> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaVerb.class);
		
		return collection;
	}
	public List<MongoSinhalaVerb> findAlleditedVerb() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		List<MongoSinhalaVerb> collection = mongoOperation
				.findAll(MongoSinhalaVerb.class);
		
		return collection;
	}
	
	public List<MongoSinhalaAdjective> findAllNotEvaluatedAdj() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").ne(true));

		List<MongoSinhalaAdjective> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaAdjective.class);
		
		return collection;
	}
	public List<MongoSinhalaAdjective> findAllEvaluatedAdj() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("evaluated").is(true));
		List<MongoSinhalaAdjective> collection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaAdjective.class);
		
		return collection;
	}
	
	
	
	
	public List<MongoSinhalaAdjective> findAllEditedAdj() {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		
		List<MongoSinhalaAdjective> collection = mongoOperation
				.findAll(MongoSinhalaAdjective.class);
		
		return collection;
	}
	
	
	public List<MongoSinhalaNoun> findNounSynsetByLemma(String word, POS pos){
		List<MongoSinhalaSynset> collection;
		List<MongoSinhalaNoun> nounCollection = null;
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").regex(word));
		if(pos.equals(POS.NOUN)){
			collection = new ArrayList<MongoSinhalaSynset>();
			nounCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaNoun.class);
		}
		
		return nounCollection;
	}
	
	public Collection<MongoSinhalaVerb> findVerbSynsetByLemma(String word, POS pos){
		List<MongoSinhalaSynset> collection;
		List<MongoSinhalaVerb> verbCollection = null;
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		
		
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").regex(word));
		if(pos.equals(POS.VERB)){
			collection = new ArrayList<MongoSinhalaSynset>();
			Criteria cr = new Criteria(word);
			GroupBy gb = new GroupBy("EWNID");
			//verbCollection = mongoOperation.group(c, "id", gb, MongoSinhalaVerb.class)
			verbCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaVerb.class);
		}
		HashMap<Long,MongoSinhalaVerb> hm = new HashMap<Long,MongoSinhalaVerb>();
		for (MongoSinhalaVerb s : verbCollection) {

			
			hm.put(s.getEWNId(), s);

		}
		
		Collection<MongoSinhalaVerb> newverbCollection = hm.values();
		 
		
		return newverbCollection;
	}
	
	public Collection<MongoSinhalaAdjective> findAdjSynsetByLemma(String word, POS pos){
		List<MongoSinhalaSynset> collection;
		List<MongoSinhalaAdjective> adjCollection = null;
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").regex(word));
		if(pos.equals(POS.ADJECTIVE)){
			collection = new ArrayList<MongoSinhalaSynset>();
			adjCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaAdjective.class);
		}
		
		HashMap<Long,MongoSinhalaAdjective> hm = new HashMap<Long,MongoSinhalaAdjective>();
		for (MongoSinhalaAdjective s : adjCollection) {

			
			hm.put(s.getEWNId(), s);

		}
		
		Collection<MongoSinhalaAdjective> newadjCollection = hm.values();
		return newadjCollection;
	}
	
	public Collection<Long> findSynsetIDByLemma(String word, POS pos){
		
		Collection<Long> ewnidList = new ArrayList<Long>();
		
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		Query searchSynsetQuery1 = new Query(Criteria.where("words.lemma").regex(word));
		if(pos.equals(POS.NOUN)){
			List<MongoSinhalaNoun> nounCollection = null;
			
			nounCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaNoun.class);
			
			HashMap<Long,Long> hm = new HashMap<Long,Long>();
			for (MongoSinhalaNoun s : nounCollection) {	
				hm.put(s.getEWNId(), s.getEWNId());
				}
			
			ewnidList = hm.values();
			
		}
		if(pos.equals(POS.VERB)){
			List<MongoSinhalaVerb> verbCollection = null;
			
			verbCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaVerb.class);
			
			HashMap<Long,Long> hm = new HashMap<Long,Long>();
			for (MongoSinhalaVerb s : verbCollection) {	
				hm.put(s.getEWNId(), s.getEWNId());
				}
			
			ewnidList = hm.values();
		}
		if(pos.equals(POS.ADJECTIVE)){
			List<MongoSinhalaAdjective> adjCollection = null;
			
			adjCollection = mongoOperation
				.find(searchSynsetQuery1,MongoSinhalaAdjective.class);
			
			HashMap<Long,Long> hm = new HashMap<Long,Long>();
			for (MongoSinhalaAdjective s : adjCollection) {	
				hm.put(s.getEWNId(), s.getEWNId());
				}
			
			ewnidList = hm.values();
			
		}
		
		
		
		return ewnidList;
	}
	
	
	
}
