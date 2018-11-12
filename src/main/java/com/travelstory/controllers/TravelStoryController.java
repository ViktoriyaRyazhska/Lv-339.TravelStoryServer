package com.travelstory.controllers;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;
import com.travelstory.services.TravelStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/travelStory")
public class TravelStoryController {
    @Autowired
    private TravelStoryService tss;

    @PutMapping("/add")
    public TravelStoryDTO addTravelStory(@Valid @RequestBody TravelStoryDTO travelStory) {
        return (tss.addTravelStory(travelStory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TravelStoryDTO> deleteTravelStory(@PathVariable long id) {
        tss.deleteTravelStory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byHead/{head}")
    public TravelStory getByHead(@PathVariable String head) {
        return (tss.getByHead(head));
    }

    @PutMapping("/edit")
    public TravelStoryDTO edit(@RequestBody TravelStoryDTO travelStory) {
        return (tss.editTravelStory(travelStory, travelStory.getId()));
    }

    @GetMapping("/getAll")
    public List<TravelStory> getAll() {
        return (tss.getAll());
    }

    @GetMapping("/byId/{id}")
    public TravelStory getById(@PathVariable long id) {
        return (tss.getById(id));
    }

    @GetMapping("/byUser/{id}")
    public List<TravelStoryDTO> getUserStories(@PathVariable long id) {
        return (tss.getByUserOwner(id));
    }
}
