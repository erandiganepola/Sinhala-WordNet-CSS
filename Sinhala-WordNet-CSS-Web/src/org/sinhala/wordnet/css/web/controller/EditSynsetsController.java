package org.sinhala.wordnet.css.web.controller;

import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.utils.maduraapi.MeaningRequestHandler;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/EditSynsets")
public class EditSynsetsController {

	@RequestMapping(method = RequestMethod.GET, params = {
			"action=ShowEditSynset", "type=noun" })
	public String showEditSynset(
			@RequestParam(value = "id", required = false) String id,
			ModelMap model,
			@RequestParam(value = "type", required = false) String type) {

		if (id != null && !"".equals(id)) {
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SinhalaWordNetSynset castSynset = new NounSynset(synset);

			// override cast synset with database values here

			MeaningRequestHandler meaningRequestHandler = new MeaningRequestHandler();
			List<String> wordList = castSynset.getWordArrayList();
			List<List<String>> meaningsList = meaningRequestHandler
					.getMeaningLists(wordList);
			List<String> intersection = meaningRequestHandler
					.getIntersection(meaningsList);

			model.addAttribute("synset", castSynset);
			model.addAttribute("meaningsList", meaningsList);
			model.addAttribute("intersection", intersection);
			model.addAttribute("wordList", wordList);
			model.addAttribute("type", type);

			return "EditSynset";
		}

		else {
			return "error";
		}
	}

<<<<<<< HEAD
	@RequestMapping(method = RequestMethod.POST)
	public String editSynset(@ModelAttribute("synset") SinhalaWordNetSynset synset, ModelMap model) {
		if ("noun".equals(synset.getType())) {
			model.addAttribute("synset", synset);
			return showEditSynset(String.valueOf(synset.getOffset()), model, "noun");
		}
		return "error";
=======
	@RequestMapping(value = "synset",method = RequestMethod.POST)
	public String editSynset(@ModelAttribute SinhalaWordNetSynset synset,
			ModelMap model) {
		//System.out.println(synset.getOffset());
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.addNounSynset((NounSynset)synset);
		
		model.addAttribute("synset", synset);
		
		
		return showEditSynset(String.valueOf(synset.getOffset()) , model, "noun");
>>>>>>> f8c88ddfdda248acf9d2bc4b47b5f5903fe65c90
	}

}
