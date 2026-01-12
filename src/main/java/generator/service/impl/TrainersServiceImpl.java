package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Trainers;
import generator.service.TrainersService;
import generator.mapper.TrainersMapper;
import org.springframework.stereotype.Service;

/**
* @author 33882
* @description 针对表【trainers】的数据库操作Service实现
* @createDate 2026-01-12 19:10:00
*/
@Service
public class TrainersServiceImpl extends ServiceImpl<TrainersMapper, Trainers>
    implements TrainersService{

}




