package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.service.ServiceGererMerge;

@Controller
public class MergeController {
	
	@Autowired
	ServiceGererMerge mergeService;
	
	@RequestMapping(value = "/saveMergeLine", method = RequestMethod.POST)
	public @ResponseBody void saveMergeLine(@ModelAttribute(value = "myMerge") final Merge merge) {
		mergeService.save(merge);
	}
	
	@RequestMapping(value = "/Merge")
	public String merge() {
		return "ListMerge";
	}
	
	@RequestMapping(value = "/getAllMerge")
	public @ResponseBody ArrayList<Merge> allMerge(@RequestParam(value="all", required=false) boolean all) {
		return mergeService.findAllOrderByDateDesc(all);
	}
	@RequestMapping(value = "/ChangeEtatFait", method = RequestMethod.POST)
	public @ResponseBody boolean changeEtatFait(@RequestParam(value="id", required=true) int id) {
		return mergeService.modifierMergeFait(id);
	}
	
}
