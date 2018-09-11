package cn.pzhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.Institute;

public interface InstituteRepository extends JpaRepository<Institute, Integer>,JpaSpecificationExecutor<Institute>{


}