package com.scott.service.impl;

import com.scott.domain.Job;
import com.scott.repository.JobRepository;
import com.scott.service.JobService;
import com.scott.service.dto.JobQueryCriteria;
import com.scott.service.mapstruct.JobMapper;
import com.scott.utils.PageUtil;
import com.scott.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * project name  my-eladmin-backend1
 * filename  JobServiceImpl
 *
 * @author liscott
 * @date 2022/12/30 14:21
 * description  TODO
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public Map<String, Object> queryAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, query, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(jobMapper::toDto).getContent(), page.getTotalElements());
    }
}

