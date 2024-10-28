package com.platform.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.data.entity.po.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
}
