package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Equipment;
import generator.service.EquipmentService;
import generator.mapper.EquipmentMapper;
import org.springframework.stereotype.Service;

/**
* @author 33882
* @description 针对表【equipment】的数据库操作Service实现
* @createDate 2026-01-12 19:09:57
*/
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment>
    implements EquipmentService{

}




