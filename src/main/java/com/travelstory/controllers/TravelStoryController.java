package com.travelstory.controllers;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.services.TravelStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.travelstory.entity.TravelStory;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/travelStory")
public class TravelStoryController {
    @Autowired
    TravelStoryService tss;

    @PutMapping("/add")
    public TravelStoryDTO addTravelStory(@Valid @RequestBody TravelStoryDTO travelStory) {
        return (tss.addTravelStory(travelStory));
    }

    @DeleteMapping("/{id}")
    public void deleteTravelStory(@PathVariable long id) {
        tss.deleteTravelStory(id);
    }

    @GetMapping("/byHead/{head}")
    public TravelStory getByHead(@PathVariable String head) {
        return (tss.getByHead(head));
    }

    @PutMapping("/edit")
    public TravelStoryDTO edit(@RequestBody TravelStoryDTO travelStory) {
        return (tss.editTravelStory(travelStory));
    }

    @GetMapping("/getAll")
    public List<TravelStory> getAll() {
        return (tss.getAll());
    }

    @GetMapping("/byId/{id}")
    public TravelStory getById(@PathVariable long id) {
        return (tss.getById(id));
    }

    @GetMapping("/byOwner/{id}")
    public List<TravelStory> getUserStories(@PathVariable long id) {
        return (tss.getByUserOwner(id));
    }
}
