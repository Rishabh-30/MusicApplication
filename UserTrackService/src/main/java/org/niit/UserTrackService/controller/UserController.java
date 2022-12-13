package org.niit.UserTrackService.controller;


import org.niit.UserTrackService.domain.Track;
import org.niit.UserTrackService.domain.User;
import org.niit.UserTrackService.exception.TrackNotFoundException;
import org.niit.UserTrackService.exception.UserAlreadyExistException;
import org.niit.UserTrackService.exception.UserNotFoundException;
import org.niit.UserTrackService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistException {
        ResponseEntity responseEntity = null;
        try{
            user.setTrackList(new ArrayList<>());
            responseEntity = new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("/track/addTrack/{userId}")
    public ResponseEntity<?> addProductForUser(@PathVariable String userId, @RequestBody Track track) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.addTrackForUser(userId,track), HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("/track/deleteTrack/{userId}/{trackId}")
    public ResponseEntity<?> deleteProductForUser(@PathVariable(value = "trackId")int trackId,@PathVariable(value = "userId") String userId) throws TrackNotFoundException, UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.deleteTrackFromUser(userId, trackId), HttpStatus.OK);
        }catch (TrackNotFoundException e){
            throw new TrackNotFoundException();
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        } catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/track/tracks/{userId}")
    public ResponseEntity<?> getProductsForUser(@PathVariable String userId) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.getTrackForUser(userId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("/track/updateTrack/{userId}")
    public ResponseEntity<?> updateTrackForUser(@PathVariable String userId,@RequestBody Track track) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.updateTrackForUser(userId,track), HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
