package com.scott.base;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  BaseMapper
 *
 * @author liscott
 * @date 2022/12/29 10:15
 * description  D-> Dto, E-> Entity
 */
public interface BaseMapper<D, E> {
    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <D> toDto(List<E> entityList);
}
