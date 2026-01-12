package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Customers;
import generator.service.CustomersService;
import generator.mapper.CustomersMapper;
import org.springframework.stereotype.Service;

/**
* @author 33882
* @description 针对表【customers】的数据库操作Service实现
* @createDate 2026-01-12 19:09:53
*/
@Service
public class CustomersServiceImpl extends ServiceImpl<CustomersMapper, Customers>
    implements CustomersService{

}




