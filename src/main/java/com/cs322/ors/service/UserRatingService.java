package com.cs322.ors.service;

import com.cs322.ors.db.UserRatingRepository;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRatingService {

    @Autowired
    private UserRatingRepository userRatingRepository;

    public List<UserRating> getAllUserRatings(){
        return userRatingRepository.findAll();
    }

    /*
     * Repository looks for all UserRating objects that contain
     * a specific User - the critic who createed the rating
     */
//    public List<UserRating> getUserRatings(User critic){
//        return userRatingRepository.findByUserCritic(critic);
//    }

    public void postUserRating(UserRating rating){
        userRatingRepository.save(rating);
    }

    public void deleteUserRating(long id) {
        Optional<UserRating> rating = this.userRatingRepository.findById(id);
        userRatingRepository.delete(rating.get());
    }

}
