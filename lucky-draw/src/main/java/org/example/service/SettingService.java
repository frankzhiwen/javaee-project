package org.example.service;

import org.example.mapper.SettingMapper;
import org.example.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingMapper settingMapper;

    public Setting queryByUserId(Integer id) {
        return settingMapper.selectByUserId(id);
    }

    public int update(Integer batchNumber, Integer userId) {
        return settingMapper.update(batchNumber, userId);
    }
}
