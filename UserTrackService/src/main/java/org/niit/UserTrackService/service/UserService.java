package org.niit.UserTrackService.service;

import org.niit.UserTrackService.domain.Track;
import org.niit.UserTrackService.domain.User;
import org.niit.UserTrackService.exception.TrackNotFoundException;
import org.niit.UserTrackService.exception.UserAlreadyExistException;
import org.niit.UserTrackService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User addUser(User user) throws UserAlreadyExistException;
    public User addTrackForUser(String userId, Track track) throws UserNotFoundException;
    public User deleteTrackFromUser(String userId,int trackId) throws UserNotFoundException, TrackNotFoundException;
    List<Track> getTrackForUser(String userId) throws UserNotFoundException;
    User updateTrackForUser(String userId,Track track) throws UserNotFoundException;
}
