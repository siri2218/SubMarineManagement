package com.submarine.jacksparrowtask.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.submarine.jacksparrowtask.model.SubMarine;

@Controller
public class SubmarineController {
	
	private List<SubMarine> submarinelist = new ArrayList<>();

	@RequestMapping("/registerSubmarine")
	public String registerSubMarine(@RequestParam("name") String name, Model model) {

		for (SubMarine submarine : submarinelist) {
			if (submarine.getName().equals(name)) {
				showNameAlreadyInUse(model);
				return "submarielist";
			}
		}
		submarinelist.add(new SubMarine());
		return submarineRegisteredSuccessfully(model, name);

	}

	public String showNameAlreadyInUse(Model model) {
		return submarineRegistrationFailed(model, "Sub Marine name already taken. Please provide new name.");
	}

	private String submarineRegistrationFailed(Model model, String string) {
		model.addAttribute("error", string);
		return "submarielist";
	}

	public String submarineRegisteredSuccessfully(Model model, String name) {

		model.addAttribute("message", "SubMarine" + name + "successully registered");
		return "redirect:/submarinelist";
	}

	public String displayHiddenSubMarineControls(Model model, String name) {
		model.addAttribute("subMarineName", name);
		return "hiddenSubMarineControls";
	}

	@RequestMapping("/hideSubmarine")
	public String hideSubMarine(@RequestParam("name") String name, Model model) {
		for (SubMarine submarine : submarinelist) {
			if (submarine.getName().equals(name)) {
				submarine.setHidden(true);
				removeSubMarineFromList(name);
				break;
			}
		}
		return "redirect:/submarinelist";
	}

	public void removeSubMarineFromList(String name) {
		submarinelist.removeIf(submarine -> submarine.getName().equals(name));
	}

	public String submarineHiddenSuccessfully(Model model, String name) {
		model.addAttribute("message", "SubMarine" + name + "Successfully hidden");
		return "redirect:/submarinelist";
	}
	 
	@RequestMapping("subMarine")
	public String subMarine() { //
		
		return "subMarine.jsp";
	}

}
