package com.serve.api.comm.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean//基础dao,作用是不用每次都继承jpa的接口 不作为bean注入spring容器
public interface BaseRepo<T, V> extends JpaRepository<T, V>, JpaSpecificationExecutor<T> {
}
