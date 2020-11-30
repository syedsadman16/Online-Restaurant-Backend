package com.cs322.ors.service;

import com.cs322.ors.db.UserRatingRepository;
import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import com.cs322.ors.model.UserWarning;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRatingService {

    // Possibly used for Disputing ratings
    // @Autowired
    // private UserRatingRepository userRatingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWarningService userWarningService;

    /*
     * View list of all ratings regardless of user type
     */
    public List<User> getAllUserRatings(){
        return userRepository.findAll();
    }

    /*
     * Get all ratings for a specific user
     */
    public List<UserRating> getUsersRatings(Long userId){
        User user = userRepository.findById(userId).get();
        return user.getRatingList();
    }

    /*
     * Get one rating for a user 
     */
    public UserRating getUserSingleRatings(Long userId, Long ratingId){
        User user = userRepository.findById(userId).get();
        return user.getSingleUserRating(ratingId);
    }

    /*
     * Add rating for a victim with current user being the critic
     * First find the user that is being rated 
     * Next, create a new rating with the current user as the critic
     * Finally, save the new rating to the victims ratings list
     * If rating is below 3, file it as a complaint
     */
    public void postUserRating(Long victimId, UserRating rating, User critic){
        User victim = userRepository.findById(victimId).get();
        if(rating.getRating() < 3){
            userWarningService.createWarning(new UserWarning(victim, rating.getComments()));
        }
        UserRating newRating = rating;
        newRating.setCritic(critic);
        victim.addToRatings(newRating);
        userRepository.save(victim);
    }

    /*
     * Locate user object and edit rating from users list
     */
    public void editUserRating(UserRating rating, Long userId, Long ratingId){
        User updatedUser = userRepository.findById(userId).get();
        updatedUser.updateRating(rating, ratingId);
        userRepository.save(updatedUser);
    }

    /*
     * Delete rating from list
     * Userid to find the user that contains the unwanted rating
     * Ratingid is used to search users list and remove it
     */
    public void deleteUserRating(Long userId, Long ratingId) {
        User userWithRating = userRepository.findById(userId).get();
        userWithRating.deleteRating(ratingId);
        userRepository.save(userWithRating);
    }

}
