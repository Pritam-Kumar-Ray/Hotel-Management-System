package com.hm.user.service.repositoties;

import com.hm.user.service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<UserEntity, String> {
}
