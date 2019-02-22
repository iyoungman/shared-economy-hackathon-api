package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-02-14.
 */

public interface ImageRepository extends JpaRepository<Image, Long> {
}
