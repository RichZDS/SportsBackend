package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Employees;
import generator.service.EmployeesService;
import generator.mapper.EmployeesMapper;
import org.springframework.stereotype.Service;

/**
* @author 33882
* @description 针对表【employees】的数据库操作Service实现
* @createDate 2026-01-12 19:09:55
*/
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees>
    implements EmployeesService{

}




