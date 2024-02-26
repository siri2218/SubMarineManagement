package com.submarine.jacksparrowtask.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.submarine.jacksparrowtask.model.SubMarine;

import jakarta.annotation.PostConstruct;

@Controller
public class ControlRoomController {

	private List<SubMarine> submarines = new ArrayList<>();

	private PubNub pubNub;

	public String showControlRoom(Model model) {
		model.addAttribute("submarines", submarines);
		return "controlRoom";
	}

	@PostConstruct
	public void registerPubNub() throws PubNubException {
		UserId userId = new UserId("1234");
		PNConfiguration pnConfiguration = new PNConfiguration(userId);
		pnConfiguration.setSubscribeKey("pub-c-931f3cfd-f7e6-4041-a048-fdcd8157dcf3");
		pnConfiguration.setPublishKey("sub-c-dc9b7878-2a23-11e8-ae0d-3a0cea6ae1c4");
		pubNub = new PubNub(pnConfiguration);
		pubNub.subscribe().channels(new ArrayList<>(List.of("submarines_channel"))).execute();
	}

	@PostMapping("/registerSubmarine")
	public String registerSubmarine(String name, Model model) { 
		// Check if the submarine name is already taken
		boolean nameExists = submarines.stream().anyMatch(submarine -> submarine.getName().equals(name));
		if (nameExists) {
			publishNameAlreadyExists(name, model);
			return "redirect:/controlRoom";
		}

		// If not taken, add the submarine submarines.add(new SubMarine(name));
		return "redirect:/controlRoom";
	}

	private void publishNameAlreadyExists(String name, Model model) {
		pubNub.publish().channel("submarines_channel").message("NameAlreadyExists").async((result, status) -> {
			model.addAttribute("error", name);
		});
	}

	@PostMapping("/hideSubmarine")
	public String hideSubmarine(String name) {
		submarines.stream().filter(submarine -> submarine.getName().equals(name)).findFirst().ifPresent(submarine -> {
			submarine.setHidden(true);
			publishRequestToHideSubmarine(name);
		});

		return "redirect:/controlRoom";
	}

	private void publishRequestToHideSubmarine(String name) {
		pubNub.publish().channel("submarines_channel").message("HideSubmarine:" + name).async((result, status) -> {
			System.out.println("Hidden SubMarines");
		});
	}

}
