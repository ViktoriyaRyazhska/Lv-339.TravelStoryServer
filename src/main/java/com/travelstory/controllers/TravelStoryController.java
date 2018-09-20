package com.travelstory.controllers;

import com.travelstory.services.TravelStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "/travelStory")
public class TravelStoryController {
    @Autowired
    TravelStoryService tss;

    @PutMapping("/add")
    public TravelStory addTravelStory(@Valid @RequestBody TravelStory travelStory) {
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

    @PutMapping("/edit/{id}")
    public TravelStory edit(@PathVariable long id, @RequestBody TravelStory travelStory) {
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
