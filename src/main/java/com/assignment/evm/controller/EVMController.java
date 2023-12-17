package com.assignment.evm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EVMController {
    private final HashMap<String,Integer> candidateWithVoteCount = new HashMap<>();

    @PostMapping("/entercandidate")
    public ResponseEntity<?>registerCandidate(@RequestParam(value = "name") String name){
        HashMap<String, Object>response = new HashMap<>();
        int size = name.length();
        // Validating the name provided by user
        for(int i = 0 ; i < size ; i++) {
            char c = name.charAt(i);
            if (Character.isDigit(c)) {
                response.put("Error", "Given Name is not Valid !");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
            // Validating Candidate name if already registered
            if(candidateWithVoteCount.containsKey(name)){
                response.put("MSG","Candidate Name Already Registered");
            }
            else {
                candidateWithVoteCount.put(name,0);
                response.put("Msg","Candidate Registered Successfully !");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @PostMapping("/castvote")
        public ResponseEntity<?>castVote(@RequestParam(value = "name") String name){
            HashMap<String, Object>response = new HashMap<>();
            // Validating Candidate name is registered or not
            if(!candidateWithVoteCount.containsKey(name)){
                response.put("Error","Candidate Name Not Found !");
            }
            // Increment Vote by 1
            else {
                int vote = candidateWithVoteCount.get(name);
                candidateWithVoteCount.put(name,vote+1);
                response.put("Vote Count",candidateWithVoteCount.get(name));
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    @GetMapping("/countvote")
    public ResponseEntity<?>countVote(@RequestParam(value = "name") String name){
        HashMap<String, Object>response = new HashMap<>();
        // Validating Candidate name is registered or not
        if(!candidateWithVoteCount.containsKey(name)){
            response.put("Error","Candidate Name Not Found !");
        }
        // Getting the vote count
        else {
            response.put("Vote Count",candidateWithVoteCount.get(name));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/listvote")
    public ResponseEntity<?>listVote(){
        HashMap<String,Object>response = new HashMap<>();
        ArrayList<HashMap<String, Object>> array = new ArrayList<>();
        for(Map.Entry<String,Integer> iterateList : candidateWithVoteCount.entrySet()){
            HashMap<String,Object>list = new HashMap<>();
           list.put("Name",iterateList.getKey());
           list.put("Votes",iterateList.getValue());
           array.add(list);
        }
        response.put("Vote List",array);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/getwinner")
    public ResponseEntity<?>getWinner(){
        Map.Entry<String,Integer> winner = null;
        for(Map.Entry<String,Integer> maxVote : candidateWithVoteCount.entrySet()){
            if (winner == null || maxVote.getValue().compareTo(winner.getValue()) > 0)
            {
                winner = maxVote;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Winner is "+winner.getKey()+" with number of Votes "+winner.getValue());
    }
    }

