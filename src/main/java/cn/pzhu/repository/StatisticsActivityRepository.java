package cn.pzhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.pzhu.model.StatisticsActivity;

public interface StatisticsActivityRepository 
extends JpaRepository<StatisticsActivity, Integer>,JpaSpecificationExecutor<StatisticsActivity>{

}
