package xyz.luckypeak.mybatisplusdemo.service.impl;

import xyz.luckypeak.mybatisplusdemo.po.Address;
import xyz.luckypeak.mybatisplusdemo.mapper.AddressMapper;
import xyz.luckypeak.mybatisplusdemo.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luckypeak
 * @since 2024-11-24
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
