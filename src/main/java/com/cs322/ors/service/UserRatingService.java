package com.cs322.ors.service;

import com.cs322.ors.db.UserRatingRepository;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

//    /*
//     * Repository looks for all UserRating objects that contain
//     * a specific User - the critic who createed the rating
//     */
//    public List<UserRating> getUserRatings(User critic){
//        Session session = HibernateUtil.getHibernateSession();
//        Criteria criteria = session.createCriteria(YourClass.class);
//        List<UserRating> list = criteria.add(Restrictions.eq("yourField", yourFieldValue)).list();
//        return userRatingRepository.findByUserCritic(critic);
//    }

    public void postUserRating(UserRating rating){
        userRatingRepository.save(rating);
    }

    public void editUserRating( UserRating rating){
        userRatingRepository.save(rating);
    }

    public void deleteUserRating(long id) {
        Optional<UserRating> rating = this.userRatingRepository.findById(id);
        userRatingRepository.delete(rating.get());
    }

}
