package com.scott.repository;

import com.scott.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * project name  my-eladmin-backend1
 * filename  JobRepository
 * @author liscott
 * @date 2022/12/30 13:44
 * description  TODO
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
}
