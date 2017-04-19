package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.RenommeBranche;
import com.soprasteria.asp.gestionEvol.model.SupprimeBranche;
import com.soprasteria.asp.gestionEvol.service.ServiceGererBranche;

@Controller
public class MergeController {
	
	String TYPE_ACTION_RENOMME = "renommeBranche";
	String TYPE_ACTION_SUPPRIME = "supprimeBranche";

	
	@Autowired
	ServiceGererBranche brancheService;
	
	@RequestMapping(value = "/saveMergeLine", method = RequestMethod.POST)
	public @ResponseBody int saveMergeLine(@ModelAttribute(value = "myMerge") final Merge merge) {
		return brancheService.save(merge).getId();
	}
	
	@RequestMapping(value = "/Merge")
	public String merge() {
		return "ListMerge";
	}
	
	@RequestMapping(value = "/getAllMerge")
	public @ResponseBody ArrayList<Merge> allMerge(@RequestParam(value="all", required=false) boolean all) {
		return brancheService.findAllMergeOrderByDateDesc(all);
	}

	@RequestMapping(value = "/ChangeEtatFait/{typeaction}", method = RequestMethod.POST)
	public @ResponseBody boolean changeEtatFait(@RequestParam(value="id", required=true) int id,@PathVariable("typeaction") String typeaction) {
		if(typeaction.equals(TYPE_ACTION_RENOMME))
		{
			return brancheService.modifierRenommeFait(id);
		}else if(typeaction.equals(TYPE_ACTION_SUPPRIME)){
			return brancheService.modifierSupprimeFait(id);
		}else{
			return brancheService.modifierMergeFait(id);
		}
	}
	@RequestMapping(value = "/Renommer")
	public String renommer() {
		return "ListRenommer";
	}
	@RequestMapping(value = "/GetAllRenommeBranche")
	public @ResponseBody ArrayList<RenommeBranche> getAllRenommeBranche(@RequestParam(value="all", required=false) boolean all) {
		return brancheService.findAllRenommeOrderByDateDesc();
	}
	@RequestMapping(value = "/SaveRenommeBrancheLine")
	public @ResponseBody int saveRenommeBrancheLine(@ModelAttribute(value = "myRenommeBranche") final RenommeBranche renommeBranche) {
		return brancheService.save(renommeBranche).getId();
	}
	
	@RequestMapping(value = "/Supprimer")
	public String supprimer() {
		return "ListSupprimer";
	}
	
	@RequestMapping(value = "/GetAllSupprimeBranche")
	public @ResponseBody ArrayList<SupprimeBranche> getAllSupprimeBranche(@RequestParam(value="all", required=false) boolean all) {
		return brancheService.findAllSupprimeOrderByDateDesc();
	}
	
	@RequestMapping(value = "/SaveSupprimeBrancheLine")
	public @ResponseBody int saveSupprimeBrancheLine(@ModelAttribute(value = "mySupprimeBranche") final SupprimeBranche supprimeBranche) {
		return brancheService.save(supprimeBranche).getId();
	}
	
}
