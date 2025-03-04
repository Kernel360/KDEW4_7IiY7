package com.example.demo.user.db;

import com.example.demo.db.SimpleDataRepository;
import com.example.demo.user.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    public List<UserEntity> findAllByScoreGreaterThen(int score) {
        return this.findAll()
                .stream()
                .filter(
                        it -> {
                            return it.getScore() >= score;
                        }
                ).collect(Collectors.toList());
    }
}
